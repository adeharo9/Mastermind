package domain.controllers;

import domain.classes.*;
import enums.*;
import exceptions.*;
import javafx.application.Platform;
import persistence.GamePersistence;
import persistence.PlayerPersistence;
import persistence.RankingPersistence;
import presentation.controllers.PresentationController;
import presentation.runnables.*;
import util.Constants;
import util.Pair;
import util.Translate;
import util.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Domain Controller
 *
 * @author Alejandro de Haro, Rafael, Alex
 */

public class DomainController
{
    private State state;
    private Set<String> savedGames;

    //private final OldPresentationController oldPresentationController;
    private PresentationController presentationController;

    private BoardController boardController;
    private GameController gameController;
    private PlayerController loggedPlayerController;
    private PlayerController codeMakerController;
    private PlayerController codeBreakerController;
    private Ranking ranking;

    private GamePersistence gamePersistence;
    private PlayerPersistence playerPersistence;
    private RankingPersistence rankingPersistence;

    public DomainController()
    {
        state = State.INIT_PROGRAM;
        savedGames = new HashSet<>();

        //oldPresentationController = new OldPresentationController();
        this.presentationController = null;

        boardController = new BoardController();
        gameController = new GameController();
        loggedPlayerController = new PlayerController();
        ranking = new Ranking();

        gamePersistence = new GamePersistence();
        playerPersistence = new PlayerPersistence();
        rankingPersistence = new RankingPersistence();
    }

    public void setPresentationController(final PresentationController presentationController)
    {
        this.presentationController = presentationController;
    }

    /* EXECUTE */

    private boolean hasToBlock(final Role role)
    {
        return gameController.getMode() == Mode.HUMAN_VS_HUMAN || loggedPlayerController.getRole() == role;
    }

    private void logInUser(String username, String password) throws IOException, WrongPasswordException, ClassNotFoundException
    {
        Player player = playerPersistence.load(username);
        PlayerController playerController = new PlayerController(player);

        boolean b = playerController.checkPassword(password);
        if (!b) throw new WrongPasswordException();

        loggedPlayerController = playerController;
    }

    private void registerUser(final String username, final String password, final String confirmPassword) throws IOException, PasswordMismatchException
    {
        boolean b = password.equals(confirmPassword);
        if(!b) throw new PasswordMismatchException();

        b = playerPersistence.exists(username);
        if(b) throw new FileAlreadyExistsException("");

        Player player = loggedPlayerController.newHuman(username, password);

        playerPersistence.save(player);
    }

    private void newGame(final Mode mode, Role role, final Difficulty difficulty)
    {
        loggedPlayerController.restart();
        presentationController.clear();

        List<Player> players = new ArrayList<>();

        PlayerController playerController1 = null;
        PlayerController playerController2 = null;

        switch(mode)
        {
            case HUMAN_VS_HUMAN:
                playerController1 = loggedPlayerController;
                playerController2 = new PlayerController();

                playerController2.newHuman(Utils.autoID());
                break;

            case HUMAN_VS_CPU:
                playerController1 = loggedPlayerController;
                playerController2 = new PlayerController();

                playerController2.newCPU(Utils.autoID());
                break;

            case CPU_VS_CPU:
                role = Role.autoRole();

                playerController1 = new PlayerController();
                playerController2 = new PlayerController();

                playerController1.newCPU(Utils.autoID());
                playerController2.newCPU(Utils.autoID());

                loggedPlayerController.setRole(Role.WATCHER);
                break;

            default:
                break;
        }

        playerController1.setRole(role);
        playerController2.setRole(Role.complementaryRole(role));

        switch (role)
        {
            case CODE_MAKER:
                codeMakerController = playerController1;
                codeBreakerController = playerController2;
                break;
            case CODE_BREAKER:
                codeMakerController = playerController2;
                codeBreakerController = playerController1;
                break;
            default:
                throw new IllegalArgumentException();
        }

        players.add(playerController1.getPlayer());
        players.add(playerController2.getPlayer());

        Board board = boardController.newBoard(difficulty);

        gameController.newGame(Utils.autoID(), difficulty, mode, board, players);
    }

    private void loadSavedGamesList() throws IOException
    {
        savedGames.clear();
        Player loggedPlayer = loggedPlayerController.getPlayer();
        savedGames = playerPersistence.loadSavedGames(loggedPlayer.getId());
    }

    private void loadGame(String id) throws IOException, ClassNotFoundException
    {
        Player loggedPlayer = loggedPlayerController.getPlayer();
        String player = loggedPlayer.getId();
        Game game = gamePersistence.load(id, player);
        gameController.setGameByReference(game);
        boardController.setBoardByReference(game.getBoard());

        List<Player> players = game.getPlayers();

        for(int i = 0; i < players.size(); ++i)
        {
            PlayerController playerController;

            String playerId = players.get(i).getId();

            if(playerId.equals(loggedPlayerController.getId()))
            {
                loggedPlayerController.setRole(players.get(i).getRole());
                players.set(i, loggedPlayerController.getPlayer());
                playerController = loggedPlayerController;
            }
            else
            {
                playerController = new PlayerController(players.get(i));
            }

            Role role = playerController.getRole();

            switch (role)
            {
                case CODE_MAKER:
                    codeMakerController = playerController;
                    break;
                case CODE_BREAKER:
                    codeBreakerController = playerController;
                    break;
                case WATCHER:
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        presentationController.clear();

        List<Turn> turnSet = boardController.getTurnSet();
        List<List<Color>> codes = new ArrayList<>(turnSet.size());
        List<List<Color>> corrections = new ArrayList<>(turnSet.size());

        for(final Turn turn : turnSet)
        {
            List<Color> code = turn.getCodePins();
            List<Color> correction = turn.getCorrectionPins();

            codes.add(code);

            if(!correction.isEmpty())
            {
                corrections.add(correction);
            }
        }

        presentationController.setSolution(boardController.getSolution().getCodePins());
        presentationController.setCodes(codes);
        presentationController.setCorrections(corrections);
    }

    private void saveGame() throws IOException
    {
        Game game = gameController.getGame();
        String gameId = gameController.getId();
        String playerId = loggedPlayerController.getId();

        gamePersistence.save(game, playerId);
        playerPersistence.savePlayerGame(gameId, playerId);
    }

    private void renameGame(final String gameId) throws IOException
    {
        Game game = gameController.getGame();
        game.setId(gameId);
    }

    public void deleteGame(final String gameId) throws IOException
    {
        String playerId = loggedPlayerController.getId();
        gamePersistence.setPlayerPath(playerId + "/");
        gamePersistence.delete(gameId);

        playerPersistence.deletePlayerGame(gameId, playerId);
    }

    public void renameUsername(final String username) throws IOException, ClassNotFoundException
    {
        Player loggedPlayer = loggedPlayerController.getPlayer();
        String player = loggedPlayer.getId();

        playerPersistence.renamePlayer(player, username);
        loggedPlayer.setId(username);
        playerPersistence.delete(username);
        playerPersistence.save(loggedPlayer);
    }

    private void changePassword(final String oldPassword, final String newPassword, final String confirmNewPassword) throws IOException, WrongPasswordException, PasswordMismatchException
    {
        boolean b = loggedPlayerController.checkPassword(oldPassword);
        if(!b) throw new WrongPasswordException();

        b = newPassword.equals(confirmNewPassword);
        if(!b) throw new PasswordMismatchException();

        Player loggedPlayer = loggedPlayerController.getPlayer();
        loggedPlayer.setPassword(newPassword);
        String username = loggedPlayerController.getId();
        deleteUser(username);

        Player player = loggedPlayerController.newHuman(username, newPassword);

        playerPersistence.save(player);
    }

    private void deleteUser(final String username) throws IOException
    {
        playerPersistence.delete(username);
    }

    private void deleteConfigFile(final String username) throws IOException
    {
        playerPersistence.deleteConfigFile(username);
    }

    private void play(PlayerController playerController) throws IllegalArgumentException, ReservedKeywordException, IllegalActionException, InterruptedException
    {
        Action action;

        Code code = boardController.getSolution();
        Turn lastTurn = boardController.getLastTurn();
        Difficulty difficulty = boardController.getDifficulty();

        action = playerController.play(difficulty, lastTurn, code, boardController.isFirstTurn());

        if(action != null)
        {
            boardController.checkAction(action);
            boardController.addAction(action);

            updateBoard();
        }
    }

    private void playCodeBreaker() throws IllegalArgumentException, ReservedKeywordException, IllegalActionException, InterruptedException
    {
        play(codeBreakerController);
    }

    private void playCodeCorrecter() throws IllegalArgumentException, ReservedKeywordException, IllegalActionException, InterruptedException
    {
        play(codeMakerController);
        if(!boardController.isFirstTurn()) gameController.pointsEndTurn();
    }

    private void playCodeMaker() throws IllegalArgumentException, ReservedKeywordException, IllegalActionException, InterruptedException
    {
        play(codeMakerController);
        if(!boardController.isFirstTurn()) gameController.pointsEndTurn();
    }

    private String generateHint() throws GameNotStartedException
    {
        boolean b = gameController.hasStarted();
        if(!b) throw new GameNotStartedException();

        int number = 0;
        int type = ThreadLocalRandom.current().nextInt(0, 2);

        Color color;
        String message;
        Code solution = boardController.getSolution();
        List<Color> solutionPins = solution.getCodePins();

        switch(type)
        {
            case 0:
                number = (int) (Math.random() * solution.size());
                color = solutionPins.get(number);

                message = "Token in position " + (number + 1) + " is " + color.getStrDescription().toLowerCase();
                break;

            case 1:
                Difficulty difficulty = boardController.getDifficulty();
                int numColors = Constants.getNumColorsByDifficulty(difficulty);
                color = Color.getRandomColor(numColors);

                for(final Color pin : solutionPins)
                {
                    if(pin == color)
                    {
                        ++number;
                    }
                }

                message = "There " + ((number == 1) ? "is" : "are") + " " + number + " " + color.getStrDescription().toLowerCase() + " token" + ((number == 1) ? "" : "s");
                break;

            default:
                throw new IllegalArgumentException();
        }

        return message;
    }

    /* USER INTERACTION METHODS */

    private void updateBoard()
    {
        Turn lastTurn = boardController.getLastTurn();

        if(lastTurn == null)
        {
            Code solution = boardController.getSolution();
            presentationController.setSolution(solution.getCodePins());
        }
        else
        {
            Code code = lastTurn.getCode();
            Code correction = lastTurn.getCorrectionCode();

            if(correction.getCodePins().isEmpty())
            {
                presentationController.addCode(code.getCodePins());
            }
            else
            {
                presentationController.addCorrection(correction.getCodePins());
            }
        }
    }

    private void loadRanking() throws IOException, ClassNotFoundException
    {
        if(rankingPersistence.exists())
        {
            ranking = rankingPersistence.load();
        }
    }

    private void saveRanking() throws IOException, ClassNotFoundException
    {
        if(rankingPersistence.exists())
        {
            rankingPersistence.delete();
        }
        rankingPersistence.save(ranking);
    }

    private void updateRanking()
    {
        String playerId = loggedPlayerController.getId();
        int points = gameController.getPoints();

        if(ranking.toTopTen(playerId, points))
        {
            ranking.addToTopTen(playerId, points);
        }
    }

    private void runOnGUIThreadAndWait(final Runnable runnable) throws InterruptedException
    {
        Platform.runLater(runnable);

        while(!PresentationController.threadHasFinished())
        {
            wait();
        }

        PresentationController.clearThreadHasFinished();
    }

    private void updateView(final View view) throws InterruptedException
    {
        runOnGUIThreadAndWait(new UpdateViewRunnable(presentationController, view.getViewFile()));
    }

    private void popUpView(final PopUpWindowStyle popUpWindowStyle, final View view) throws InterruptedException
    {
        runOnGUIThreadAndWait(new PopUpViewRunnable(presentationController, popUpWindowStyle, view.getViewFile()));
    }

    private void errorMessage(final String message)
    {
        Platform.runLater(new ProcessInfoRunnable(presentationController, message));
    }

    private void showMessageAndWait(final String message) throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, message));
    }

    private void showHint(final String message) throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, message));
    }

    private void showScore(final String message) throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, message));
    }

    private void showInfo() throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, Constants.INFO_MESSAGE));
    }

    private void showLoadedGames(final Set<String> savedGames) throws InterruptedException
    {
        runOnGUIThreadAndWait(new ShowLoadedGamesRunnable(presentationController, savedGames));
    }

    private void showUsername() throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, loggedPlayerController.getId()));
    }

    private void showRenameUsernameTextField() throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, Constants.EDIT_USERNAME));
    }

    private void showRanking(List<Pair<String, Integer>> topTen) throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, topTen));
    }

    private void renderBoard(final Difficulty difficulty)
    {
        Platform.runLater(new RenderBoardRunnable(presentationController, difficulty));
    }

    private void renderLastTurn() throws InterruptedException
    {
        Platform.runLater(new RenderLastTurnRunnable(presentationController, false));
    }

    private void renderLastTurnBlocking() throws InterruptedException
    {
        runOnGUIThreadAndWait(new RenderLastTurnRunnable(presentationController, true));
    }

    private Runnable getUpdateBoardRunnable(final PlayingAction playingAction)
    {
        Runnable runnable;

        switch (playingAction)
        {
            case CODE_BREAK:
                runnable = () -> presentationController.updateToCodeBreakerBoard();
                break;
            case CODE_CORRECT:
                runnable = () -> presentationController.updateToCodeCorrecterBoard();
                break;
            case CODE_MAKE:
                runnable = () -> presentationController.updateToCodeMakerBoard();
                break;
            default:
                throw new IllegalArgumentException();
        }

        return runnable;
    }

    private void updateBoardTo(final PlayingAction playingAction) throws InterruptedException
    {
        Runnable runnable = getUpdateBoardRunnable(playingAction);

        Platform.runLater(runnable);
    }

    /* MAIN STATE MACHINE */

    public synchronized void exe() throws InterruptedException
    {
        int returnState;
        String returnString = null;
        String gameId = null;
        String username;
        String password;
        String confirmPassword;
        String currentPassword;

        Mode mode = null;
        Role role = null;
        Difficulty difficulty = null;

        while(!state.equals(State.CLOSE_PROGRAM))
        {
            switch(state)
            {
                case CHECK_GAME_HAS_FINISHED:
                    boolean hasFinished = gameController.hasFinished();

                    if(hasFinished)
                    {
                        try
                        {
                            renderLastTurn();

                            if(gameController.getMode() != Mode.CPU_VS_CPU)
                            {
                                updateRanking();
                                saveRanking();
                            }
                        }
                        catch (IOException | ClassNotFoundException e)
                        {
                            errorMessage(Constants.RANKING_LOAD_ERROR);
                        }
                    }

                    state = Translate.booleanModeToStateCheckGameHasFinished(hasFinished);

                    break;

                case CLOSE_PROGRAM:
                    // This state is never reached since it is the loop exit condition.
                    //
                    // Its presence here is only for completeness purposes.
                    break;

                case CLOSE_PROGRAM_WARNING:
                    popUpView(PopUpWindowStyle.WARNING, View.CLOSE_PROGRAM_WARNING_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateCloseProgramWarning(returnState);

                    break;

                case CONTINUE_GAME:
                    state = State.RENDER_BOARD;
                    break;

                case EDIT_USER_MENU:
                    updateView(View.EDIT_USER_VIEW);
                    showUsername();

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateEditUserMenu(returnState);

                    break;

                case EDIT_USERNAME:
                    showRenameUsernameTextField();
                    username = PresentationController.getUsername();

                    try
                    {
                        renameUsername(username);
                    }
                    catch(IOException | ClassNotFoundException e)
                    {
                        errorMessage(Constants.USERNAME_ALREADY_EXISTS);
                    }
                    state = State.EDIT_USER_MENU;
                    break;

                case EDIT_PASSWORD:
                    currentPassword = PresentationController.getCurrentPassword();
                    password = PresentationController.getNewPassword();
                    confirmPassword = PresentationController.getConfirmPassword();

                    try
                    {
                        changePassword(currentPassword, password, confirmPassword);
                        errorMessage(Constants.NEW_PASSWORD_SAVED);
                    }
                    catch(WrongPasswordException e)
                    {
                        errorMessage(Constants.WRONG_PASSWORD);
                    }
                    catch (PasswordMismatchException pme)
                    {
                        errorMessage(Constants.PASSWORDS_MUST_MATCH);
                    }
                    catch (IOException ioe)
                    {
                        errorMessage(Constants.PASSWORD_CHANGE_ERROR);
                    }
                    finally
                    {
                        state = State.EDIT_USER_MENU;
                    }

                    break;

                case EXIT_CURRENT_GAME:
                    state = State.MAIN_MENU;
                    break;

                case EXIT_CURRENT_GAME_WARNING:
                    popUpView(PopUpWindowStyle.WARNING, View.EXIT_CURRENT_GAME_WARNING_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateExitCurrentGameWarning(returnState);

                    break;

                case GAME_OVER_MENU:
                    popUpView(PopUpWindowStyle.GAME_OVER, View.GAME_OVER_VIEW);
                    showScore(String.valueOf(gameController.getGame().getPoints()));
                    /*gameController.pointsEndGame();*/

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateGameOverMenu(returnState);


                    break;

                case GAME_PAUSE_MENU:
                    updateView(View.PAUSE_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateGamePauseMenu(returnState);

                    break;

                case HINT_MENU:
                    popUpView(PopUpWindowStyle.INTERACTION, View.HINT_VIEW);
                    try
                    {
                        returnString = generateHint();
                        gameController.pointsClue();
                    }
                    catch (GameNotStartedException e)
                    {
                        returnString = Constants.GAME_NOT_STARTED_ERROR;
                    }
                    finally
                    {
                        showHint(returnString);

                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStateHintMenu(returnState);
                    }
                    break;

                case INFO_MENU:
                    updateView(View.INFO_VIEW);
                    showInfo();

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateInfoMenu(returnState);

                    break;

                case INIT_PROGRAM:
                    state = State.LOAD_RANKING;
                    
                    break;

                case INIT_SESSION_MENU:
                    updateView(View.INIT_SESSION_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateInitSessionMenu(returnState);

                    break;

                case LAST_ACTIONS:
                    Platform.exit();
                    state = State.CLOSE_PROGRAM;
                    break;

                case LOAD_GAME:
                    try
                    {
                        loadGame(gameId);
                        state = State.RENDER_BOARD;
                    }
                    catch(FileNotFoundException e)
                    {
                        errorMessage(Constants.GAME_DOESNT_EXISTS);
                        state = State.LOAD_GAME_MENU;
                    }
                    catch (IOException | ClassNotFoundException e)
                    {
                        errorMessage(Constants.GAME_LOADING_ERROR);
                        state = State.LOAD_GAME_MENU;
                    }
                    break;

                case LOAD_GAME_MENU:
                    updateView(View.LOAD_GAME_VIEW);

                    try
                    {
                        loadSavedGamesList();
                    }
                    catch (FileNotFoundException e)
                    {
                        errorMessage(Constants.PLAYER_HAS_NO_SAVED_GAMES);
                    }
                    catch (IOException e)
                    {
                        errorMessage(Constants.SAVED_GAMES_LIST_LOAD_ERROR);
                    }
                    finally
                    {
                        showLoadedGames(savedGames);

                        returnState = PresentationController.getReturnState();
                        gameId = PresentationController.getGameId();
                        //gameId = Translate.int2SavedGameId(savedGames, returnState);
                        state = Translate.int2StateLoadGameMenu(returnState);
                    }

                    break;

                case DELETE_GAME:
                    try
                    {
                        deleteGame(gameId);
                    }
                    catch (IOException e)
                    {
                        errorMessage(Constants.GAME_DELETING_ERROR);
                    }

                    state = State.LOAD_GAME_MENU;
                    break;

                case LOAD_RANKING:
                    try
                    {
                        loadRanking();
                    }
                    catch (IOException | ClassNotFoundException e)
                    {
                        errorMessage(Constants.RANKING_LOADING_ERROR);
                    }
                    finally
                    {
                        Thread.sleep(Constants.LOADING_TIME_MS);
                        state = State.INIT_SESSION_MENU;
                    }
                    break;

                case LOG_IN_MENU:
                    updateView(View.LOG_IN_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.intToStateLogInMenu(returnState);

                    break;

                case LOG_IN_USER:
                    try
                    {
                        username = PresentationController.getUsername();
                        password = PresentationController.getPassword();

                        logInUser(username, password);
                        state = State.MAIN_MENU;
                    }
                    catch(FileNotFoundException | WrongPasswordException e)
                    {
                        errorMessage(Constants.WRONG_USERNAME_OR_PASSWORD);
                        state = State.LOG_IN_MENU;
                    }
                    catch(IOException | ClassNotFoundException e)
                    {
                        errorMessage(Constants.USER_LOADING_ERROR);
                        state = State.LOG_IN_MENU;
                    }
                    break;

                case LOG_OUT_WARNING:
                    popUpView(PopUpWindowStyle.WARNING, View.LOG_OUT_WARNING_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateLogOutWarning(returnState);

                    break;

                case MAIN_MENU:
                    updateView(View.MAIN_MENU_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateMainMenu(returnState);

                    break;

                case NEW_GAME:
                    newGame(mode, role, difficulty);
                    state = State.RENDER_BOARD;
                    break;

                case NEW_GAME_MENU:
                    updateView(View.NEW_GAME_VIEW);

                    returnState = PresentationController.getMode();
                    mode = Translate.int2Mode(returnState);

                    returnState = PresentationController.getRole();
                    role = Translate.int2Role(returnState);

                    returnState = PresentationController.getDifficulty();
                    difficulty = Translate.int2Difficulty(returnState);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateGameSettingsMenu(returnState);

                    break;

                case PLAY_CODE_BREAKER:
                    try
                    {
                        if(hasToBlock(Role.CODE_BREAKER))
                        {
                            updateBoardTo(PlayingAction.CODE_BREAK);
                            renderLastTurnBlocking();
                        }
                        else
                        {
                            renderLastTurn();
                        }

                        playCodeBreaker();

                        state = State.PLAY_CODE_CORRECTER;
                    }
                    catch (IllegalActionException e)
                    {
                        errorMessage(e.getMessage());
                    }
                    catch (ReservedKeywordException e)
                    {
                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStatePlay(returnState);
                    }
                    break;

                case PLAY_CODE_CORRECTER:
                    try
                    {

                        if(hasToBlock(Role.CODE_MAKER))
                        {
                            updateBoardTo(PlayingAction.CODE_CORRECT);
                            renderLastTurnBlocking();
                        }
                        else
                        {
                            renderLastTurn();
                        }

                        playCodeCorrecter();

                        state = State.CHECK_GAME_HAS_FINISHED;
                    }
                    catch (IllegalActionException e)
                    {
                        errorMessage(e.getMessage());
                    }
                    catch (ReservedKeywordException e)
                    {
                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStatePlay(returnState);
                    }
                    break;

                case PLAY_CODE_MAKER:
                    try
                    {

                        if(hasToBlock(Role.CODE_MAKER))
                        {
                            updateBoardTo(PlayingAction.CODE_MAKE);
                            renderLastTurnBlocking();
                        }
                        else
                        {
                            renderLastTurn();
                        }

                        playCodeMaker();

                        state = State.CHECK_GAME_HAS_FINISHED;
                    }
                    catch (IllegalActionException e)
                    {
                        errorMessage(e.getMessage());
                    }
                    catch (ReservedKeywordException e)
                    {
                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStatePlay(returnState);
                    }
                    break;

                case PLAY_TURN:
                    boolean hasStarted = gameController.hasStarted();
                    boolean hasCodeToCorrect = boardController.hasCodeToCorrect();

                    state = Translate.booleanToStatePlayTurn(hasStarted, hasCodeToCorrect);

                    break;

                case RANKING_MENU:
                    updateView(View.RANKING_VIEW);
                    showRanking(ranking.getTopTen());

                    returnState = PresentationController.getReturnState();
                    state = Translate.intToStateRankingMenu(returnState);

                    break;

                default:
                    break;

                case REGISTER_MENU:
                    updateView(View.REGISTER_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.intToStateRegisterMenu(returnState);

                    break;

                case REGISTER_USER:
                    try
                    {
                        username = PresentationController.getUsername();
                        password = PresentationController.getPassword();
                        confirmPassword = PresentationController.getConfirmPassword();

                        registerUser(username, password, confirmPassword);
                        state = State.MAIN_MENU;
                    }
                    catch (PasswordMismatchException e)
                    {
                        errorMessage(Constants.PASSWORDS_MUST_MATCH);
                        state = State.REGISTER_MENU;
                    }
                    catch(FileAlreadyExistsException e)
                    {
                        errorMessage(Constants.USERNAME_ALREADY_EXISTS);
                        state = State.REGISTER_MENU;
                    }
                    catch (IllegalArgumentException iae)
                    {
                        errorMessage(Constants.EMPTY_PASSWORD_ERROR);
                        state = State.REGISTER_MENU;
                    }
                    catch(IOException e)
                    {
                        errorMessage(Constants.USER_REGISTERING_ERROR);
                        state = State.REGISTER_MENU;
                    }
                    break;

                case RENDER_BOARD:
                    updateView(View.GAME_IN_PROGRESS_VIEW);
                    renderBoard(boardController.getDifficulty());

                    state = State.CHECK_GAME_HAS_FINISHED;
                    break;

                case RESTART_GAME:
                    mode = gameController.getMode();
                    role = loggedPlayerController.getRole();
                    difficulty = boardController.getDifficulty();

                    state = State.NEW_GAME;
                    break;

                case SAVE_GAME:
                    try
                    {
                        renameGame(PresentationController.getGameId());
                        saveGame();
                        state = State.GAME_PAUSE_MENU;
                    }
                    catch (FileAlreadyExistsException e)
                    {
                        state = State.SAVE_GAME_OVERWRITE_MENU;
                    }
                    catch(IOException e)
                    {
                        popUpView(PopUpWindowStyle.WARNING, View.ERROR_MESSAGE_WARNING_VIEW);
                        showMessageAndWait(Constants.GAME_SAVING_ERROR);

                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStateErrorMessageWarning(returnState);
                    }
                    break;

                case SAVE_GAME_MENU:
                    popUpView(PopUpWindowStyle.INTERACTION, View.SAVE_GAME_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.intToStateSaveGameMenu(returnState);

                    break;

                case SAVE_GAME_OVERWRITE_MENU:
                    popUpView(PopUpWindowStyle.INTERACTION, View.SAVE_GAME_OVERWRITE_VIEW);

                    returnState = PresentationController.getReturnState();

                    try
                    {
                        if(returnState == 1)
                        {
                            gamePersistence.delete(gameController.getId());
                        }

                        state = Translate.intToStateSaveGameOverwrite(returnState);
                    }
                    catch (IOException ei)
                    {
                        popUpView(PopUpWindowStyle.WARNING, View.ERROR_MESSAGE_WARNING_VIEW);
                        showMessageAndWait(Constants.GAME_DELETING_ERROR);

                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStateErrorMessageWarning(returnState);
                    }
                    break;
            }
        }
    }
}
