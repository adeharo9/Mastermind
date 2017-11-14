package testing.drivers;

import persistence.PlayerPersistence;
import util.ioUtils;
import domain.classes.*;

import java.io.IOException;

public class DriverPlayerPersistence {

    public static void main(String[] args)
    {
        DriverPlayerPersistence driverPlayerPersistence = new DriverPlayerPersistence();
        driverPlayerPersistence.exe();
    }

    private void exe()
    {
        if(savePlayerTest())
        {
            ioUtils.printOutLn("The player with name lsd and password 1234 has been saved correctly!");

            if(loadPlayerTest())
            {
                ioUtils.printOutLn("The player has been loaded correctly!");
            }
            else
            {
                ioUtils.printOutLn("Test not passed. The player hasn't been loaded correctly!");
            }
        }

        else
        {
            ioUtils.printOutLn("Test not passed. The player hasn't been saved.");
        }
    }

    private boolean savePlayerTest()
    {
        Player player = new Human("lsd", "1234");
        PlayerPersistence playerPersistence = new PlayerPersistence();
        try
        {
            playerPersistence.save("lsd", player);
        }
        catch(IOException e)
        {
            return false;
        }

        return playerPersistence.exists("lsd");
    }

    private boolean loadPlayerTest()
    {
        Player playerLoaded;
        PlayerPersistence playerPersistence = new PlayerPersistence();

        try
        {
            playerLoaded = playerPersistence.load("lsd");
        }
        catch(IOException | ClassNotFoundException e)
        {
            return false;
        }

        return (playerLoaded.getId() == "lsd");
    }

    private boolean savePlayerGameTest()
    {
        return true;
    }
}
