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
        testCodeUnorderedEquals();
        testCodeOrderedEquals();
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

                    if(!code1.equals(code2)) throw new RuntimeException("Error on testCodeOrderedEquals.");
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
