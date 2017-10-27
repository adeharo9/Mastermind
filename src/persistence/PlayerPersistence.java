package persistence;

import domain.classes.Human;
import domain.classes.Player;
import exceptions.IntegrityCorruption;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;


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
        //savePlayer((Player) player);
        String id = ((Player) player).getId();
        super.save(id, player);
    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
