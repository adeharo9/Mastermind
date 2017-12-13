package domain.controllers;

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
import runnables.UpdateViewRunnable;
import util.Constants;
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

    private void registerUser(final String username, final String password) throws IOException
    {
        boolean b = playerPersistence.exists(username);
        if(b) throw new FileAlreadyExistsException("");

        Player player = loggedPlayerController.newHuman(username, password);

        playerPersistence.save(player);
    }

    private void newGame(final Mode mode, Role role, final Difficulty difficulty)
    {
        loggedPlayerController.restart();
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

    private void play(PlayerController playerController) throws IllegalArgumentException, ReservedKeywordException, IllegalActionException
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

    private void playCodeMaker() throws IllegalArgumentException, ReservedKeywordException, IllegalActionException
    {
        play(codeMakerController);
        if(!boardController.isFirstTurn()) gameController.pointsEndTurn();
    }

    private void playCodeBreaker() throws IllegalArgumentException, ReservedKeywordException, IllegalActionException
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
    }

    /* USER INTERACTION METHODS */

    private void updateBoard()
    {
        Turn lastTurn = boardController.getLastTurn();

        if(lastTurn == null)
        {
            Code solution = boardController.getSolution();
            oldPresentationController.setSolution(solution.getCodePins());
        }
        else
        {
            Code code = lastTurn.getCode();
            Code correction = lastTurn.getCorrectionCode();

            if(correction.getCodePins().isEmpty())
            {
                oldPresentationController.addCode(code.getCodePins());
            }
            else
            {
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

    private void updateView(View view)
    {
        Platform.runLater(new UpdateViewRunnable(presentationController, view.getViewFile()));
    }

    /* MAIN STATE MACHINE */

    public synchronized void exe() throws InterruptedException
    {
        int returnState;
        String gameId = null;
        String username = null;
        String password = null;

        Mode mode = null;
        Role role = null;
        Difficulty difficulty = null;

        while(!state.equals(State.CLOSE_PROGRAM))
        {
            switch(state)
            {
                case ASK_FOR_CLUE:
                    try
                    {
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
                    }
                    break;

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

                case CHECK_INFO:
                    oldPresentationController.showInfo();
                    state = State.MAIN_GAME_MENU;
                    break;

                case SHOW_RANKING:
                    try
                    {
                        returnState = oldPresentationController.printRanking(ranking.getTopTen());
                        state = Translate.intToStateShowRanking(returnState);
                    }
                    catch (IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }

                    break;

                case CLOSE_PROGRAM:
                    break;

                case CLOSE_PROGRAM_WARNING:
                    try
                    {
                        returnState = oldPresentationController.closeProgramWarning();
                        state = Translate.int2StateCloseProgramWarning(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    break;

                case CONTINUE_GAME:
                    state = State.PLAY_TURN;
                    break;

                case EXIT_GAME_WITHOUT_SAVING:
                    state = State.MAIN_GAME_MENU;
                    break;

                case EXIT_GAME_WITHOUT_SAVING_WARNING:
                    try
                    {
                        returnState = oldPresentationController.exitGameWarning();
                        state = Translate.int2StateExitGameWarning(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    break;

                case GAME_DIFFICULTY_SELECTION_MENU:
                    try
                    {
                        returnState = oldPresentationController.gameDifficultySelectionMenu();

                        difficulty = Translate.int2Difficulty(returnState);

                        if(mode == Mode.CPU_VS_CPU)
                        {
                            state = Translate.int2StateGameDifficultySelectionMenuCPU(returnState);
                        }
                        else
                        {
                            state = Translate.int2StateGameDifficultySelectionMenuHuman(returnState);
                        }
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    break;

                case GAME_MODE_SELECTION_MENU:
                    try
                    {
                        returnState = oldPresentationController.gameModeSelectionMenu();

                        mode = Translate.int2Mode(returnState);
                        state = Translate.int2StateGameModeSelectionMenu(returnState);
                    }
                    catch (IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
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
                    try
                    {
                        returnState = oldPresentationController.pauseMenu();
                        state = Translate.int2StateGamePauseMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    break;

                case GAME_ROLE_SELECTION_MENU:
                    try
                    {
                        returnState = oldPresentationController.gameRoleSelectionMenu();

                        role = Translate.int2Role(returnState);
                        state = Translate.int2StateGameRoleSelectionMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
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

                case INIT_PROGRAM:

                    state = State.LOAD_RANKING;

                    updateView(View.INIT_SESSION_VIEW);
                    
                    break;

                case INIT_SESSION_MENU:
                    wait();
                    try
                    {
                        returnState = PresentationController.getReturnState();
                        //returnState = oldPresentationController.initSessionMenu();
                        state = Translate.int2StateInitSessionMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    break;

                case LOAD_GAME:
                    try
                    {
                        loadGame(gameId);
                        state = State.CHECK_GAME_HAS_FINISHED;
                    }
                    catch(FileNotFoundException e)
                    {
                        oldPresentationController.gameNotExistError(gameId);
                        state = State.LOAD_GAME_MENU;
                    }
                    catch (IOException | ClassNotFoundException e)
                    {
                        oldPresentationController.gameLoadError();
                        state = State.LOAD_GAME_MENU;
                    }
                    break;

                case LOAD_GAME_MENU:
                    try
                    {
                        returnState = oldPresentationController.loadGameMenu(savedGames);

                        gameId = Translate.int2SavedGameId(savedGames, returnState);
                        state = Translate.int2StateLoadGameMenu(returnState);
                    }
                    catch (IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
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
                        state = State.MAIN_GAME_MENU;
                    }
                    catch (IOException e)
                    {
                        oldPresentationController.savedGamesListLoadError();
                        state = State.MAIN_GAME_MENU;
                    }
                    break;

                case LOG_IN_GET_USERNAME_MENU:
                    try
                    {
                        username = oldPresentationController.getUsername();
                        state = State.LOG_IN_GET_PASSWORD_MENU;
                    }
                    catch (ReservedKeywordException e)
                    {
                        state = State.INIT_SESSION_MENU;
                    }
                    break;

                case LOG_IN_GET_PASSWORD_MENU:
                    try
                    {
                        password = oldPresentationController.getPassword();
                        state = State.LOG_IN_USER;
                    }
                    catch (ReservedKeywordException e)
                    {
                        state = State.LOG_IN_GET_USERNAME_MENU;
                    }
                    break;

                case LOG_IN_USER:
                    try
                    {
                        logInUser(username, password);
                        state = State.MAIN_GAME_MENU;
                    }
                    catch(FileNotFoundException | WrongPasswordException e)
                    {
                        oldPresentationController.logInError();
                        state = State.LOG_IN_GET_USERNAME_MENU;
                    }
                    catch(IOException | ClassNotFoundException e)
                    {
                        oldPresentationController.playerLoadError();
                        state = State.LOG_IN_GET_USERNAME_MENU;
                    }
                    break;

                case LOG_OUT_WARNING:
                    try
                    {
                        returnState = oldPresentationController.logOutWarning();
                        state = Translate.int2StateLogOutWarning(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    break;

                case MAIN_GAME_MENU:
                    try
                    {
                        returnState = oldPresentationController.mainGameMenu();
                        state = Translate.int2StateMainGameMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        oldPresentationController.optionError();
                    }
                    break;

                case NEW_GAME:
                    newGame(mode, role, difficulty);
                    state = State.CHECK_GAME_HAS_FINISHED;
                    break;

                case PLAY_CODE_BREAKER:
                    try
                    {
                        if(hasToPrintBoard(Role.CODE_BREAKER))
                        {
                            oldPresentationController.printBoard(Role.CODE_BREAKER);
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
                    break;

                case PLAY_CODE_MAKER:
                    try
                    {
                        if(hasToPrintBoard(Role.CODE_MAKER))
                        {
                            oldPresentationController.printBoard(Role.CODE_MAKER);
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
                    break;

                case PLAY_TURN:
                    boolean hasStarted = gameController.hasStarted();
                    boolean hasCodeToCorrect = boardController.hasCodeToCorrect();

                    state = Translate.booleanToStatePlayTurn(hasStarted, hasCodeToCorrect);

                    break;

                case REGISTER_GET_USERNAME_MENU:
                    try
                    {
                        username = oldPresentationController.getUsername();
                        state = State.REGISTER_GET_PASSWORD_MENU;
                    }
                    catch (ReservedKeywordException e)
                    {
                        state = State.INIT_SESSION_MENU;
                    }
                    break;

                case REGISTER_GET_PASSWORD_MENU:
                    try
                    {
                        password = oldPresentationController.getPassword();
                        state = State.REGISTER_USER;
                    }
                    catch (ReservedKeywordException e)
                    {
                        state = State.REGISTER_GET_USERNAME_MENU;
                    }
                    break;

                case REGISTER_USER:
                    try
                    {
                        registerUser(username, password);
                        state = State.MAIN_GAME_MENU;
                    }
                    catch(FileAlreadyExistsException e)
                    {
                        oldPresentationController.playerAlreadyExistsError(username);
                        state = State.REGISTER_GET_USERNAME_MENU;
                    }
                    catch(IOException e)
                    {
                        oldPresentationController.registerError();
                        state = State.REGISTER_GET_USERNAME_MENU;
                    }
                    break;

                case RESTART_GAME:
                    mode = gameController.getMode();
                    role = loggedPlayerController.getRole();
                    difficulty = boardController.getDifficulty();
                    state = State.NEW_GAME;
                    break;

                case SAVE_GAME_AND_CONTINUE:
                    try
                    {
                        saveGame();
                        state = State.PLAY_TURN;
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
                    break;

                case SAVE_GAME_AND_EXIT:
                    try
                    {
                        saveGame();
                        state = State.MAIN_GAME_MENU;
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
                        catch (IOException io)
                        {
                            oldPresentationController.gameDeleteError();
                            state = State.GAME_PAUSE_MENU;
                        }
                        catch (IllegalArgumentException il)
                        {
                            oldPresentationController.optionError();
                        }
                    }
                    catch(IOException e)
                    {
                        oldPresentationController.gameSaveError();
                        state = State.GAME_PAUSE_MENU;
                    }
                    break;

                default:
                    break;
            }
        }


    }
}
