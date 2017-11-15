package domain.controllers;

import domain.classes.*;
import enums.*;
import exceptions.GameNotStartedException;
import exceptions.IntegrityCorruptionException;
import exceptions.ReservedKeywordException;
import exceptions.WrongPasswordException;
//import persistence.BoardPersistence;
import persistence.GamePersistence;
import persistence.PlayerPersistence;
//import persistence.RankingPersistence;
import presentation.controllers.PresentationController;
import util.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DomainController
{
    private State state;
    private List<String> savedGames;

    private final PresentationController presentationController;

    private BoardController boardController;
    private GameController gameController;
    private PlayerController loggedPlayerController;
    private PlayerController codeMakerController;
    private PlayerController codeBreakerController;

    private GamePersistence gamePersistence;
    private PlayerPersistence playerPersistence;
    //private RankingPersistence rankingPersistence;

    public DomainController()
    {
        state = State.INIT_PROGRAM;
        savedGames = new ArrayList<>();

        presentationController = new PresentationController();

        boardController = new BoardController();
        gameController = new GameController();
        loggedPlayerController = new PlayerController();

        gamePersistence = new GamePersistence();
        playerPersistence = new PlayerPersistence();
        //rankingPersistence = new RankingPersistence();
    }

    /* EXECUTE */

    private void logInUser(Pair<String, String> userInfo) throws IntegrityCorruptionException, IOException, WrongPasswordException, ClassNotFoundException
    {
        Player player = playerPersistence.load(userInfo.first);
        PlayerController playerController = new PlayerController(player);

        boolean b = playerController.checkPassword(userInfo.second);
        if (!b) throw new WrongPasswordException();

        loggedPlayerController = playerController;
    }

    private void registerUser(Pair<String, String> userInfo) throws IOException
    {
        boolean b = playerPersistence.exists(userInfo.first);
        if(b) throw new FileAlreadyExistsException("");

        Player player = loggedPlayerController.newHuman(userInfo.first, userInfo.second);

        playerPersistence.save(player);
    }

    private void newGame(Mode mode, Role role, Difficulty difficulty)
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

        gameController.newGame(Utils.autoID(), difficulty, board, players);
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

        for(Player player : players)
        {
            PlayerController playerController;

            String playerId = player.getId();

            if(playerId.equals(loggedPlayerController.getId()))
            {
                loggedPlayerController.setRole(player.getRole());
                player = loggedPlayerController.getPlayer();
                playerController = loggedPlayerController;
            }
            else
            {
                playerController = new PlayerController(player);
            }

            Role role = player.getRole();

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
    }

    private void saveGame() throws IOException
    {
        Game game = gameController.getGame();
        gamePersistence.save(game);

        String gameId = gameController.getId();
        String playerId = loggedPlayerController.getId();

        playerPersistence.savePlayerGame(gameId, playerId);
    }

    private void playTurn() throws IllegalArgumentException, ReservedKeywordException
    {
        Action action;
        Turn lastTurn;

        Code code = boardController.getSolution();
        Difficulty difficulty = boardController.getDifficulty();

        lastTurn = boardController.getLastTurn();

        action = codeMakerController.play(difficulty, lastTurn, code);

        if(action != null)
        {
            boardController.checkAction(action);
            boardController.addAction(action);

            printBoard(codeMakerController.getRole());
        }

        action = codeBreakerController.play(difficulty, lastTurn, code);

        if(action != null)
        {
            boardController.checkAction(action);
            boardController.addAction(action);

            printBoard(codeBreakerController.getRole());
        }
    }

    private boolean checkTurn(Turn turn)
    {
        return true;
    }

    private void giveClue() throws IllegalArgumentException, GameNotStartedException
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

        presentationController.showClue(type,String.valueOf(num),name);
    }

    /* USER INTERACTION METHODS */

    private void printBoard(Role role)
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

        presentationController.printBoard(role);
    }

    /* MAIN STATE MACHINE */

    public void exe() throws IntegrityCorruptionException, ReservedKeywordException
    {
        int returnState;
        String str = null;

        Mode mode = null;
        Role role = null;
        Difficulty difficulty = null;

        Pair<String, String> userInfo = null;

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
                        presentationController.gameNotStartedError();
                    }
                    finally
                    {
                        state = State.IN_GAME_MENU;
                    }
                    break;

                case CHECK_INFO:
                    presentationController.showInfo();
                    state = State.MAIN_GAME_MENU;
                    break;

                case CHECK_RANKING:
                    state = State.MAIN_GAME_MENU;
                    break;

                case CHECK_TURN_NUMBER:
                    boolean hasFinished = gameController.hasFinished();
                    state = Translate.booleanModeToStateCheckTurnNumber(hasFinished, mode);

                    break;

                case CLOSE_PROGRAM:
                    break;

                case CLOSE_PROGRAM_WARNING:
                    try
                    {
                        returnState = presentationController.closeProgramWarning();
                        state = Translate.int2StateCloseProgramWarning(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }
                    break;

                case CONTINUE_GAME:
                    state = State.IN_GAME_MENU;
                    break;

                case EXIT_GAME_WITHOUT_SAVING:
                    state = State.MAIN_GAME_MENU;
                    break;

                case EXIT_GAME_WITHOUT_SAVING_WARNING:
                    try
                    {
                        returnState = presentationController.exitGameWarning();
                        state = Translate.int2StateExitGameWarning(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }
                    break;

                case GAME_DIFFICULTY_SELECTION_MENU:
                    try
                    {
                        returnState = presentationController.gameDifficultySelectionMenu();

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
                        presentationController.optionError();
                    }
                    break;

                case GAME_MODE_SELECTION_MENU:
                    try
                    {
                        returnState = presentationController.gameModeSelectionMenu();

                        mode = Translate.int2Mode(returnState);
                        state = Translate.int2StateGameModeSelectionMenu(returnState);
                    }
                    catch (IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }
                    break;

                case GAME_OVER_MENU:
                    /*gameController.pointsEndGame();*/
                    returnState = presentationController.gameOverMenu(String.valueOf(gameController.getGame().getPoints()));

                    try
                    {
                        state = Translate.int2StateGameOverMenu(returnState);
                    }
                    catch (IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }

                    break;

                case GAME_PAUSE_MENU:
                    try
                    {
                        returnState = presentationController.pauseMenu();
                        state = Translate.int2StateGamePauseMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }
                    break;

                case GAME_ROLE_SELECTION_MENU:
                    try
                    {
                        returnState = presentationController.gameRoleSelectionMenu();

                        role = Translate.int2Role(returnState);
                        state = Translate.int2StateGameRoleSelectionMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }
                    break;

                case IN_GAME_MENU:
                    try
                    {
                        returnState = presentationController.inGameMenu();
                        state = Translate.int2StateInGameMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }
                    break;

                case INIT_PROGRAM:
                    state = State.INIT_SESSION_MENU;
                    break;

                case INIT_SESSION_MENU:
                    try
                    {
                        returnState = presentationController.initSessionMenu();
                        state = Translate.int2StateInitSessionMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }
                    break;

                case LOAD_GAME:
                    try
                    {
                        loadGame(str);
                        state = State.CHECK_TURN_NUMBER;
                    }
                    catch (IOException | ClassNotFoundException e)
                    {
                        presentationController.gameLoadError();
                        state = State.LOAD_GAME_MENU;
                    }
                    break;

                case LOAD_GAME_MENU:
                    returnState = presentationController.loadGameMenu(savedGames);

                    str = Translate.int2SavedGameId(savedGames, returnState);
                    state = Translate.int2StateLoadGameMenu(returnState);
                    break;

                case LOAD_SAVED_GAMES_LIST:
                    try
                    {
                        loadSavedGamesList();
                        state = State.LOAD_GAME_MENU;
                    }
                    catch (FileNotFoundException e)
                    {
                        presentationController.savedGamesListNotExistError();
                        state = State.MAIN_GAME_MENU;
                    }
                    catch (IOException e)
                    {
                        presentationController.savedGamesListLoadError();
                        state = State.MAIN_GAME_MENU;
                    }
                    break;

                case LOG_IN_USER:
                    try
                    {
                        logInUser(userInfo);
                        state = State.MAIN_GAME_MENU;
                    }
                    catch(IOException | ClassNotFoundException | WrongPasswordException e)
                    {
                        presentationController.logInError();
                        state = State.LOG_IN_USER_MENU;
                    }
                    break;

                case LOG_IN_USER_MENU:
                    try
                    {
                        userInfo = presentationController.logInMenu();
                        state = State.LOG_IN_USER;
                    }
                    catch (ReservedKeywordException e)
                    {
                        state = State.INIT_SESSION_MENU;
                    }

                    break;

                case LOG_OUT_WARNING:
                    try
                    {
                        returnState = presentationController.logOutWarning();
                        state = Translate.int2StateLogOutWarning(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }
                    break;

                case MAIN_GAME_MENU:
                    try
                    {
                        returnState = presentationController.mainGameMenu();
                        state = Translate.int2StateMainGameMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }
                    break;

                case NEW_GAME:
                    newGame(mode, role, difficulty);
                    state = State.CHECK_TURN_NUMBER;
                    break;

                case PLAY_TURN:
                    try
                    {
                        playTurn();
                        gameController.pointsEndTurn();
                        state = State.CHECK_TURN_NUMBER;
                    }
                    catch (ReservedKeywordException e)
                    {
                        state = State.IN_GAME_MENU;
                    }
                    catch (IllegalArgumentException e)
                    {
                        presentationController.optionError();
                        state = State.IN_GAME_MENU;
                    }
                    break;

                case REGISTER_USER:
                    try
                    {
                        registerUser(userInfo);
                        state = State.MAIN_GAME_MENU;
                    }
                    catch(IOException e)
                    {
                        presentationController.registerError();
                        state = State.INIT_SESSION_MENU;
                    }
                    break;

                case REGISTER_USER_MENU:
                    try
                    {
                        userInfo = presentationController.registerMenu();
                        state = State.REGISTER_USER;
                    }
                    catch(ReservedKeywordException e)
                    {
                        state = State.INIT_SESSION_MENU;
                    }

                    break;

                case RESTART_GAME:
                    state = State.NEW_GAME;
                    break;

                case SAVE_GAME_AND_CONTINUE:
                    try
                    {
                        saveGame();
                        state = State.IN_GAME_MENU;
                    }
                    catch(IOException e)
                    {
                        presentationController.gameSaveError();
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
                        ioUtils.printOutLn("Hola");
                    }
                    catch(IOException e)
                    {
                        presentationController.gameSaveError();
                        state = State.GAME_PAUSE_MENU;
                    }
                    break;

                default:
                    break;
            }
        }


    }
}
