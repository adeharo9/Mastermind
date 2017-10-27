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
        state = State.initProgram;

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

        while(!state.equals(State.endProgram))
        {
            switch(state)
            {
                case initProgram:
                    state = State.initSession;

                    break;

                case initSession:
                    try
                    {
                        returnState = presentationController.initialMenu();
                        state = Translate.int2StateInitSession(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.wrongOption();
                    }

                    break;

                case registerUserInput:
                    try
                    {
                        userInfo = presentationController.registerUserMenu();
                        state = State.registerUser;
                    }
                    catch (NumberFormatException e)
                    {
                        presentationController.wrongOption();
                    }

                    break;

                case registerUser:
                    try
                    {
                        registerUser(userInfo);
                        state = State.gameInProgressSelection;
                    }
                    catch(FileAlreadyExistsException e)
                    {

                    }

                    break;

                case logInUser:
                    try
                    {
                        logIn();
                        state = State.gameInProgressSelection;
                    }
                    catch(IOException | ClassNotFoundException | WrongPassword e)
                    {
                        e.printStackTrace();
                    } //Momentáneo

                    break;

                case gameInProgressSelection:
                    try
                    {
                        returnState = presentationController.gameSelectionMenu();
                        state = Translate.int2StateGameSelection(returnState);
                    }
                    catch(IllegalArgumentException e)
                    {
                        presentationController.wrongOption();
                    }

                    break;

                case gameDifficultySelection:
                    returnState = presentationController.gameDifficultySelectionMenu();

                    try
                    {
                        difficulty = Translate.int2Difficulty(returnState);

                        state = State.newGame;
                    }
                    catch(RollbackException e)
                    {
                        state = State.gameModeSelection;
                    }
                    catch(NumberFormatException e)
                    {
                        presentationController.wrongOption();
                    }

                    break;

                case gameModeSelection:
                    returnState = presentationController.gameModeSelectionMenu();

                    try
                    {
                        role = Translate.int2Role(returnState);

                        state = State.gameDifficultySelection;
                    }
                    catch(RollbackException e)
                    {
                        state = State.gameInProgressSelection;
                    }
                    catch(NumberFormatException e)
                    {
                        presentationController.wrongOption();
                    }

                    break;

                case newGame:
                    newGame(role, difficulty);
                    state = State.playSelection;

                    break;

                case loadGame:
                    loadGame("");
                    state = State.playSelection;

                    break;

                case saveGameAndContinue:
                    saveGame("");
                    state = State.playSelection;

                    break;

                case saveGameAndExit:
                    saveGame("");
                    state = State.gameInProgressSelection;

                    break;

                case checkRanking:

                    break;

                case checkInfo:

                    break;

                case continueGame:
                    state = State.playSelection;

                    break;

                case playSelection:
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

                case playTurn:
                    playTurn();
                    state = State.playSelection;

                    break;

                case gamePause:
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

                case askForClue:
                    giveClue();
                    state = State.playSelection;

                    break;

                case exitGameWithoutSaving:
                    state = State.gameInProgressSelection;

                    break;

                case endProgram:

                    break;

                default:

                    break;
            }
        }


    }
}
