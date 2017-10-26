package persistence;

import domain.classes.Game;
import domain.classes.Human;
import domain.classes.Player;
import exceptions.IntegrityCorruption;

import java.io.*;

public class PlayerPersistence extends AbstractPersistence
{
    public PlayerPersistence()
    {

    }

    public boolean exists(String key)
    {
        File filePlayer = new File(basePath + playerPath + key + ".gm");
        return filePlayer.exists();
    }

    public Player load(String id) throws IOException, ClassNotFoundException
    {
        if(!exists(id)) throw new FileNotFoundException();
        else {
            File filePlayer = new File(basePath + playerPath + id + ".gm");
            Human load = new Human();
            FileInputStream in = new FileInputStream(filePlayer);
            ObjectInputStream s = new ObjectInputStream(in);
            load = (Human) s.readObject();
            return load;
        }
    }

    public void save(Object player) throws IOException
    {
        String nameFile = ((Human) player).getId() + ".gm";
        File filePlayer = new File(basePath + playerPath + nameFile);
        boolean b = filePlayer.mkdirs();
        if(!b) throw new FileNotFoundException();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePlayer));
        oos.writeObject((Human) player);
        oos.close();
    }

    public String getPath(String key)
    {
        return basePath + playerPath + key +".gm";
    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
