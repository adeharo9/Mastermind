package persistence;

import exceptions.IntegrityCorruption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public abstract class AbstractPersistence
{

    protected static String basePath = "./Mastermind/";
    protected static String usersPath = "users/";
    protected static String gamesPath = "games/";
    protected static String playerPath = "player/";

    public abstract boolean exists(String key);

    public abstract Object load(String key) throws IOException, ClassNotFoundException;

    public abstract void save(Object object) throws IOException, FileAlreadyExistsException;

    public abstract String getPath(String key);

    public void delete(String key) throws IOException{

        if(!exists(key)) throw new FileNotFoundException();
        else {
            String path = getPath(key);
            File file = new File(path);
            boolean b = file.delete();
            if (!b) throw new IOException();
        }
    }

    public static void checkIntegrity(int hash, Object object) throws IntegrityCorruption
    {

    }
}
