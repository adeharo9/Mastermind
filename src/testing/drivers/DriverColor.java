package testing.drivers;

import enums.Color;
import util.ioUtils;
import java.util.*;

public class DriverColor {

    public static void main(String[] args)
    {
        DriverColor driverColor = new DriverColor();
        driverColor.exe();
    }

    private void exe()
    {
        testGetRandomColorAndGetStrDescription();
        testGetRandomColorOfASet();
    }

    private void testGetRandomColorAndGetStrDescription()
    {
        Color randomColor;

        randomColor = Color.getRandomColor(4);
        ioUtils.printOutLn(randomColor.getStrDescription());

        randomColor = Color.getRandomColor(6);
        ioUtils.printOutLn(randomColor.getStrDescription());

        randomColor = Color.getRandomColor(8);
        ioUtils.printOutLn(randomColor.getStrDescription());
    }

    private void testGetRandomColorOfASet()
    {
        ioUtils.printOutLn("----------------------------------------------------------------\n" +
                        "Now, we're going to test if, given a set of colors (blue, black, orange and purple), the function getRandomColor " +
                "returns one of the colors that the set contains:\n" +
                "Introduce the 4 colors of this set (Input example: O Y B G):\n" +
                "O: Orange\n" +
                "R: Red\n" +
                "G: Green\n" +
                "B: Blue\n" +
                "Y: Yellow\n" +
                "P: Purple");
        Set<Color> colorSet = new HashSet<>();
        for(int i = 0; i < 4; ++i)
        {
            Color color = Color.getColor(ioUtils.input());
            colorSet.add(color);
        }
        ioUtils.printOutLn("If the getRandomColor works correctly, the color that will appear is one " +
                "of those that have been previously introduced:");
        ioUtils.printOutLn(Color.getRandomColor(colorSet).getStrDescription());
    }
}
