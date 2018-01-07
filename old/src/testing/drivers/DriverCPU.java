package testing.drivers;

import domain.classes.*;
import enums.*;
import java.util.*;
import util.Utils;
import util.ioUtils;

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
        CPU cpu = new CPU(Utils.autoID());
        this.testedCPU = cpu;

        testCodeMake();
        testCodeBreak();
    }

    private void testCodeMake()
    {
        Action action;
        List<Color> code = new ArrayList<>();

        action = testedCPU.codeMake(Difficulty.EASY);
        code = action.getCode().getCodePins();
        ioUtils.printOutLn("Code solution generate for a easy game (forbidden repeated colors): ");
        for(int i = 0; i < code.size(); ++i)
        {
            ioUtils.printOutLn(code.get(i).getStrDescription());
        }
        ioUtils.endLine();

        action = testedCPU.codeMake(Difficulty.MEDIUM);
        code = action.getCode().getCodePins();
        ioUtils.printOutLn("Code solution generate for a medium game: ");
        for(int i = 0; i < code.size(); ++i)
        {
            ioUtils.printOutLn(code.get(i).getStrDescription());
        }
        ioUtils.endLine();

        this.solution = new Code(code);

        action = testedCPU.codeMake(Difficulty.HARD);
        code = action.getCode().getCodePins();
        ioUtils.printOutLn("Code solution generate for a hard game: ");
        for(int i = 0; i < code.size(); ++i)
        {
            ioUtils.printOutLn(code.get(i).getStrDescription());
        }
        ioUtils.endLine();
    }

    private void testCodeBreak()
    {
        List<Color> code = new ArrayList<>();
        Action action = testedCPU.codeBreak(Difficulty.MEDIUM, null, true);

        ioUtils.printOutLn("------------------------------------------------------------------" +
                "\nNow, we're going to test the algorithm. Solution for the game: ");
        for(int i = 0; i < this.solution.getCodePins().size(); ++i)
        {
            ioUtils.printOutLn(this.solution.getCodePins().get(i).getStrDescription());
        }
        ioUtils.endLine();

        code = action.getCode().getCodePins();
        Turn lastTurn = new Turn(action.getCode());
        ioUtils.printOutLn("First solution generate by the algorithm: ");
        for(int i = 0; i < code.size(); ++i)
        {
            ioUtils.printOutLn(code.get(i).getStrDescription());
        }
        ioUtils.endLine();

        int count = 1;
        while(count < 7)
        {
            lastTurn = new Turn(action.getCode());
            Action codeCorrect = new CodeCorrect();
            codeCorrect = testedCPU.codeCorrect(Difficulty.MEDIUM, action.getCode(), solution);

            Code correction = codeCorrect.getCode();
            lastTurn.setCorrection(correction);

            action = testedCPU.codeBreak(Difficulty.MEDIUM, lastTurn, false);
            code = action.getCode().getCodePins();
            ioUtils.printOutLn("Solution " + Integer.toString(count) + " generate by the algorithm: ");
            for (int i = 0; i < code.size(); ++i) {
                ioUtils.printOutLn(code.get(i).getStrDescription());
            }
            ioUtils.endLine();
            ++count;
        }

    }
}
