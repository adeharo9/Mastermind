package testing.drivers;

import domain.classes.Code;
import domain.classes.Turn;
import enums.Color;
import util.ioUtils;

import java.util.ArrayList;
import java.util.List;

public class DriverTurn {

    private Turn test;
    private List<Color> colors;

    private DriverTurn()
    {

    }

    public static void main (String args[]){

        DriverTurn dt = new DriverTurn();
        dt.exe();

    }

    public void exe(){
        testConstructors();
        testGetsAndSets();
    }

    public void testConstructors()
    {
        colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.PURPLE);
        colors.add(Color.GREEN);
        colors.add(Color.GREEN);
        Code code = new Code(colors);
        test = new Turn(code);
        ioUtils.printOutLn("Success on testConstructors");
    }

    private void testGetsAndSets()
    {
        List<Color> pins = test.getCodePins();
        boolean error = false;

        for(int i = 0; i < pins.size(); ++i)
        {
            if(pins.get(i) != colors.get(i))
            {
                ioUtils.printOutLn("Error in getCodePins!");
                error = true;
                break;
            }
        }

        colors = new ArrayList<>();
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        colors.add(Color.NONE);
        colors.add(Color.NONE);
        test.setCorrectionPins(colors);

        List<Color> correct = test.getCorrectionPins();

        for(int i = 0; i < correct.size(); ++i)
        {
            if(correct.get(i) != colors.get(i))
            {
                ioUtils.printOutLn("Error in getCorrectionPins!");
                error = true;
                break;
            }
        }

        if(!error) ioUtils.printOutLn("Success on testGetsAndSets");
    }


}
