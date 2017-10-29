package domain.controllers;

import domain.classes.*;
import exceptions.IntegrityCorruption;
import exceptions.WrongPassword;
//import persistence.BoardPersistence;
import persistence.GamePersistence;
import persistence.PlayerPersistence;
//import persistence.RankingPersistence;
import presentation.controllers.PresentationController;
import util.*;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class DomainController
{
    private State state;
    private ArrayList<String> savedGames;

    private PresentationController presentationController;

    private BoardController boardController;
    private GameController gameController;
    private PlayerController loggedPlayerController;
    private ArrayList<PlayerController> playingPlayerControllers;

    //private BoardPersistence boardPersistence;
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
        loggedPlayerController = new HumanController();
        playingPlayerControllers = new ArrayList<>();

        //boardPersistence = new BoardPersistence();
        gamePersistence = new GamePersistence();
        playerPersistence = new PlayerPersistence();
        //rankingPersistence = new RankingPersistence();
    }

    /* EXECUTE */

    private void logInUser(Pair<String, String> userInfo) throws IntegrityCorruption, IOException, WrongPassword, ClassNotFoundException
    {
        Player player = playerPersistence.load(userInfo.first);
        PlayerController playerController = new HumanController(player);

        boolean b = ((HumanController) playerController).checkPassword(userInfo.second);
        if (!b) throw new WrongPassword();

        loggedPlayerController = playerController;
    }

    private void registerUser(Pair<String, String> userInfo) throws IOException
    {
        boolean b = playerPersistence.exists(userInfo.first);
        if(b) throw new FileAlreadyExistsException("");

        Player player = ((HumanController)loggedPlayerController).newPlayer(userInfo.first, userInfo.second);

        playerPersistence.save(player);
    }

    private void newGame(Mode mode, Role role, Difficulty difficulty)
    {
        ArrayList<Pair<Player, Role>> playerRolePairs = new ArrayList<>();

        PlayerController playerController1 = null;
        PlayerController playerController2 = null;

        switch(mode)
        {
            case HUMAN_VS_HUMAN:
                playerController1 = loggedPlayerController;
                playerController2 = new HumanController();
                break;

            case HUMAN_VS_CPU:
                playerController1 = loggedPlayerController;
                playerController2 = new CPUController();
                break;

            case CPU_VS_CPU:
                role = Role.autoRole();

                playerController1 = new CPUController();
                playerController2 = new CPUController();

                playerController1.newPlayer(Utils.autoID());
                break;

            default:
                break;
        }

        playerController2.newPlayer(Utils.autoID());

        playingPlayerControllers.add(playerController1);
        playingPlayerControllers.add(playerController2);

        playerRolePairs.add(new Pair<>(playerController1.getPlayer(), role));
        playerRolePairs.add(new Pair<>(playerController2.getPlayer(), Role.complementaryRole(role)));

        Board board = boardController.newBoard(difficulty);

        gameController.newGame(Utils.autoID(), difficulty, board, playerRolePairs);
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
    }

    private void saveGame() throws IOException
    {
        Game game = gameController.getGame();
        gamePersistence.save(game);

        String gameId = game.getId();
        String playerId = loggedPlayerController.getPlayer().getId();

        playerPersistence.savePlayerGame(gameId, playerId);
    }

    private void playTurn()
    {
        Action action;

        for(PlayerController playerController : playingPlayerControllers)
        {
            action = playerController.play();
        }
    }

    private boolean checkTurn(Turn turn)
    {
        return true;
    }

    private void giveClue()
    {

    }

    public void exe() throws IntegrityCorruption
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
                case INIT_PROGRAM:
                    state = State.INIT_SESSION_MENU;

                    break;

                case INIT_SESSION_MENU:
                    try
                    {
                        returnState = presentationController.initSessionMenu();
                        state = Translate.int2StateInitSession(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }

                    break;

                case REGISTER_USER_MENU:
                    try
                    {
                        userInfo = presentationController.registerMenu();
                        state = State.REGISTER_USER;
                    }
                    catch (NumberFormatException e)
                    {
                        presentationController.optionError();
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
                        state = State.REGISTER_USER_MENU;
                    }

                    break;

                case LOG_IN_USER_MENU:
                    try
                    {
                        userInfo = presentationController.logInMenu();
                        state = State.LOG_IN_USER;
                    }
                    catch (NumberFormatException e)
                    {
                        presentationController.optionError();
                    }
                    break;

                case LOG_IN_USER:
                    try
                    {
                        logInUser(userInfo);
                        state = State.MAIN_GAME_MENU;
                    }
                    catch(IOException | ClassNotFoundException | WrongPassword e)
                    {
                        presentationController.logInError();
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

                case CHECK_RANKING:

                    break;

                case CHECK_INFO:

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

                case NEW_GAME:
                    newGame(mode, role, difficulty);
                    state = State.IN_GAME_MENU;

                    break;

                case LOAD_SAVED_GAMES_LIST:
                    try
                    {
                        loadSavedGamesList();
                        state = State.LOAD_GAME_MENU;
                    }
                    catch (IOException e)
                    {
                        presentationController.savedGamesListLoadError();
                        state = State.MAIN_GAME_MENU;
                    }

                    break;

                case LOAD_GAME_MENU:
                    returnState = presentationController.loadGameMenu(savedGames);

                    str = Translate.int2SavedGameId(savedGames, returnState);
                    state = Translate.int2StateLoadGameMenu(returnState);

                    break;

                case LOAD_GAME:
                    try
                    {
                        loadGame(str);
                        state = State.IN_GAME_MENU;
                    }
                    catch (IOException | ClassNotFoundException e)
                    {
                        presentationController.gameLoadError();
                    }

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
                    catch(IOException e)
                    {
                        presentationController.gameSaveError();
                        state = State.GAME_PAUSE_MENU;
                    }

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

                case EXIT_GAME_WITHOUT_SAVING:
                    state = State.MAIN_GAME_MENU;

                    break;

                case CONTINUE_GAME:
                    state = State.IN_GAME_MENU;

                    break;

                case IN_GAME_MENU:
                    try
                    {
                        returnState = presentationController.inGameMenu();
                        state = Translate.int2StatePlaySelection(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }

                    break;

                case PLAY_TURN:
                    playTurn();
                    state = State.IN_GAME_MENU;

                    break;

                case GAME_PAUSE_MENU:
                    try
                    {
                        returnState = presentationController.pauseMenu();
                        state = Translate.int2StateGamePause(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.optionError();
                    }

                    break;

                case ASK_FOR_CLUE:
                    giveClue();
                    state = State.IN_GAME_MENU;

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

                case CLOSE_PROGRAM:

                    break;

                default:

                    break;
            }
        }


    }
}
