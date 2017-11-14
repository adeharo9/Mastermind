package domain.classes;

import enums.Color;
import enums.Difficulty;
import exceptions.ReservedKeywordException;
import util.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Human extends Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private String password;

    /* PRIVATE METHODS */

    private static boolean isValidPassword(final String password)
    {
        return !password.isEmpty();
    }

    /* CONSTRUCTION METHODS */

    public Human()
    {
        super();
        this.password = "";
    }

    public Human(final String username) throws IllegalArgumentException
    {
        super(username);
    }

    public Human(final String username, final String password) throws IllegalArgumentException, NullPointerException
    {
        super(username);

        setPassword(password);
    }

    @Deprecated
    public Human(final Human human) throws IllegalArgumentException, NullPointerException
    {
        super(human);

        setPassword(human.getPassword());
    }

    /* SET METHODS */

    public void setPassword(final String password) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPassword(password);
        if(!b) throw new IllegalArgumentException();

        this.password = password;
    }

    /* GET METHODS */

    public final String getPassword()
    {
        return password;
    }

    /* VALIDATION METHODS */

    public final boolean checkPassword(final String password) throws NullPointerException
    {
        return this.password.equals(password);
    }

    /* CLONING METHODS */

    @Deprecated
    public Human deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Human(this);
    }

    /* PLAY METHODS */

    public Action codeMake(final Difficulty difficulty) throws ReservedKeywordException
    {
        List<Color> colorList = codeInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeMake(code);
    }

    public Action codeBreak(final Difficulty difficulty, final Turn lastTurn) throws ReservedKeywordException
    {
        List<Color> colorList = codeInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeBreak(code);
    }

    public Action codeCorrect(final Difficulty difficulty, final Code c, final Code s) throws ReservedKeywordException
    {
        List<Color> colorList = correctionInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeCorrect(code);
    }

    /* USER INTERACTION METHODS */

    private List<Color> codeInputByUser(Difficulty difficulty) throws ReservedKeywordException
    {
        List<String> code = readCode(difficulty);
        List<Color> colorList = new ArrayList<>(code.size());

        for(final String str : code)
        {
            colorList.add(Color.getColor(str));
        }

        return colorList;
    }

    private List<Color> correctionInputByUser(Difficulty difficulty) throws ReservedKeywordException
    {
        List<String> correction = readCorrectionCode(difficulty);
        List<Color> colorList = new ArrayList<>(correction.size());

        for(final String str : correction)
        {
            colorList.add(Color.getColor(str));
        }

        return colorList;
    }

    private List<String> readCode(Difficulty difficulty) throws ReservedKeywordException
    {
        boolean repetitionsPolicy = Constants.getRepetitionPolicyByDifficulty(difficulty);
        int numColors = Constants.getNumColorsByDifficulty(difficulty);
        int numPins = Constants.getNumPinsByDifficulty(difficulty);

        Set<Color> colorSet = Color.getValues(difficulty);
        List<String> code = new ArrayList<>(numPins);

        ioUtils.endLine();
        ioUtils.printOut("Write a code of " + numPins + (repetitionsPolicy ? " " : " non-repeated ") + "colors using the following letters (Input example:");

        for(int i = 0; i < numPins; ++i)
        {
            ioUtils.printOut(" " + Color.getRandomColor(numColors).getStrId());
        }

        ioUtils.printOutLn("):");

        for(final Color color : colorSet)
        {
            ioUtils.printOutLn(color.getStrId() + ": " + color.getStrDescription());
        }

        ioUtils.endLine();
        ioUtils.printOutLn("Write your code here (or 0 to pause):");

        String read;

        for(int i = 0; i < numPins; ++i)
        {
            read = ioUtils.input();
            if (read.equals("0")) throw new ReservedKeywordException();

            code.add(read);
        }

        return code;
    }

    private List<String> readCorrectionCode (Difficulty difficulty) throws ReservedKeywordException
    {
        int numColors = Constants.getNumColorsByDifficulty(difficulty);
        int numPins = Constants.getNumPinsByDifficulty(difficulty);
        Set<Color> correctionSet = Color.getCorrectionValues();
        List<String> code = new ArrayList<>(numPins);

        ioUtils.endLine();

        ioUtils.printOut("Write a " + numPins + "-sized code using the following letters (Input example:");

        for(int i = 0; i < numPins; ++i)
        {
            ioUtils.printOut(" " + Color.getRandomColor(numColors).getStrId());
        }

        ioUtils.printOutLn("):");

        for(final Color color : correctionSet)
        {
            ioUtils.printOutLn(color.getStrId() + ": " + color.getStrDescription());
        }

        ioUtils.endLine();

        ioUtils.printOutLn("Write your code here(or 0 to pause):");

        String read;

        for(int i = 0; i < numPins; ++i)
        {
            read = ioUtils.input();
            if (read.equals("0")) throw new ReservedKeywordException();

            code.add(read);
        }

        return code;
    }
}