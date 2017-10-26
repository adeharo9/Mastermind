package persistence;

import exceptions.IntegrityCorruption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AbstractPersistence
{

    protected final static String basePath = "./";
    protected final static String usersPath = "users/";
    protected final static String gamePath = "games/";
    protected final static String playerPath = "players/";
    protected final static String fileExtension = ".mm";

    public abstract boolean exists(String key);

    public abstract String getDirPath();

    public String getFilePath(String key)
    {
        return getDirPath() + key + fileExtension;
    }

    public abstract Object load(String key) throws IOException, ClassNotFoundException;

    public abstract void save(Object object) throws IOException;

    public void delete(String key) throws IOException
    {
        boolean b = exists(key);
        if(!b) throw new FileNotFoundException();

        String path = getFilePath(key);
        File file = new File(path);

        b = file.delete();
        if (!b) throw new IOException();
    }

    public static void checkIntegrity(int hash, Object object) throws IntegrityCorruption
    {

    }
}
