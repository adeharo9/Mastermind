package testing.drivers;

import domain.classes.Human;
import domain.classes.Player;
import persistence.PlayerPersistence;
import util.ioUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DriverPlayerPersistence {

    private String id;

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
        Set<String> savedGames = new HashSet<>();
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
                (savedGames.contains(this.id)?this.id:""));
    }
}
