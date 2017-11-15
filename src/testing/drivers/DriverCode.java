package testing.drivers;

import domain.classes.Code;
import enums.Color;
import enums.Difficulty;
import util.Constants;
import util.ioUtils;

import java.util.*;

public class DriverCode
{
    private static final int NUM_TESTS = 1000;
    private static final Difficulty DIFFICULTY = Difficulty.HARD;
    private Code test;
    private List<Color> colors;

    private DriverCode()
    {

    }

    public static void main(String[] args) throws RuntimeException
    {
        DriverCode codeDriver = new DriverCode();

        codeDriver.exe();
    }

    private void exe() throws RuntimeException
    {
        testConstructors();
        testGetsAndSets();
        testCodeUnorderedEquals();
        testCodeOrderedEquals();
    }

    private void testConstructors()
    {
        colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.PURPLE);
        colors.add(Color.GREEN);
        colors.add(Color.GREEN);

        Code code = new Code(colors);
        test = code;

        Code codeCopy = new Code(code);

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

        if(!error) ioUtils.printOutLn("Success on testGetsAndSets");
    }

    private void testCodeUnorderedEquals() throws RuntimeException
    {
        int n = NUM_TESTS / 2;
        List<Code> codeList = new ArrayList<>(n);

        for(int i = 0; i < n; ++i)
        {
            Code code = randomCode(DIFFICULTY);
            codeList.add(code);
            codeList.add(code);
        }

        Collections.shuffle(codeList);

        //ioUtils.printOutLn("UNORDERED HASH TESTING");

        for(int i = 0; i < n; ++i)
        {
            Code code1 = codeList.get(i);
            for(int j = i; j < n; ++j)
            {
                Code code2 = codeList.get(j);
                if(code1.unorderedEquals(code2))
                {
                    /*printCode(code1);
                    printCode(code2);
                    ioUtils.endLine();*/

                    if(!areUnorderedEqual(code1, code2))
                    {
                        throw new RuntimeException("Error on testCodeUnorderedEquals.");
                    }
                }
            }
        }
        ioUtils.printOutLn("Success on testCodeUnorderedEquals");
    }

    private void testCodeOrderedEquals() throws RuntimeException
    {
        int n = NUM_TESTS / 2;
        List<Code> codeList = new ArrayList<>(n);

        for(int i = 0; i < n; ++i)
        {
            Code code = randomCode(DIFFICULTY);
            codeList.add(code);
            codeList.add(code);
        }

        Collections.shuffle(codeList);

        //ioUtils.printOutLn("ORDERED HASH TESTING");

        for(int i = 0; i < n; ++i)
        {
            Code code1 = codeList.get(i);
            for(int j = i; j < n; ++j)
            {
                Code code2 = codeList.get(j);
                if(code1.orderedEquals(code2))
                {
                    /*printCode(code1);
                    printCode(code2);
                    ioUtils.endLine();*/

                    if(!code1.hardEquals(code2)) throw new RuntimeException("Error on testCodeOrderedEquals.");
                }
            }
        }
        ioUtils.printOutLn("Success on testCodeOrderedEquals");
    }

    private Code randomCode(final Difficulty difficulty)
    {
        int numPins = Constants.getNumPinsByDifficulty(difficulty);
        int numColors = Constants.getNumColorsByDifficulty(difficulty);

        List<Color> colorList = new ArrayList<>(numPins);

        for(int i = 0; i < numPins; ++i)
        {
            Color color = Color.getRandomColor(numColors);
            colorList.add(color);
        }

        Collections.shuffle(colorList);

        return new Code(colorList);
    }

    private void printCode(final Code code)
    {
        List<Color> colorList = code.getCodePins();

        for(final Color color : colorList)
        {
            ioUtils.printOut(color.getStrId() + " ");
        }
        ioUtils.endLine();
    }

    private boolean areUnorderedEqual(Code code1, Code code2)
    {
        if(code1 == code2) return true;
        if(code1 == null || code2 == null || code1.size() != code2.size()) return false;

        Set<Color> colorSet1 = new HashSet<>(code1.getCodePins());
        Set<Color> colorSet2 = new HashSet<>(code2.getCodePins());

        return colorSet1.size() == colorSet2.size();
    }
}
