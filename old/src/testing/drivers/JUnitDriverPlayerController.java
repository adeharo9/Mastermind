package testing.drivers;

import static org.junit.Assert.*;
import domain.controllers.PlayerController;
import domain.classes.Player;
import domain.classes.Human;
import enums.Role;
import util.Utils;


public class JUnitDriverPlayerController {

    private PlayerController testedPlayerController;

    public static void main(String[] args)
    {
        JUnitDriverPlayerController driverPlayerController = new JUnitDriverPlayerController();
        driverPlayerController.exe();
    }

    private void exe()
    {
        testSetPlayerByReference();
        testGetsAndSets();
        testNewHumanAndNewCPU();
        testPlayCodeMaker();
        testPlayCodeBreaker();
        testPlayWatcher();
    }

    private void testSetPlayerByReference()
    {
        String id = Utils.autoID();
        Player player = new Human(id, "1234");
        PlayerController playerController = new PlayerController();
        playerController.setPlayerByReference(player);

        testedPlayerController = playerController;
        assertEquals(testedPlayerController.getId(), id);
    }

    private void testGetsAndSets()
    {
        Player player = testedPlayerController.getPlayer();
        assertNotNull(player);
        String id = testedPlayerController.getId();
        assertNotNull(id);

        assertNull(testedPlayerController.getRole());

        testedPlayerController.setRole(Role.CODE_MAKER);
        assertNotNull(testedPlayerController.getRole());

        String role = testedPlayerController.getRole().toString();
        assertEquals(role, Role.CODE_MAKER.toString());
    }

    private void testNewHumanAndNewCPU()
    {
        testedPlayerController.newHuman("alex", "1234");
        assertEquals(testedPlayerController.getId(), "alex");
        testedPlayerController.newCPU("cpu");
        assertEquals(testedPlayerController.getId(), "cpu");
    }

    private void testPlayCodeMaker() {
        boolean isFirstTurn = true;
        testing.stubs.StubPlayer player = new testing.stubs.StubPlayer();
        Object action;
        testedPlayerController.setRole(Role.CODE_MAKER);
        for (int i = 0; i < 2; ++i) {
            switch (testedPlayerController.getRole()) {
                case CODE_MAKER:
                    if (isFirstTurn) {
                        action = player.codeMake(null);
                    } else {
                        action = player.codeCorrect(null, null, null);
                    }
                    break;
                case CODE_BREAKER:
                    action = player.codeBreak(null, null, false);
                    break;
                case WATCHER:
                    action = null;
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            assertNull(action);
            isFirstTurn = false;
        }
    }

    private void testPlayCodeBreaker()
    {
        boolean isFirstTurn = true;
        testing.stubs.StubPlayer player = new testing.stubs.StubPlayer();
        Object action;
        testedPlayerController.setRole(Role.CODE_BREAKER);
        for (int i = 0; i < 2; ++i) {
            switch (testedPlayerController.getRole()) {
                case CODE_MAKER:
                    if (isFirstTurn) {
                        action = player.codeMake(null);
                    } else {
                        action = player.codeCorrect(null, null, null);
                    }
                    break;
                case CODE_BREAKER:
                    action = player.codeBreak(null, null, false);
                    break;
                case WATCHER:
                    action = null;
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            assertNull(action);
            isFirstTurn = false;
        }
    }

    private void testPlayWatcher()
    {
        boolean isFirstTurn = true;
        testing.stubs.StubPlayer player = new testing.stubs.StubPlayer();
        Object action;
        testedPlayerController.setRole(Role.WATCHER);
        for (int i = 0; i < 2; ++i) {
            switch (testedPlayerController.getRole()) {
                case CODE_MAKER:
                    if (isFirstTurn) {
                        action = player.codeMake(null);
                    } else {
                        action = player.codeCorrect(null, null, null);
                    }
                    break;
                case CODE_BREAKER:
                    action = player.codeBreak(null, null, false);
                    break;
                case WATCHER:
                    action = null;
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            assertNull(action);
            isFirstTurn = false;
        }
    }
}
