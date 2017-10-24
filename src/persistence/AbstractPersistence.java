package persistence;

import exceptions.FileAlreadyExists;
import exceptions.FileDoesNotExist;
import exceptions.IntegrityCorruption;

public abstract class AbstractPersistence
{

    static String basePath = "./Mastermind/";
    static String usersPath = "users/";
    static String gamesPath = "games/";

    public abstract boolean exists(String key);

    public abstract Object load(String key) throws FileDoesNotExist;

    public abstract boolean save(Object object) throws FileAlreadyExists;

    public abstract void delete(String key) throws FileDoesNotExist;

    public static void checkIntegrity(int hash, Object object) throws IntegrityCorruption
    {

    }
}
