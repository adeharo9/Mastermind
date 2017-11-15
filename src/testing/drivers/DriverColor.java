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
                "returns one of the colors that the set contains:\n");
        Set<Color> colorSet = new HashSet<>();

        colorSet.add(Color.BLUE);
        colorSet.add(Color.ORANGE);
        colorSet.add(Color.BLACK);
        colorSet.add(Color.PURPLE);

        ioUtils.printOutLn(Color.getRandomColor(colorSet).getStrDescription());
    }
}
