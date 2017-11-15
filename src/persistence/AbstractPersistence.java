package persistence;

import exceptions.IntegrityCorruptionException;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public abstract class AbstractPersistence
{

    protected final static String BASE_PATH = "./";
    protected final static String USERS_PATH = "users/";
    protected final static String GAME_PATH = "games/";
    protected final static String PLAYERS_PATH = "players/";
    protected final static String RANKING_PATH = "ranking/";
    protected final static String GAME_EXTENSION = ".mm";
    protected final static String CONFIG_FILE = "config.cfg";

    public abstract boolean exists(String key);

    public abstract String getDirPath();

    public String getFilePath(String key)
    {
        return getDirPath() + key + GAME_EXTENSION;
    }

    public Object load(String key) throws IOException, ClassNotFoundException
    {
        boolean b = exists(key);
        if(!b) throw new FileNotFoundException();

        File gameFile = new File(getFilePath(key));

        FileInputStream in = new FileInputStream(gameFile);
        ObjectInputStream s = new ObjectInputStream(in);

        return s.readObject();
    }

    public void save(String objectName, Object object) throws IOException
    {
        boolean b;

        File gameDirectory = new File(getDirPath());
        File gameFile = new File(getFilePath(objectName));

        b = gameDirectory.mkdirs();

        b = gameFile.exists();
        if(b) throw new FileAlreadyExistsException(getFilePath(objectName));

        b = gameFile.createNewFile();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(gameFile));
        oos.writeObject(object);
        oos.close();
    }

    public void delete(String key) throws IOException
    {
        boolean b = exists(key);
        if(!b) throw new FileNotFoundException();

        String path = getFilePath(key);
        File file = new File(path);

        b = file.delete();
        if (!b) throw new IOException();
    }

    public static void checkIntegrity(int hash, Object object) throws IntegrityCorruptionException
    {

    }
}
