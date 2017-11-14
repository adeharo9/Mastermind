package testing.drivers;
import domain.classes.Game;
import persistence.GamePersistence;
import enums.Difficulty;
import util.Utils;
import util.ioUtils;

public class DriverGamePersistence
{
    private static final String gameID = Utils.autoID();

    public static void main(String args[])
    {
        Game test = new Game(gameID, Difficulty.EASY);
        Game result;
        GamePersistence gP = new GamePersistence();

        try
        {
            gP.save(test);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

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

