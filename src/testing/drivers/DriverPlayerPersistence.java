package testing.drivers;

import persistence.PlayerPersistence;
import util.Utils;
import util.ioUtils;
import domain.classes.*;
import java.util.*;

import javax.rmi.CORBA.Util;
import java.io.IOException;

public class DriverPlayerPersistence {

    protected String id;
    protected String game;

    public static void main(String[] args)
    {
        DriverPlayerPersistence driverPlayerPersistence = new DriverPlayerPersistence();
        driverPlayerPersistence.exe();
    }

    private void exe()
    {
        if(savePlayerTest())
        {
            ioUtils.printOutLn("The player has been saved correctly!");

            if(loadPlayerTest())
            {
                ioUtils.printOutLn("The player has been loaded correctly!");

                if(savePlayerGameTest())
                {
                    ioUtils.printOutLn("The player game has been saved correctly!");

                    if(loadPlayerGameTest())
                    {
                        ioUtils.printOutLn("The player game has been loaded correctly!");
                    }

                    else
                    {
                        ioUtils.printOutLn("Test not passed. The player game hasn't been loaded correctly!");
                    }

                }

                else
                {
                    ioUtils.printOutLn("Test not passed. The player game hasn't been saved correctly!");
                }
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
        String idPlayer = Utils.autoID();
        this.id = idPlayer;
        Player player = new Human(idPlayer, "1234");
        PlayerPersistence playerPersistence = new PlayerPersistence();
        try
        {
            playerPersistence.save(player);
        }
        catch(IOException e)
        {
            return false;
        }

        return playerPersistence.exists(idPlayer);
    }

    private boolean loadPlayerTest()
    {
        Player playerLoaded;
        PlayerPersistence playerPersistence = new PlayerPersistence();
        String idPlayer = null;

        try
        {
            playerLoaded = playerPersistence.load(this.id);
            idPlayer = playerLoaded.getId();
        }
        catch(IOException | ClassNotFoundException e)
        {
            return false;
        }

        return (idPlayer.equals(this.id));
    }

    private boolean savePlayerGameTest()
    {
        PlayerPersistence playerPersistence = new PlayerPersistence();
        String idGame = Utils.autoID();
        this.game = idGame;
        try
        {
            playerPersistence.savePlayerGame(idGame, this.id);
        }
        catch(IOException e)
        {
            return false;
        }

        return true;
    }

    private boolean loadPlayerGameTest()
    {
        PlayerPersistence playerPersistence = new PlayerPersistence();
        ArrayList<String> savedGames = new ArrayList<>();
        Player player = new Human();

        try
        {
            playerPersistence.load(this.id);
            savedGames = playerPersistence.loadSavedGames(this.id);

        }
        catch(IOException | ClassNotFoundException e)
        {
            return false;
        }
        return(savedGames.get(0).equals(game));
    }
}
