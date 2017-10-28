package testing.drivers;
import domain.classes.Game;
import persistence.GamePersistence;
import util.Difficulty;
import util.Utils;
import util.ioUtils;

public class TestPersistence
{
    private static final String gameID = Utils.autoID();

    public static void main(String args[])
    {
        Game test = new Game(gameID, Difficulty.easy);
        Game result = new Game();
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
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        ioUtils.printOutLn(result.getId());
    }
}
