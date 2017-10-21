package domain.controllers;

import domain.classes.Turn;
import persistence.BoardPersistence;
import persistence.GamePersistence;
import persistence.PlayerPersistence;
import persistence.RankingPersistence;
import presentation.controllers.PresentationController;
import util.Pair;
import util.State;

public class DomainController
{
    private State state;

    private PresentationController presentationController;

    private BoardController boardController;
    private PlayerController playerController1;
    private PlayerController playerController2;

    private BoardPersistence boardPersistence;
    private GamePersistence gamePersistence;
    private PlayerPersistence playerPersistence;
    private RankingPersistence rankingPersistence;

    public DomainController()
    {
        state = State.initProgram;

        presentationController = new PresentationController();

        playerController1 = new HumanController();
        playerController2 = new CPUController();
        boardController = new BoardController();

        boardPersistence = new BoardPersistence();
        gamePersistence = new GamePersistence();
        playerPersistence = new PlayerPersistence();
        rankingPersistence = new RankingPersistence();
    }

    /* EXECUTE */

    public boolean registerUser(String username, String password)
    {
        return true;
    }

    public boolean logIn(String username, String password)
    {
        return playerController1.logIn(username, password);
    }

    //public void newGame(int gameMode, int difficulty)
    public void newGame()
    {
        // Initialize Game, Board, Player structures
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
        Pair<String, String> userInfo;
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
                    userInfo = presentationController.registerUserMenu();
                    registerUser(userInfo.first, userInfo.second);
                    state = State.gameSelection;

                    break;

                case logInUser:
                    userInfo = presentationController.logInMenu();
                    logIn(userInfo.first, userInfo.second);
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
