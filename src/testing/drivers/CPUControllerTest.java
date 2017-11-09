package testing.drivers;

import domain.classes.Code;
import domain.controllers.CPUController;
import enums.Color;
import enums.Difficulty;
import util.ioUtils;

public class CPUControllerTest extends CPUController
{
    public static void main(String args[])
    {
        CPUControllerTest cpuControllerTest = new CPUControllerTest();

        cpuControllerTest.exe();
    }

    private void exe()
    {
        testPermutationGeneration();
    }

    private void testPermutationGeneration()
    {
        generatePermutations(Difficulty.EASY);


        for(final Code code : getSolutions())
        {
            for (final Color color : code.getBPins())
            {
                ioUtils.printOut(color.getStrId());
                ioUtils.printOut(" ");
            }
            ioUtils.endLine();
        }
    }
}
