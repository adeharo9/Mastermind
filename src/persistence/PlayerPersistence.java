package persistence;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import domain.classes.Player;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashSet;
import java.util.Set;

/**
 * PlayerPersistence.
 *
 * @author Alex
 */

public class PlayerPersistence extends AbstractPersistence
{

    protected final static String PLAYERS_PATH = "players/";
    protected final static String CONFIG_FILE = "config.cfg";
    protected String playerPath;

    protected String getConfigFilePath(String playerId)
    {
        setPlayerPath(playerId + "/");
        return  BASE_PATH + PLAYERS_PATH + playerPath + CONFIG_FILE;
    }

    public void setPlayerPath(String playerPath)
    {
        this.playerPath = playerPath;
    }

    public String getDirPath()
    {
        return BASE_PATH + PLAYERS_PATH + playerPath;
    }

    public PlayerPersistence()
    {

    }

    public boolean exists(String key)
    {
        File filePlayer = new File(getDirPath() + key + GAME_EXTENSION);
        return filePlayer.exists();
    }

    public boolean existsDir()
    {
        File filePlayer = new File(getDirPath());
        return filePlayer.exists();
    }

    public Player load(String playerId) throws IOException, ClassNotFoundException
    {
        setPlayerPath(playerId + "/");
        return (Player) super.load(playerId);
    }

    public void save(Object player) throws IOException
    {
        String playerId = ((Player) player).getId();
        setPlayerPath(playerId + "/");

        super.save(playerId, player);
    }

    public void savePlayerGame(String gameId, String playerId) throws IOException
    {
        String filePath = getConfigFilePath(playerId);
        File configFile = new File(filePath);

        boolean b = configFile.exists();
        if(!b) b = configFile.createNewFile();
        if(!b) throw new FileAlreadyExistsException("");

        BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));
        out.write(gameId);
        out.write("\n");
        out.close();
    }

    public void renamePlayer(final String oldUsername, final String newUsername) throws IOException
    {
        setPlayerPath(newUsername + "/");

        boolean b = existsDir();
        if(b) throw new IOException();

        setPlayerPath(oldUsername + "/");
        String playerFilePath = getFilePath(oldUsername);
        String newPlayerFilePath = getFilePath(newUsername);

        File oldPlayerFile = new File(playerFilePath);
        File newPlayerFile = new File(newPlayerFilePath);

        b = oldPlayerFile.renameTo(newPlayerFile);
        if(!b) throw new IOException();

        String oldPlayerDirPath = getDirPath();
        setPlayerPath(newUsername + "/");
        String newPlayerDirPath = getDirPath();

        File oldPlayerDir = new File(oldPlayerDirPath);
        File newPlayerDir = new File(newPlayerDirPath);

        b = oldPlayerDir.renameTo(newPlayerDir);
        if(!b) throw new IOException();
    }

    public void savePlayerGames(Set<String> gamesId, String playerId) throws IOException
    {
        for(String gameId : gamesId)
        {
            savePlayerGame(gameId, playerId);
        }
    }

    public Set<String> loadSavedGames(String playerId) throws IOException
    {
        String savedGame;
        Set<String> savedGames = new HashSet<>();

        String filePath = getConfigFilePath(playerId);
        File configFile = new File(filePath);

        boolean b = configFile.exists();
        if(!b) throw new FileNotFoundException();

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        savedGame = br.readLine();
        while(savedGame != null)
        {
            savedGames.add(savedGame);
            savedGame = br.readLine();
        }

        br.close();

        return savedGames;
    }

    public void deletePlayerGame(String gameId, String playerId) throws IOException
    {
        Set<String> savedGames = loadSavedGames(playerId);
        savedGames.remove(gameId);

        for(final String game : savedGames)
        {
            savePlayerGame(game, playerId);
        }

    }

    public void deleteConfigFile(final String username) throws IOException
    {
        String configFilePath = getConfigFilePath(username);

        File file = new File(configFilePath);
        file.delete();
    }
}
