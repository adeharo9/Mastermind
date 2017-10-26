package persistence;

import domain.classes.Game;
import exceptions.IntegrityCorruption;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class GamePersistence extends AbstractPersistence
{
    private void saveGame(Game game) throws IOException
    {
        boolean b;
        String fileName = Integer.toString((game).getId());

        File gameDirectory = new File(getDirPath());
        File gameFile = new File(getFilePath(fileName));

        b = gameDirectory.mkdirs();

        b = gameFile.exists();
        if(b) throw new FileAlreadyExistsException(getFilePath(fileName));

        b = gameFile.createNewFile();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(gameFile));
        oos.writeObject(game);
        oos.close();
    }

    public GamePersistence()
    {

    }

    public boolean exists(String key)
    {
        File fileGame = new File(getFilePath(key));
        return fileGame.exists();
    }

    public String getDirPath()
    {
        return basePath + gamePath;
    }

    public Game load(String id) throws IOException, ClassNotFoundException
    {
        boolean b = exists(id);
        if(!b) throw new FileNotFoundException();

        File gameFile = new File(getFilePath(id));

        FileInputStream in = new FileInputStream(gameFile);
        ObjectInputStream s = new ObjectInputStream(in);

        return (Game) s.readObject();
    }

    public void save(Object game) throws IOException
    {
        saveGame((Game) game);
    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
