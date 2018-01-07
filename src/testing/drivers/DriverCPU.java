package testing.drivers;

import domain.classes.Action;
import domain.classes.CPU;
import domain.classes.Code;
import domain.classes.Turn;
import enums.Color;
import enums.Difficulty;
import util.Utils;
import util.ioUtils;

import java.util.List;

public class DriverCPU {

    private CPU testedCPU;
    private Code solution;

    public static void main(String[] args)
    {
        DriverCPU driverCPU = new DriverCPU();
        driverCPU.exe();
    }

    private void exe()
    {
        this.testedCPU = new CPU(Utils.autoID());

        testCodeMake();
        testCodeBreak();
    }

    private void testCodeMake()
    {
        Action action;
        List<Color> code;

        action = testedCPU.codeMake(Difficulty.EASY);
        code = action.getCode().getCodePins();
        ioUtils.printOutLn("Code solution generate for a easy game (forbidden repeated colors): ");
        for(Color aCode : code)
        {
            ioUtils.printOutLn(aCode.getStrDescription());
        }
        ioUtils.endLine();

        action = testedCPU.codeMake(Difficulty.MEDIUM);
        code = action.getCode().getCodePins();
        ioUtils.printOutLn("Code solution generate for a medium game: ");
        for(Color aCode : code)
        {
            ioUtils.printOutLn(aCode.getStrDescription());
        }
        ioUtils.endLine();

        this.solution = new Code(code);

        action = testedCPU.codeMake(Difficulty.HARD);
        code = action.getCode().getCodePins();
        ioUtils.printOutLn("Code solution generate for a hard game: ");
        for (Color aCode : code)
        {
            ioUtils.printOutLn(aCode.getStrDescription());
        }
        ioUtils.endLine();
    }

    private void testCodeBreak()
    {
        List<Color> code;
        Action action = testedCPU.codeBreak(Difficulty.MEDIUM, null, true);

        ioUtils.printOutLn("------------------------------------------------------------------" +
                "\nNow, we're going to test the algorithm. Solution for the game: ");
        for(int i = 0; i < this.solution.getCodePins().size(); ++i)
        {
            ioUtils.printOutLn(this.solution.getCodePins().get(i).getStrDescription());
        }
        ioUtils.endLine();

        code = action.getCode().getCodePins();
        Turn lastTurn;
        ioUtils.printOutLn("First solution generate by the algorithm: ");
        for (Color aCode : code)
        {
            ioUtils.printOutLn(aCode.getStrDescription());
        }
        ioUtils.endLine();

        int count = 1;
        while(count < 7)
        {
            lastTurn = new Turn(action.getCode());
            Action codeCorrect;
            codeCorrect = testedCPU.codeCorrect(action.getCode(), solution);

            Code correction = codeCorrect.getCode();
            lastTurn.setCorrection(correction);

            action = testedCPU.codeBreak(Difficulty.MEDIUM, lastTurn, false);
            code = action.getCode().getCodePins();
            ioUtils.printOutLn("Solution " + Integer.toString(count) + " generate by the algorithm: ");
            for (Color aCode : code)
            {
                ioUtils.printOutLn(aCode.getStrDescription());
            }
            ioUtils.endLine();
            ++count;
        }

    }
}
