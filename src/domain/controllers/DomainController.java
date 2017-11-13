package domain.controllers;

import domain.classes.*;
import enums.*;
import exceptions.IntegrityCorruption;
import exceptions.ReservedKeywordException;
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
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DomainController
{
    private State state;
    private ArrayList<String> savedGames;

    private final PresentationController presentationController;

    private BoardController boardController;
    private GameController gameController;
    private PlayerController loggedPlayerController;
    private ArrayList<PlayerController> playingPlayerControllers;

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
        loggedPlayerController = new HumanController(this);
        playingPlayerControllers = new ArrayList<>();

        gamePersistence = new GamePersistence();
        playerPersistence = new PlayerPersistence();
        //rankingPersistence = new RankingPersistence();
    }

    /* EXECUTE */

    private void logInUser(Pair<String, String> userInfo) throws IntegrityCorruption, IOException, WrongPassword, ClassNotFoundException
    {
        Player player = playerPersistence.load(userInfo.first);
        PlayerController playerController = new HumanController(this, player);

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
                playerController2 = new HumanController(this);
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

        playerController1.setRole(role);
        playerController2.setRole(Role.complementaryRole(role));

        playingPlayerControllers.add(playerController1);
        playingPlayerControllers.add(playerController2);

        playerRolePairs.add(new Pair<>(playerController1.getPlayer(), playerController1.getRole()));
        playerRolePairs.add(new Pair<>(playerController2.getPlayer(), playerController2.getRole()));

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
        boardController.setBoardByReference(game.getBoard());

        ArrayList<Pair<Player, Role>> playerRolePairs = game.getPlayerRolePairs();
        Player loggedPlayer = loggedPlayerController.getPlayer();

        String playerId;

        for(Pair<Player, Role> playerRolePair : playerRolePairs)
        {
            playerId = playerRolePair.first.getId();
            if(playerId.equals(loggedPlayer.getId()))
            {
                playerRolePair.first = loggedPlayer;
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

        for(PlayerController playerController : playingPlayerControllers)
        {
            lastTurn = boardController.getLastTurn();

            action = playerController.play(difficulty, lastTurn, code);

            if(action != null)
            {
                boardController.checkAction(action);
                boardController.addAction(action);

                printBoard();
            }
        }
    }

    /*private void processAction(Action action)
    {

    }*/

    private boolean checkTurn(Turn turn)
    {
        return true;
    }

    private void giveClue() throws IllegalArgumentException
    {
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

    public List<Color> codeInputByUser(Difficulty difficulty) throws ReservedKeywordException
    {
        List<String> code = presentationController.readCode(difficulty);
        List<Color> colorList = new ArrayList<>(code.size());

        for(final String str : code)
        {
            colorList.add(Color.getColor(str));
        }

        return colorList;
    }

    public List<Color> correctionInputByUser(Difficulty difficulty) throws ReservedKeywordException
    {
        List<String> correction = presentationController.readCorrectionCode(difficulty);
        List<Color> colorList = new ArrayList<>(correction.size());

        for(final String str : correction)
        {
            colorList.add(Color.getColor(str));
        }

        return colorList;
    }

    private void printBoard()
    {
        List<Turn> turnSet = boardController.getTurnSet();
        List<List<Color>> codeList = new ArrayList<>(turnSet.size());

        for(final Turn turn : turnSet)
        {
            codeList.add(turn.toList());
        }

        presentationController.printBoard(codeList);
    }

    /* MAIN STATE MACHINE */

    public void exe() throws IntegrityCorruption, ReservedKeywordException
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
                    giveClue();
                    gameController.pointsClue();
                    state = State.IN_GAME_MENU;
                    break;

                case CHECK_INFO:
                    presentationController.showInfo();
                    state = State.MAIN_GAME_MENU;
                    break;

                case CHECK_RANKING:
                    state = State.MAIN_GAME_MENU;
                    break;

                case CHECK_TURN_NUMBER:
                    boolean finished = boardController.finished();

                    state = Translate.booleanModeToStateCheckTurnNumber(finished, mode);

                    /*if(finished)
                    {
                        state = State.GAME_OVER_MENU;
                    }
                    else
                    {
                        state = State.IN_GAME_MENU;
                    }*/
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
                    gameController.pointsEndGame();
                    returnState = presentationController.gameOverMenu();
                    state = Translate.int2StateGameOverMenu(returnState);
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
                    catch(IOException | ClassNotFoundException | WrongPassword e)
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
                    catch (ReservedKeywordException e){
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
                        state = State.CHECK_TURN_NUMBER;
                    }
                    catch (ReservedKeywordException e)
                    {
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
                        state = State.REGISTER_USER_MENU;
                    }
                    break;

                case REGISTER_USER_MENU:
                    try{
                        userInfo = presentationController.registerMenu();
                        state = State.REGISTER_USER;
                    }
                    catch(ReservedKeywordException e){
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
