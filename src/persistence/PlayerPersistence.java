package persistence;

import domain.classes.Human;
import domain.classes.Player;
import exceptions.IntegrityCorruption;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;


public class PlayerPersistence extends AbstractPersistence
{

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

    public void savePlayerGame(String idGame, String idPlayer) throws IOException
    {
        boolean b;

        String filePath = getDirPath() + idPlayer + CONFIG_FILE;
        File configFile = new File(filePath);

        b = configFile.exists();
        if(!b) configFile.createNewFile();

        BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));
        out.write(idGame);
        if(out != null) out.close();
    }

    public ArrayList<String> loadSavedGames(String id) throws IOException{
        boolean b;
        ArrayList<String> savedGames = null;
        String playerGame;

        String filePath = getDirPath() + id + CONFIG_FILE;
        File configFile = new File(filePath);

        b = configFile.exists();
        if(!b) throw new FileNotFoundException();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while((playerGame = br.readLine()) != null) {
            savedGames.add(playerGame);
        }
        return savedGames;
    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
