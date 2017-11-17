package testing.drivers;

import persistence.PlayerPersistence;
import util.Utils;
import util.ioUtils;
import domain.classes.*;
import java.util.*;

import javax.rmi.CORBA.Util;
import java.io.IOException;

public class DriverPlayerPersistence {

    private String id;
    private String game;

    public static void main(String[] args)
    {
        DriverPlayerPersistence driverPlayerPersistence = new DriverPlayerPersistence();
        driverPlayerPersistence.exe();
    }

    private void exe()
    {
        savePlayerTest();
        loadPlayerTest();
        savePlayerGameTest();
        loadPlayerGameTest();
    }

    private void savePlayerTest()
    {
        ioUtils.printOutLn("Test savePlayer");
        ioUtils.printOutLn("Introduce a id:");
        String idPlayer = ioUtils.input();
        this.id = idPlayer;

        ioUtils.printOutLn("Introduce a password:");
        String password = ioUtils.input();
        Player player = new Human(idPlayer, password);
        PlayerPersistence playerPersistence = new PlayerPersistence();
        try
        {
            playerPersistence.save(player);
        }
        catch(IOException e)
        {
            ioUtils.printOutLn("Error on savePlayerTest");
        }
        ioUtils.printOutLn("Success on savePlayerTest");
    }

    private void loadPlayerTest()
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
            ioUtils.printOutLn("Error on loadPlayerTest");
        }

        ioUtils.printOutLn("If the function works correctly, the id introduced previously is: " +
                            idPlayer);
    }

    private void savePlayerGameTest()
    {
        PlayerPersistence playerPersistence = new PlayerPersistence();
        ioUtils.printOutLn("Introduce a id of a game: ");
        String idGame = ioUtils.input();
        this.game = idGame;
        try
        {
            playerPersistence.savePlayerGame(idGame, this.id);
        }
        catch(IOException e)
        {
            ioUtils.printOutLn("Error on savePlayerGameTest");
        }

        ioUtils.printOutLn("Success on savePlayerGameTest");
    }

    private void loadPlayerGameTest()
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
            ioUtils.printOutLn("Error on loadPlayerGameTest");
        }
        ioUtils.printOutLn("If the function works correctly, the id introduced previously is: " +
                savedGames.get(0));
    }
}
