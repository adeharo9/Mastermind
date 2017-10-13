package testing.drivers;

import util.*;

public class TestMenuDriver
{

    private String msg;

    public void exe()
    {
        showMenu();
        userInput();
        driverStateMachine();
    }

    public static void showMenu()
    {
        ioUtils.printOut("Testing Interface Menu");
        ioUtils.endLine();
        ioUtils.printOut("1. Turn Testing");
        ioUtils.endLine();
    }

    public void userInput()
    {
        msg = ioUtils.input();
    }

    public void driverStateMachine()
    {
        switch(msg)
        {
            case "1":
                break;
            default:
                ioUtils.printErr("Error. Not a valid option");
        }
    }
}
