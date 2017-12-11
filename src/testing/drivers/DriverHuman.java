package testing.drivers;

import domain.classes.Code;
import domain.classes.Human;
import enums.Color;
import enums.Difficulty;
import enums.Role;
import exceptions.ReservedKeywordException;
import util.Utils;
import util.ioUtils;

import java.util.ArrayList;
import java.util.List;

public class DriverHuman {

    private Human testedHuman;

    public static void main(String[] args)
    {
        DriverHuman driverHuman = new DriverHuman();
        driverHuman.exe();
    }

    private void exe()
    {
        testConstructors();
        testGetsAndSets();
        testCheckPassword();
        testCodeMake();
        testCodeBreak();
        testCodeCorrect();
    }

    private void testConstructors()
    {
        Human human = new Human();
        Human humanWithId = new Human(Utils.autoID());
        Human humanWithIdAndPass = new Human(Utils.autoID(), "1234");
        this.testedHuman = humanWithIdAndPass;
    }

    private void testGetsAndSets()
    {
        String id = testedHuman.getId();
        String password = testedHuman.getPassword();

        testedHuman.setPassword("4321");
        ioUtils.printOutLn("New password: " + testedHuman.getPassword() + "\n");

        testedHuman.setRole(Role.CODE_MAKER);
    }

    private void testCheckPassword()
    {
        if(testedHuman.checkPassword("1234")){ioUtils.printOutLn("Error in checkPassword function!");}

        if(testedHuman.checkPassword("4231")){ioUtils.printOutLn("Error in checkPassword function!");}

        if(!testedHuman.checkPassword("4321")){ioUtils.printOutLn("Error in checkPassword function!");}
    }

    private void testCodeMake()
    {
        ioUtils.printOutLn("Test codeMake function:\n");
        try
        {
            testedHuman.codeMake(Difficulty.EASY);
        }
        catch (ReservedKeywordException e)
        {
            e.printStackTrace();
        }
    }

    private void testCodeBreak()
    {
        ioUtils.printOutLn("Test codeBreak function:\n");
        testedHuman.setRole(Role.CODE_BREAKER);

        try
        {
            testedHuman.codeBreak(Difficulty.EASY, null, true);
        }
        catch (ReservedKeywordException e)
        {
            e.printStackTrace();
        }
    }

    private void testCodeCorrect()
    {
        ioUtils.printOutLn("Test codeCorrect function:\n");
        List<Color> codePins = new ArrayList<>();
        codePins.add(Color.BLUE);
        codePins.add(Color.ORANGE);
        codePins.add(Color.PURPLE);
        codePins.add(Color.YELLOW);

        List<Color> sol = new ArrayList<>();
        codePins.add(Color.BLUE);
        codePins.add(Color.ORANGE);
        codePins.add(Color.PURPLE);
        codePins.add(Color.YELLOW);

        Code code = new Code(codePins);
        Code solution = new Code(sol);

        try
        {
            testedHuman.codeCorrect(Difficulty.EASY, code, solution);
        }
        catch (ReservedKeywordException e)
        {
            e.printStackTrace();
        }
    }
}
