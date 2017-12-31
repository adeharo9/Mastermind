package domain.controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import domain.classes.*;
import enums.*;
import exceptions.GameNotStartedException;
import exceptions.IllegalActionException;
import exceptions.ReservedKeywordException;
import exceptions.WrongPasswordException;
import javafx.application.Platform;
import persistence.GamePersistence;
import persistence.PlayerPersistence;
import persistence.RankingPersistence;
import presentation.controllers.OldPresentationController;
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
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Domain Controller
 *
 * @author Alejandro de Haro, Rafael, Alex
 */

public class DomainController
{
    private State state;
    private List<String> savedGames;

    private final OldPresentationController oldPresentationController;
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
        savedGames = new ArrayList<>();

        oldPresentationController = new OldPresentationController();
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

    private boolean hasToPrintBoard(final Role role)
    {
        return gameController.getMode() == Mode.HUMAN_VS_HUMAN || loggedPlayerController.getRole() == role || loggedPlayerController.getRole() == Role.WATCHER;
    }

    private void logInUser(String username, String password) throws IOException, WrongPasswordException, ClassNotFoundException
    {
        Player player = playerPersistence.load(username);
        PlayerController playerController = new PlayerController(player);

        boolean b = playerController.checkPassword(password);
        if (!b) throw new WrongPasswordException();

        loggedPlayerController = playerController;
    }

    private void registerUser(final String username, final String password, final String confirmPassword) throws IOException, WrongPasswordException
    {
        boolean b = password.equals(confirmPassword);
        if(!b) throw new WrongPasswordException();

        b = playerPersistence.exists(username);
        if(b) throw new FileAlreadyExistsException("");

        Player player = loggedPlayerController.newHuman(username, password);

        playerPersistence.save(player);
    }

    private void newGame(final Mode mode, Role role, final Difficulty difficulty)
    {
        loggedPlayerController.restart();
        presentationController.clear();
        oldPresentationController.clear();

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
        Player loggedPlayer = loggedPlayerController.getPlayer();
        savedGames = playerPersistence.loadSavedGames(loggedPlayer.getId());
    }

    private void loadGame(String id) throws IOException, ClassNotFoundException
    {
        Game game = gamePersistence.load(id);
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
        oldPresentationController.clear();
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

        oldPresentationController.setSolution(boardController.getSolution().getCodePins());
        oldPresentationController.setCodes(codes);
        oldPresentationController.setCorrections(corrections);
    }

    private void saveGame() throws IOException
    {
        Game game = gameController.getGame();
        gamePersistence.save(game);

        String gameId = gameController.getId();
        String playerId = loggedPlayerController.getId();

        playerPersistence.savePlayerGame(gameId, playerId);
    }

    private void renameGame(final String gameId) throws IOException
    {
        Game game = gameController.getGame();
        String gameIdToChange = game.getId();

        String playerId = loggedPlayerController.getId();
        deleteGame(gameIdToChange);
        playerPersistence.deletePlayerGame(gameIdToChange, playerId);

        game.setId(gameId);
        saveGame();
    }

    public void deleteGame(final String gameId) throws IOException
    {
        gamePersistence.delete(gameId);
    }

    public void renameUsername(final String username) throws IOException, ClassNotFoundException
    {
        Player loggedPlayer = loggedPlayerController.getPlayer();
        deleteUser(loggedPlayer.getId());

        loggedPlayer.setId(username);
        playerPersistence.save(loggedPlayer);
    }

    public void changePassword(final String password) throws IOException
    {
        String username = loggedPlayerController.getId();
        deleteUser(username);

        Player player = loggedPlayerController.newHuman(username, password);

        playerPersistence.save(player);
    }

    public void deleteUser(final String username) throws IOException
    {
        playerPersistence.delete(username);
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

    private void playCodeMaker() throws IllegalArgumentException, ReservedKeywordException, IllegalActionException, InterruptedException
    {
        play(codeMakerController);
        if(!boardController.isFirstTurn()) gameController.pointsEndTurn();
    }

    private void playCodeBreaker() throws IllegalArgumentException, ReservedKeywordException, IllegalActionException, InterruptedException
    {
        play(codeBreakerController);
    }

    private void giveClue() throws GameNotStartedException
    {
        boolean b = gameController.hasStarted();
        if(!b) throw new GameNotStartedException();

        int type = ThreadLocalRandom.current().nextInt(1, 3);
        int num = 0;

        Code c = boardController.getSolution();
        Color color;
        String name;
        List<Color> pins = c.getCodePins();

        switch(type)
        {
            case 1:
                num = (int) (Math.random() * c.size());
                color = pins.get(num);
                name = String.valueOf(color);
                ++num;
                break;

            case 2:
                Difficulty difficulty = boardController.getDifficulty();
                int numColors = Constants.getNumColorsByDifficulty(difficulty);
                color = Color.getRandomColor(numColors);

                for(int i = 0; i < c.size(); ++i){
                    if(pins.get(i) == color)
                    {
                        ++num;
                    }
                }

                name = String.valueOf(color);
                break;

            default:
                throw new IllegalArgumentException();
        }

        oldPresentationController.showClue(type,String.valueOf(num),name);
        /*presentationController.showClue(type,String.valueOf(num),name);*/
    }

    /* USER INTERACTION METHODS */

    private void updateBoard()
    {
        Turn lastTurn = boardController.getLastTurn();

        if(lastTurn == null)
        {
            Code solution = boardController.getSolution();
            presentationController.setSolution(solution.getCodePins());
            oldPresentationController.setSolution(solution.getCodePins());
        }
        else
        {
            Code code = lastTurn.getCode();
            Code correction = lastTurn.getCorrectionCode();

            if(correction.getCodePins().isEmpty())
            {
                presentationController.addCode(code.getCodePins());
                oldPresentationController.addCode(code.getCodePins());
            }
            else
            {
                presentationController.addCorrection(correction.getCodePins());
                oldPresentationController.addCorrection(correction.getCodePins());
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
    }

    private void updateView(View view) throws InterruptedException
    {
        runOnGUIThreadAndWait(new UpdateViewRunnable(presentationController, view.getViewFile()));
    }

    private void popUpView(View view) throws InterruptedException
    {
        runOnGUIThreadAndWait(new PopUpViewRunnable(presentationController, view.getViewFile()));
    }

    private void errorMessage(final String message)
    {
        Platform.runLater(new ProcessInfoRunnable(presentationController, message));
    }

    private void showHint() throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, Constants.INFO_MESSAGE));
    }

    private void showLoadedGames(final List<String> savedGames) throws InterruptedException
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
        Platform.runLater(new RenderLastTurnRunnable(presentationController));
    }

    private void renderLastTurnBlocking() throws InterruptedException
    {
        runOnGUIThreadAndWait(new RenderLastTurnRunnable(presentationController));
    }

    /* MAIN STATE MACHINE */

    public synchronized void exe() throws InterruptedException
    {
        int returnState;
        String returnString = null;
        String gameId = null;
        String username = null;
        String password = null;
        String confirmPassword = null;

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
                            oldPresentationController.printBoard(Role.CODE_MAKER);

                            if(gameController.getMode() != Mode.CPU_VS_CPU)
                            {
                                updateRanking();
                                saveRanking();
                            }
                        }
                        catch (IOException | ClassNotFoundException e)
                        {
                            oldPresentationController.rankingSaveError();
                        }
                    }

                    state = Translate.booleanModeToStateCheckGameHasFinished(hasFinished);

                    break;

                case CLOSE_PROGRAM:
                    break;

                case CLOSE_PROGRAM_WARNING:
                    popUpView(View.CLOSE_PROGRAM_WARNING_VIEW);

                    try
                    {
                        returnState = PresentationController.getReturnState();
                        state = Translate.int2StateCloseProgramWarning(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
                    break;

                case CONTINUE_GAME:
                    state = State.PLAY_TURN;
                    break;

                case EDIT_USER_MENU:
                    updateView(View.EDIT_USER_VIEW);
                    PresentationController.clearThreadHasFinished();
                    showUsername();

                    try
                    {
                        returnState = PresentationController.getReturnState();

                        state = Translate.int2StateEditUserMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
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

                    }

                    state = State.EDIT_USER_MENU;
                    break;

                case EDIT_PASSWORD:

                    password = PresentationController.getNewPassword();
                    confirmPassword = PresentationController.getConfirmPassword();

                    try
                    {
                        changePassword(password);
                    }
                    catch(IOException e){}
                    break;

                case EXIT_CURRENT_GAME:
                    state = State.MAIN_MENU;
                    break;

                case EXIT_CURRENT_GAME_WARNING:
                    updateView(View.EXIT_CURRENT_GAME_WARNING_VIEW);

                    try
                    {
                        returnState = PresentationController.getReturnState();
                        state = Translate.int2StateExitCurrentGameWarning(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
                    break;

                case GAME_OVER_MENU:
                    try
                    {
                        /*gameController.pointsEndGame();*/
                        returnState = oldPresentationController.gameOverMenu(String.valueOf(gameController.getGame().getPoints()));
                        state = Translate.int2StateGameOverMenu(returnState);
                    }
                    catch (IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }

                    break;

                case GAME_PAUSE_MENU:
                    updateView(View.PAUSE_VIEW);

                    try
                    {
                        returnState = PresentationController.getReturnState();
                        state = Translate.int2StateGamePauseMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
                    break;

                case HINT_MENU:
                    updateView(View.HINT_VIEW);

                    try
                    {
                        //PresentationController.giveClue();
                        giveClue();
                        gameController.pointsClue();
                    }
                    catch (GameNotStartedException e)
                    {
                        oldPresentationController.gameNotStartedError();
                    }
                    finally
                    {
                        state = State.GAME_PAUSE_MENU;
                        PresentationController.clearThreadHasFinished();
                    }
                    break;

                case INFO_MENU:
                    updateView(View.INFO_VIEW);
                    PresentationController.clearThreadHasFinished();
                    showHint();

                    try
                    {
                        returnState = PresentationController.getReturnState();

                        state = Translate.int2StateInfoMenu(returnState);
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
                    break;

                case INIT_PROGRAM:

                    Thread.sleep(Constants.THREAD_SLEEP_MS);

                    state = State.LOAD_RANKING;
                    
                    break;

                case INIT_SESSION_MENU:
                    updateView(View.INIT_SESSION_VIEW);

                    try
                    {
                        returnState = PresentationController.getReturnState();
                        state = Translate.int2StateInitSessionMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
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
                    PresentationController.clearThreadHasFinished();
                    showLoadedGames(savedGames);

                    try
                    {
                        returnState = PresentationController.getReturnState();
                        //returnState = oldPresentationController.loadGameMenu(savedGames);

                        gameId = Translate.int2SavedGameId(savedGames, returnState);
                        state = Translate.int2StateLoadGameMenu(returnState);
                    }
                    catch (IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
                    break;

                case LOAD_RANKING:
                    try
                    {
                        loadRanking();
                    }
                    catch (IOException | ClassNotFoundException e)
                    {
                        oldPresentationController.rankingLoadError();
                    }
                    finally
                    {
                        state = State.INIT_SESSION_MENU;
                    }
                    break;

                case LOAD_SAVED_GAMES_LIST:
                    try
                    {
                        loadSavedGamesList();
                        state = State.LOAD_GAME_MENU;
                    }
                    catch (FileNotFoundException e)
                    {
                        oldPresentationController.savedGamesListNotExistError();
                        state = State.MAIN_MENU;
                    }
                    catch (IOException e)
                    {
                        oldPresentationController.savedGamesListLoadError();
                        state = State.MAIN_MENU;
                    }
                    break;

                case LOG_IN_MENU:
                    updateView(View.LOG_IN_VIEW);

                    try
                    {
                        returnState = PresentationController.getReturnState();

                        state = Translate.intToStateLogInMenu(returnState);
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
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
                    popUpView(View.LOG_OUT_WARNING_VIEW);

                    try
                    {
                        returnState = PresentationController.getReturnState();

                        state = Translate.int2StateLogOutWarning(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
                    break;

                case MAIN_MENU:
                    updateView(View.MAIN_MENU_VIEW);

                    try
                    {
                        returnState = PresentationController.getReturnState();

                        state = Translate.int2StateMainMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
                    break;

                case NEW_GAME:
                    newGame(mode, role, difficulty);
                    state = State.RENDER_BOARD;
                    break;

                case NEW_GAME_MENU:
                    updateView(View.NEW_GAME_VIEW);

                    try
                    {
                        returnState = PresentationController.getMode();
                        mode = Translate.int2Mode(returnState);

                        returnState = PresentationController.getRole();
                        role = Translate.int2Role(returnState);

                        returnState = PresentationController.getDifficulty();
                        difficulty = Translate.int2Difficulty(returnState);

                        returnState = PresentationController.getReturnState();
                        state = Translate.int2StateGameSettingsMenu(returnState);
                    }
                    catch (IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
                    break;

                case PLAY_CODE_BREAKER:
                    try
                    {

                        if(hasToPrintBoard(Role.CODE_BREAKER))
                        {
                            oldPresentationController.printBoard(Role.CODE_BREAKER);
                            renderLastTurnBlocking();
                        }
                        else
                        {
                            renderLastTurn();
                        }

                        playCodeBreaker();

                        state = State.PLAY_CODE_MAKER;
                    }
                    catch (IllegalActionException e)
                    {
                        oldPresentationController.illegalActionError(e.getMessage());
                    }
                    catch (ReservedKeywordException e)
                    {
                        state = State.GAME_PAUSE_MENU;
                    }
                    catch (IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
                    break;

                case PLAY_CODE_MAKER:
                    try
                    {

                        if(hasToPrintBoard(Role.CODE_MAKER))
                        {
                            oldPresentationController.printBoard(Role.CODE_MAKER);
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
                        oldPresentationController.illegalActionError(e.getMessage());
                    }
                    catch (ReservedKeywordException e)
                    {
                        state = State.GAME_PAUSE_MENU;
                    }
                    catch (IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
                    break;

                case PLAY_TURN:
                    boolean hasStarted = gameController.hasStarted();
                    boolean hasCodeToCorrect = boardController.hasCodeToCorrect();

                    state = Translate.booleanToStatePlayTurn(hasStarted, hasCodeToCorrect);

                    break;

                case RANKING_MENU:
                    updateView(View.RANKING_VIEW);
                    PresentationController.clearThreadHasFinished();
                    showRanking(ranking.getTopTen());

                    try
                    {
                        returnState = PresentationController.getReturnState();
                        //returnState = oldPresentationController.printRanking(ranking.getTopTen());
                        state = Translate.intToStateRankingMenu(returnState);
                    }
                    catch (IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }

                    break;

                default:
                    break;

                case REGISTER_MENU:
                    updateView(View.REGISTER_VIEW);

                    try
                    {
                        returnState = PresentationController.getReturnState();

                        state = Translate.intToStateRegisterMenu(returnState);
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
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
                    catch (WrongPasswordException e)
                    {
                        errorMessage(Constants.PASSWORDS_MUST_MATCH);
                        state = State.REGISTER_MENU;
                    }
                    catch(FileAlreadyExistsException e)
                    {
                        errorMessage(Constants.USERNAME_ALREADY_EXISTS);
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
                    PresentationController.clearThreadHasFinished();

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
                    popUpView(View.SAVE_GAME_VIEW);
                    try
                    {
                        saveGame();
                        renameGame(PresentationController.getGameId());
                        state = State.GAME_PAUSE_MENU;
                    }
                    catch (FileAlreadyExistsException e)
                    {
                        returnState = oldPresentationController.savedGameAlreadyExistsWarning();

                        try
                        {
                            switch (returnState)
                            {
                                case 0:
                                    state = State.GAME_PAUSE_MENU;
                                    break;
                                case 1:
                                    gamePersistence.delete(gameController.getId());
                                    break;
                                default:
                                    throw new IllegalArgumentException();
                            }
                        }
                        catch (IOException ei)
                        {
                            oldPresentationController.gameDeleteError();
                            state = State.GAME_PAUSE_MENU;
                        }
                        catch (IllegalArgumentException ei)
                        {
                            oldPresentationController.optionError();
                        }
                    }
                    catch(IOException e)
                    {
                        oldPresentationController.gameSaveError();
                        state = State.GAME_PAUSE_MENU;
                    }
                    finally
                    {
                        PresentationController.clearThreadHasFinished();
                    }
                    break;
            }
        }


    }
}
