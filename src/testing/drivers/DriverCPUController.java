package testing.drivers;

import domain.classes.Action;
import domain.classes.CPU;
import domain.classes.Code;
import domain.classes.CodeCorrect;
import domain.controllers.PlayerController;
import enums.Color;
import enums.Difficulty;
import util.Utils;
import util.ioUtils;

import java.util.ArrayList;

public class DriverCPUController extends CPU
{
    public static void main(String args[])
    {
        DriverCPUController cpuControllerTest = new DriverCPUController();

        cpuControllerTest.exe();
    }

    private void exe()
    {
        testPermutationGeneration();
        testCPUCodeCorrect();
    }

    private void testPermutationGeneration()
    {
        generatePermutations(Difficulty.EASY);


        for(final Code code : getSolutions())
        {
            for (final Color color : code.getCodePins())
            {
                ioUtils.printOut(color.getStrId());
                ioUtils.printOut(" ");
            }
            ioUtils.endLine();
        }
    }

    private void testCPUCodeCorrect()
    {
        ArrayList<Color> proposedSolution = new ArrayList<>();
        ArrayList<Color> solution = new ArrayList<>();
        PlayerController cpuController = new PlayerController();

        cpuController.newCPU(Utils.autoID());

        for(int i = 0; i < 4; ++i) {
            proposedSolution.add(Color.getRandomColor(4));
        }

        for(int i = 0; i < 4; ++i) {
            solution.add(Color.getRandomColor(4));
        }

        Code code = new Code(proposedSolution);
        Code sol = new Code(solution);
        Difficulty dif = Difficulty.MEDIUM;

        //Action result = cpuController.codeCorrect(code, sol, dif);;
    }
}
