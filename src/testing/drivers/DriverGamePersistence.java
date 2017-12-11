package testing.drivers;

import domain.classes.Game;
import enums.Difficulty;
import enums.Mode;
import persistence.GamePersistence;
import util.ioUtils;

public class DriverGamePersistence
{
    private String gameID;
    private GamePersistence gP = new GamePersistence();

    public static void main(String args[])
    {
        DriverGamePersistence driverGamePersistence = new DriverGamePersistence();
        driverGamePersistence.exe();
    }

    private void exe()
    {
        testSaveGame();
        testLoadGame();
    }

    private void testSaveGame()
    {
        ioUtils.printOutLn("Test saveGame");
        ioUtils.printOutLn("Introduce difficulty (h: hard, m: medium, e: easy):");
        String input = ioUtils.input();
        Difficulty difficulty = null;
        switch(input)
        {
            case "h":
                difficulty = Difficulty.HARD;
                break;
            case "m":
                difficulty = Difficulty.MEDIUM;
                break;
            case "e":
                difficulty = Difficulty.EASY;
                break;
            default:
                throw new IllegalArgumentException("Input error");
        }

        ioUtils.printOutLn("Introduce game id:");
        String game = ioUtils.input();
        gameID = game;

        ioUtils.printOutLn("Choose a mode:\n" +
                "1. HUMAN_VS_HUMAN\n" +
                "2. HUMAN_VS_CPU\n" +
                "3. CPU_VS_CPU");
        input = ioUtils.input();
        Mode mode = null;
        switch(input)
        {
            case "1":
                mode = Mode.HUMAN_VS_HUMAN;
                break;
            case "2":
                mode = Mode.HUMAN_VS_CPU;
                break;
            case "3":
                mode = Mode.CPU_VS_CPU;
                break;
            default:
                throw new IllegalArgumentException("Input error");
        }

        Game test = new Game(game, difficulty, mode);

        try
        {
            gP.save(test);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void testLoadGame()
    {
        ioUtils.printOutLn("Test loadGame");
        ioUtils.printOutLn("The function works correctly if the id introduced previously is: ");
        Game result = null;
        try
        {
            result = gP.load(gameID);
            ioUtils.printOutLn(result.getId());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

