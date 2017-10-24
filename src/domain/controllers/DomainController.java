package domain.controllers;

import domain.classes.*;
import exceptions.IntegrityCorruption;
import exceptions.FileAlreadyExists;
import exceptions.WrongPassword;
import exceptions.FileDoesNotExist;
import persistence.BoardPersistence;
import persistence.GamePersistence;
import persistence.PlayerPersistence;
import persistence.RankingPersistence;
import presentation.controllers.PresentationController;
import util.*;

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

    public void logIn() throws IntegrityCorruption, FileDoesNotExist, WrongPassword
    {
        Pair<String, String> userInfo = presentationController.logInMenu();

        Player player = playerPersistence.load(userInfo.first);

        boolean b = player.isValid();
        if (!b) throw new IntegrityCorruption();

        b = ((Human) player).checkPassword(userInfo.second);
        if (!b) throw new WrongPassword();
    }

    public void registerUser() throws FileAlreadyExists
    {
        Pair<String, String> userInfo = presentationController.registerUserMenu();

        boolean b = playerPersistence.exists(userInfo.first);
        if(b) throw new FileAlreadyExists();

        Player player = loggedPlayerController.newPlayer(Utils.autoID());
        playerPersistence.save(player);
    }

    public void newGame()
    {
        int gameMode = presentationController.gameModeSelectionMenu();
        Role role = Translate.int2Role(gameMode);

        int gameDifficulty = presentationController.gameDifficultySelectionMenu();
        Difficulty difficulty = Translate.int2Difficulty(gameDifficulty);

        ArrayList<Pair<Player, Role>> playerRolePairs = new ArrayList<>();
        playingPlayerControllers.add(loggedPlayerController);
        playerRolePairs.add(new Pair<>(loggedPlayerController.getPlayer(), role));

        PlayerController playerController = new CPUController();
        Player player = playerController.newPlayer(Utils.autoID());
        playingPlayerControllers.add(playerController);

        switch(role)
        {
            case codeMaker:
                playerRolePairs.add(new Pair<>(player, Role.codeBreaker));
                break;

            case codeBreaker:
                playerRolePairs.add(new Pair<>(player, Role.codeMaker));
                break;

            case watcher:
                PlayerController playerController1 = new CPUController();
                Player player1 = playerController1.newPlayer(Utils.autoID());

                playingPlayerControllers.add(playerController1);
                playerRolePairs.add(new Pair<>(player, Role.codeMaker));
                playerRolePairs.add(new Pair<>(player1, Role.codeBreaker));
                break;

            default:
                break;
        }

        Board board = boardController.newBoard(difficulty);

        Game game = gameController.newGame(Utils.autoID(), difficulty, board, playerRolePairs);
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

    public void exe() throws IntegrityCorruption
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
                    try
                    {
                        registerUser();
                        state = State.gameSelection;
                    }
                    catch(FileAlreadyExists e){}

                    break;

                case logInUser:
                    try
                    {
                        logIn();
                        state = State.gameSelection;
                    }
                    catch(FileDoesNotExist | WrongPassword e){}

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
