package domain.controllers;

import domain.classes.*;
import exceptions.RollbackException;
import exceptions.IntegrityCorruption;
import exceptions.WrongPassword;
import persistence.BoardPersistence;
import persistence.GamePersistence;
import persistence.PlayerPersistence;
import persistence.RankingPersistence;
import presentation.controllers.PresentationController;
import util.*;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class DomainController
{
    private State state;

    private PresentationController presentationController;

    private BoardController boardController;
    private GameController gameController;
    private PlayerController loggedPlayerController;
    private ArrayList<PlayerController> playingPlayerControllers;

    private BoardPersistence boardPersistence;
    private GamePersistence gamePersistence;
    private PlayerPersistence playerPersistence;
    private RankingPersistence rankingPersistence;

    public DomainController()
    {
        state = State.INIT_PROGRAM;

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

    public void logIn() throws IntegrityCorruption, IOException, WrongPassword, ClassNotFoundException
    {
        try
        {
            Pair<String, String> userInfo = presentationController.logInMenu();

            Player player = playerPersistence.load(userInfo.first);

            boolean b = player.isValid();
            if (!b) throw new IntegrityCorruption();

            b = ((Human) player).checkPassword(userInfo.second);
            if (!b) throw new WrongPassword();
        }
        catch(NumberFormatException e)
        {
            presentationController.wrongOption();
        }
    }

    public void registerUser(Pair<String, String> userInfo) throws FileAlreadyExistsException
    {
        boolean b = playerPersistence.exists(userInfo.first);
        if(b) throw new FileAlreadyExistsException("");

        Player player = loggedPlayerController.newPlayer(userInfo.first);
        try
        {
            playerPersistence.save(player);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void newGame(Role role, Difficulty difficulty)
    {
        ArrayList<Pair<Player, Role>> playerRolePairs = new ArrayList<>();
        playingPlayerControllers.add(loggedPlayerController);
        playerRolePairs.add(new Pair<>(loggedPlayerController.getPlayer(), role));

        PlayerController playerController = new CPUController();
        Player player = playerController.newPlayer(Utils.autoID());
        playingPlayerControllers.add(playerController);

        switch(role)
        {
            case CODE_MAKER:
                playerRolePairs.add(new Pair<>(player, Role.CODE_BREAKER));
                break;

            case CODE_BREAKER:
                playerRolePairs.add(new Pair<>(player, Role.CODE_MAKER));
                break;

            case watcher:
                PlayerController playerController1 = new CPUController();
                Player player1 = playerController1.newPlayer(Utils.autoID());

                playingPlayerControllers.add(playerController1);
                playerRolePairs.add(new Pair<>(player, Role.CODE_MAKER));
                playerRolePairs.add(new Pair<>(player1, Role.CODE_BREAKER));
                break;

            default:
                break;
        }

        Board board = boardController.newBoard(difficulty);

        Game game = gameController.newGame(Utils.autoID(), difficulty, board, playerRolePairs);
    }

    public void loadGame(String id)
    {
        // Load persistence data structures
        //gamePersistence.load(id);
    }

    public boolean saveGame(String id)
    {
        return true;
    }

    /*public boolean endGame()
    {
        return true;
    }*/

    public void playTurn()
    {

    }

    public boolean checkTurn(Turn turn)
    {
        return true;
    }

    public void giveClue()
    {

    }

    public void exe() throws IntegrityCorruption
    {
        int returnState;
        Role role = null;
        Difficulty difficulty = null;
        Pair<String, String> userInfo = null;

        while(!state.equals(State.END_PROGRAM))
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
                        presentationController.wrongOption();
                    }

                    break;

                case REGISTER_USER_MENU:
                    try
                    {
                        userInfo = presentationController.registerUserMenu();
                        state = State.REGISTER_USER;
                    }
                    catch (NumberFormatException e)
                    {
                        presentationController.wrongOption();
                    }

                    break;

                case REGISTER_USER:
                    try
                    {
                        registerUser(userInfo);
                        state = State.MAIN_GAME_MENU;
                    }
                    catch(FileAlreadyExistsException e)
                    {

                    }

                    break;

                case LOG_IN_USER_MENU:
                    state = State.LOG_IN_USER;
                    break;

                case LOG_IN_USER:
                    try
                    {
                        logIn();
                        state = State.MAIN_GAME_MENU;
                    }
                    catch(IOException | ClassNotFoundException | WrongPassword e)
                    {
                        e.printStackTrace();
                    } //Momentáneo

                    break;

                case MAIN_GAME_MENU:
                    try
                    {
                        returnState = presentationController.mainGameMenu();
                        state = Translate.int2StateMainGameMenu(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.wrongOption();
                    }

                    break;

                case CHECK_RANKING:

                    break;

                case CHECK_INFO:

                    break;

                case GAME_MODE_SELECTION_MENU:
                    state = State.GAME_ROLE_SELECTION_MENU;
                    break;

                case GAME_ROLE_SELECTION_MENU:
                    returnState = presentationController.gameRoleSelectionMenu();

                    try
                    {
                        role = Translate.int2Role(returnState);

                        state = State.GAME_DIFFICULTY_SELECTION_MENU;
                    }
                    catch(RollbackException e)
                    {
                        state = State.MAIN_GAME_MENU;
                    }
                    catch(NumberFormatException e)
                    {
                        presentationController.wrongOption();
                    }

                    break;

                case GAME_DIFFICULTY_SELECTION_MENU:
                    returnState = presentationController.gameDifficultySelectionMenu();

                    try
                    {
                        difficulty = Translate.int2Difficulty(returnState);

                        state = State.NEW_GAME;
                    }
                    catch(RollbackException e)
                    {
                        state = State.GAME_ROLE_SELECTION_MENU;
                    }
                    catch(NumberFormatException e)
                    {
                        presentationController.wrongOption();
                    }

                    break;

                case NEW_GAME:
                    newGame(role, difficulty);
                    state = State.IN_GAME_MENU;

                    break;

                case LOAD_GAME_MENU:
                    loadGame("");
                    state = State.IN_GAME_MENU;

                    break;

                case SAVE_GAME_AND_CONTINUE:
                    saveGame("");
                    state = State.IN_GAME_MENU;

                    break;

                case SAVE_GAME_AND_EXIT:
                    saveGame("");
                    state = State.MAIN_GAME_MENU;

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
                        presentationController.wrongOption();
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
                        presentationController.wrongOption();
                    }

                    break;

                case ASK_FOR_CLUE:
                    giveClue();
                    state = State.IN_GAME_MENU;

                    break;

                case END_PROGRAM:

                    break;

                default:

                    break;
            }
        }


    }
}
