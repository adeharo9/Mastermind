package persistence;

import domain.classes.Player;
import exceptions.IntegrityCorruption;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;


public class PlayerPersistence extends AbstractPersistence
{
    protected String getConfigFilePath(String playerId)
    {
        return getDirPath() + playerId + "/" + CONFIG_FILE;
    }

    public PlayerPersistence()
    {

    }

    public boolean exists(String key)
    {
        File filePlayer = new File(BASE_PATH + PLAYER_PATH + key + GAME_EXTENSION);
        return filePlayer.exists();
    }

    public String getDirPath()
    {
        return BASE_PATH + PLAYER_PATH;
    }

    public Player load(String id) throws IOException, ClassNotFoundException
    {
        return (Player) super.load(id);
    }

    public void save(Object player) throws IOException
    {
        String id = ((Player) player).getId();
        super.save(id, player);
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
        out.close();
    }

    public ArrayList<String> loadSavedGames(String playerId) throws IOException
    {
        String savedGame;
        ArrayList<String> savedGames = new ArrayList<>();

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

        return savedGames;
    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
