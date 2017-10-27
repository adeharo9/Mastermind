package persistence;

import domain.classes.Human;
import domain.classes.Player;
import exceptions.IntegrityCorruption;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;


public class PlayerPersistence extends AbstractPersistence
{
    private void savePlayer(Player player) throws IOException
    {
        boolean b;
        String nameFile = (player).getId();
        File directoryPlayer = new File(getDirPath());
        File filePlayer = new File(getFilePath(nameFile));

        b = directoryPlayer.mkdirs();

        b = filePlayer.exists();
        if(b) throw new FileAlreadyExistsException(getFilePath(nameFile));

        b = filePlayer.createNewFile();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePlayer));
        oos.writeObject(player);
        oos.close();
    }

    public PlayerPersistence()
    {

    }

    public boolean exists(String key)
    {
        File filePlayer = new File(basePath + playerPath + key + fileExtension);
        return filePlayer.exists();
    }

    public String getDirPath()
    {
        return basePath + playerPath;
    }

    public Player load(String id) throws IOException, ClassNotFoundException
    {
        boolean b = exists(id);
        if(!b) throw new FileNotFoundException();

        File playerFile = new File(getFilePath(id));

        FileInputStream in = new FileInputStream(playerFile);
        ObjectInputStream s = new ObjectInputStream(in);

        return (Human) s.readObject();
    }

    public void save(Object player) throws IOException
    {
        savePlayer((Player) player);
    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
