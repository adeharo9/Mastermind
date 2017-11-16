package testing.drivers;

import static org.junit.jupiter.api.Assertions.*;
import domain.controllers.DomainController;
import testing.stubs.*;
import enums.*;
import util.Utils;

import java.util.*;

class JUnitDriverDomainController
{

    private DomainController domainController;
    private PlayerPersistence playerPersistence = new PlayerPersistence();
    private PlayerController loggedPlayerController = new PlayerController();
    private PresentationController presentationController = new PresentationController();
    private Role role = Role.CODE_BREAKER;
    private Mode mode = Mode.HUMAN_VS_HUMAN;
    private PlayerController codeMakerController = new PlayerController();
    private PlayerController codeBreakerController = new PlayerController();
    private BoardController boardController = new BoardController();
    private GameController gameController = new GameController();
    private Difficulty difficulty = Difficulty.EASY;
    private List<String> savedGames;
    private GamePersistence gamePersistence = new GamePersistence();

    public static void main(String[] args)
    {
        JUnitDriverDomainController jUnitDriverDomainController = new JUnitDriverDomainController();
        jUnitDriverDomainController.exe();
    }

    private void exe()
    {
        testConstructors();
        testHasToPrintBoard();
        testLogInUser();
        testRegisterUser();
        testNewGame();
        testLoadSavedGameList();
        testSaveGame();
    }

    private void testConstructors()
    {
        assertNull(domainController);
        domainController = new DomainController();
        assertNotNull(domainController);
    }

    private void testHasToPrintBoard()
    {
        GameController gameController = new GameController();
        PlayerController loggedPlayerController = new PlayerController();

        boolean hasToPrint = gameController.getMode() == Mode.HUMAN_VS_HUMAN || loggedPlayerController.getRole() == role || loggedPlayerController.getRole() == Role.WATCHER;
        assertTrue(hasToPrint);
    }

    private void testLogInUser()
    {
        Player player = playerPersistence.load("pep");
        PlayerController playerController = new PlayerController(player);

        boolean b = playerController.checkPassword("1234");
        assertTrue(b);
    }

    private void testRegisterUser()
    {
        boolean b = playerPersistence.exists("pep");
        assertFalse(b);
        Player player = loggedPlayerController.newHuman("pep", "1234");
        playerPersistence.save(player);
    }

    private void testNewGame()
    {
        boolean passedTest1 = false;
        boolean passedTest2 = false;
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
                passedTest1 = true;
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
                passedTest2 = true;
                break;
            default:
                throw new IllegalArgumentException();
        }

        players.add(playerController1.getPlayer());
        players.add(playerController2.getPlayer());

        Board board = boardController.newBoard(difficulty);

        gameController.newGame(Utils.autoID(), difficulty, mode, board, players);

        assertTrue(passedTest1 && passedTest2);
    }

    private void testLoadSavedGameList()
    {
        Player loggedPlayer = loggedPlayerController.getPlayer();
        savedGames = playerPersistence.loadSavedGames(loggedPlayer.getId());

        for(int i = 0; i < savedGames.size(); ++i)
        {
            if(i == 0){assertEquals(savedGames.get(i), "alexgame");}
            else if(i == 1){assertEquals(savedGames.get(i), "rafaelgame");}
            else{assertEquals(savedGames.get(i), "alejandrogame");}
        }
    }

    private void testSaveGame()
    {
        Game game = gameController.getGame();
        gamePersistence.save(game);

        String gameId = gameController.getId();
        String playerId = loggedPlayerController.getId();

        playerPersistence.savePlayerGame(gameId, playerId);
    }



}
