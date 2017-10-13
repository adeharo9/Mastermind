package testing.drivers;

import domain.classes.*;
import testing.AbstractTesting;
import testing.stubs.Visualize;
import util.*;

import java.util.ArrayList;

public class TestMenuDriver extends AbstractTesting
{
    private String state;
    private String msg;
    private Turn turn;

    public void userInput()
    {
        msg = ioUtils.input();
    }

    public TestMenuDriver()
    {
        state = "init";
    }

    public void exe()
    {
        while(!state.equals("end"))
        {
            while (!driverStateMachine()) ;
        }
    }

    public void menu()
    {
        showMenu();
        userInput();
    }

    public static void showMenu()
    {
        ioUtils.printOutLn("Testing Interface Menu");
        ioUtils.endLine();
        ioUtils.printOutLn("1. Turn Testing");
        ioUtils.endLine();
        ioUtils.printOut("Select an option: ");
    }

    public void turnMenu()
    {
        showTurnMenu();
        userInput();
    }

    public static void showTurnMenu()
    {
        ioUtils.printOutLn("Turn Testing Menu");
        ioUtils.endLine();
        ioUtils.printOutLn("1. Manual Turn Input");
        ioUtils.printOutLn("2. Test Games");
        ioUtils.endLine();
        ioUtils.printOut("Select an option: ");
    }

    public void manualTurn()
    {
        ioUtils.printOut("Introduce number of pins: ");
        userInput();
        int n = Integer.parseInt(msg);
        turn  = new Turn(n);

        ioUtils.printOutLn("Introduce bPin color sequence: ");
        for(int i = 0; i < n; ++i)
        {
            userInput();
            turn.getBPins().add(Integer.parseInt(msg));
        }

        /*ioUtils.printOut("Introduce sPin color sequence: ");
        for(int i = 0; i < n; ++i)
        {
            userInput();
            turn.getSPins().add(Integer.parseInt(msg));
        }*/
    }

    public boolean driverStateMachine()
    {
        boolean b = true;

        switch(state)
        {
            case "init":
                menu();

                switch(msg)
                {
                    case "1":
                        state = "turn";
                        break;
                    default:
                        b = false;
                }
                break;

            case "turn":
                turnMenu();

                switch(msg)
                {
                    case "1":
                        state = "manualTurn";
                        break;
                    case "2":
                        state = "autoTurn";
                        break;
                    default:
                        b = false;
                }

                break;

            case "manualTurn":
                manualTurn();

                state = "visualize";
                break;

            case "autoTurn":
                //autoTurn();

                state = "visualize";
                break;

            case "visualize":
                Visualize.visualizeTurn(turn);

                state = "end";
                break;

            default:

        }

        return b;
    }
}
