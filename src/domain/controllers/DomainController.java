package domain.controllers;

import domain.classes.Turn;
import persistence.BoardPersistence;
import persistence.GamePersistence;
import persistence.PlayerPersistence;
import persistence.RankingPersistence;
import presentation.controllers.PresentationController;
import util.Pair;
import util.State;

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
        state = State.initProgram;

        presentationController = new PresentationController();

        boardController = new BoardController();
        gameController = new GameController();
        loggedPlayerController = new HumanController();
        playingPlayerControllers = new ArrayList<>();

        boardPersistence = new BoardPersistence();
        gamePersistence = new GamePersistence();
        playerPersistence = new PlayerPersistence();
        rankingPersistence = new RankingPersistence();
    }

    /* EXECUTE */

    public boolean logIn(String username, String password)
    {
        return loggedPlayerController.logIn(username, password);
    }

    public boolean logIn()
    {
        Pair<String, String> userInfo;

        userInfo = presentationController.logInMenu();
        return logIn(userInfo.first, userInfo.second);
    }

    public boolean registerUser(String username, String password)
    {
        return loggedPlayerController.registerUser(username, password);
    }

    public boolean registerUser()
    {
        Pair<String, String> userInfo;

        userInfo = presentationController.registerUserMenu();
        return registerUser(userInfo.first, userInfo.second);
    }

    //public void newGame(int gameMode, int difficulty)
    public void newGame()
    {
        // Initialize Game, Board, Player structures
        int gameMode, gameDifficulty;

        gameMode = presentationController.gameModeSelectionMenu();
        gameDifficulty = presentationController.gameDifficultySelectionMenu();

        //boardController.newBoard();
        //gameController.newGame();
    }

//    public void loadGame(String id)
    public void loadGame()
    {
        // Load persistence data structures
        //gamePersistence.load(id);
    }

    public boolean saveGame()
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

    public void exe()
    {
        int returnState;

        while(!state.equals(State.endProgram))
        {
            switch(state)
            {
                case initProgram:
                    state = State.initSession;

                    break;

                case initSession:
                    returnState = presentationController.initialMenu();

                    switch(returnState)
                    {
                        case 0:
                            state = State.endProgram;
                            break;
                        case 1:
                            state = State.registerUser;
                            break;
                        case 2:
                            state = State.logInUser;
                            break;
                        default:
                            break;
                    }

                    break;

                case registerUser:
                    registerUser();
                    state = State.gameSelection;

                    break;

                case logInUser:
                    logIn();
                    state = State.gameSelection;

                    break;

                case gameSelection:
                    returnState = presentationController.gameSelectionMenu();

                    switch(returnState)
                    {
                        case 0:
                            state = State.initSession;
                            break;
                        case 1:
                            state = State.newGame;
                            break;
                        case 2:
                            state = State.loadGame;
                            break;
                        case 3:
                            state = State.checkRanking;
                            break;
                        case 4:
                            state = State.checkInfo;
                            break;
                        default:
                            break;
                    }

                    break;

                case newGame:
                    newGame();
                    state = State.playSelection;

                    break;

                case loadGame:
                    loadGame();
                    state = State.playSelection;

                    break;

                case saveGameAndContinue:
                    saveGame();
                    state = State.playSelection;

                    break;

                case saveGameAndExit:
                    saveGame();
                    state = State.gameSelection;

                    break;

                case checkRanking:

                    break;

                case checkInfo:

                    break;

                case playSelection:
                    returnState = presentationController.inGameMenu();

                    switch(returnState)
                    {
                        case 0:
                            break;
                        case 1:
                            state = State.playTurn;
                            break;
                        case 2:
                            state = State.gamePause;
                            break;
                        default:
                            break;
                    }

                    break;

                case playTurn:
                    playTurn();
                    state = State.playSelection;

                    break;

                case gamePause:
                    returnState = presentationController.pauseMenu();

                    switch(returnState)
                    {
                        case 0:
                            state = State.playSelection;
                            break;
                        case 1:
                            state = State.saveGameAndContinue;
                            break;
                        case 2:
                            state = State.saveGameAndExit;
                            break;
                        case 3:
                            state = State.exitGameWithoutSaving;
                            break;
                        case 4:
                            state = State.askForClue;
                            break;
                        default:
                            break;
                    }

                    break;

                case askForClue:
                    giveClue();
                    state = State.playSelection;

                    break;

                case exitGameWithoutSaving:
                    state = State.gameSelection;

                    break;

                case endProgram:

                    break;

                default:

                    break;
            }
        }


    }
}
