package testing.drivers;

import domain.controllers.PlayerController;
import domain.classes.Player;
import domain.classes.Human;
import enums.Difficulty;
import enums.Role;
import util.Utils;
import util.ioUtils;
import testing.stubs.*;

public class DriverPlayerController {

    PlayerController testedPlayerController;

    public static void main(String[] args)
    {
        DriverPlayerController driverPlayerController = new DriverPlayerController();
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
        Player player = new Human(Utils.autoID(), "1234");
        PlayerController playerController = new PlayerController();
        playerController.setPlayerByReference(player);

        testedPlayerController = playerController;
        ioUtils.printOutLn("Success on testSetPlayerByReference");
    }

    private void testGetsAndSets()
    {
        Player player = testedPlayerController.getPlayer();
        String id = testedPlayerController.getId();
        String role;
        try
        {
            role = testedPlayerController.getRole().toString();
        }
        catch(NullPointerException e)
        {
            ioUtils.printOutLn("Success on testGetRole");
        }

        testedPlayerController.setRole(Role.CODE_MAKER);
        role = testedPlayerController.getRole().toString();

        ioUtils.printOutLn("Success on testGetsAndSets");
    }

    private void testNewHumanAndNewCPU()
    {
        testedPlayerController.newHuman("alex", "1234");
        testedPlayerController.newCPU("cpu");

        ioUtils.printOutLn("Success on testNewHumanAndNewCPU");
    }

    private void testPlayCodeMaker() {
        boolean isFirstTurn = true;
        testing.stubs.Player player = new testing.stubs.Player();
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

            if (action == null && i == 0)
            {
                ioUtils.printOutLn("Success on testPlayCodeMake with isFirstTurn = true");
                isFirstTurn = false;
            }
            else if(action == null && i == 1){ioUtils.printOutLn("Success on testPlayCodeMake with isFirstTurn = false");}
        }
    }

    private void testPlayCodeBreaker()
    {
        boolean isFirstTurn = true;
        testing.stubs.Player player = new testing.stubs.Player();
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

            if (action == null && i == 0)
            {
                ioUtils.printOutLn("Success on testPlayCodeBreak with isFirstTurn = true");
                isFirstTurn = false;
            }
            else if(action == null && i == 1){ioUtils.printOutLn("Success on testPlayCodeBreak with isFirstTurn = false");}
        }
    }

    private void testPlayWatcher()
    {
        boolean isFirstTurn = true;
        testing.stubs.Player player = new testing.stubs.Player();
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
            if (action == null && i == 0)
            {
                ioUtils.printOutLn("Success on testPlayWatcher with isFirstTurn = true");
                isFirstTurn = false;
            }
            else if(action == null && i == 1){ioUtils.printOutLn("Success on testPlayWatcher with isFirstTurn = false");}
        }
    }
}
