package testing.drivers;

import domain.controllers.DomainController;
import enums.Difficulty;
import enums.Mode;
import enums.Role;
import enums.State;
import testing.stubs.*;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

class JUnitDriverDomainController
{
    private State state;

    private DomainController domainController;
    private StubPlayerPersistence playerPersistence = new StubPlayerPersistence();
    private StubPlayerController loggedPlayerController = new StubPlayerController();
    private StubPresentationController presentationController = new StubPresentationController();
    private Role role = Role.CODE_BREAKER;
    private Mode mode = Mode.HUMAN_VS_HUMAN;
    private StubBoardController boardController = new StubBoardController();
    private StubGameController gameController = new StubGameController();
    private Difficulty difficulty = Difficulty.EASY;
    private StubGamePersistence gamePersistence = new StubGamePersistence();

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
        StubGameController gameController = new StubGameController();
        StubPlayerController loggedPlayerController = new StubPlayerController();

        boolean hasToPrint = gameController.getMode() == Mode.HUMAN_VS_HUMAN || loggedPlayerController.getRole() == role || loggedPlayerController.getRole() == Role.WATCHER;
        assertTrue(hasToPrint);
    }

    private void testLogInUser()
    {
        StubPlayer player = playerPersistence.load("pep");
        StubPlayerController playerController = new StubPlayerController(player);

        boolean b = playerController.checkPassword("1234");
        assertTrue(b);
    }

    private void testRegisterUser()
    {
        boolean b = playerPersistence.exists("pep");
        assertFalse(b);
        StubPlayer player = loggedPlayerController.newHuman("pep", "1234");
        playerPersistence.save(player);
    }

    @SuppressWarnings("ConstantConditions")
    private void testNewGame()
    {
        boolean passedTest1 = false;
        boolean passedTest2 = false;
        loggedPlayerController.restart();
        presentationController.clear();

        List<StubPlayer> players = new ArrayList<>();

        StubPlayerController playerController1 = null;
        StubPlayerController playerController2 = null;

        switch(mode)
        {
            case HUMAN_VS_HUMAN:
                playerController1 = loggedPlayerController;
                playerController2 = new StubPlayerController();

                playerController2.newHuman(Utils.autoID());
                passedTest1 = true;
                break;

            case HUMAN_VS_CPU:
                playerController1 = loggedPlayerController;
                playerController2 = new StubPlayerController();

                playerController2.newCPU(Utils.autoID());
                break;

            case CPU_VS_CPU:
                role = Role.autoRole();

                playerController1 = new StubPlayerController();
                playerController2 = new StubPlayerController();

                playerController1.newCPU(Utils.autoID());
                playerController2.newCPU(Utils.autoID());

                loggedPlayerController.setRole(Role.WATCHER);
                break;

            default:
                break;
        }

        playerController1.setRole(role);
        playerController2.setRole(Role.complementaryRole(role));

        StubPlayerController codeMakerController;
        StubPlayerController codeBreakerController = playerController2;

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

        StubBoard board = boardController.newBoard(difficulty);

        gameController.newGame(Utils.autoID(), difficulty, mode, board, players);

        assertTrue(passedTest1 && passedTest2);
    }

    private void testLoadSavedGameList()
    {
        StubPlayer loggedPlayer = loggedPlayerController.getPlayer();
        List<String> savedGames = playerPersistence.loadSavedGames(loggedPlayer.getId());

        for(int i = 0; i < savedGames.size(); ++i)
        {
            if(i == 0){assertEquals(savedGames.get(i), "alexgame");}
            else if(i == 1){assertEquals(savedGames.get(i), "rafaelgame");}
            else{assertEquals(savedGames.get(i), "alejandrogame");}
        }
    }

    private void testSaveGame()
    {
        StubGame game = gameController.getGame();
        gamePersistence.save(game);

        String gameId = gameController.getId();
        String playerId = loggedPlayerController.getId();

        playerPersistence.savePlayerGame(gameId, playerId);
    }

}
