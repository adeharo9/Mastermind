package persistence;

import exceptions.IntegrityCorruption;

import java.io.IOException;

public abstract class AbstractPersistence
{

    static String basePath = "./Mastermind/";
    static String usersPath = "users/";
    static String gamesPath = "games/";

    public abstract boolean exists(String key);

    public abstract Object load(String key) throws IOException;

    public abstract void save(Object object) throws IOException;

    public abstract void delete(String key) throws IOException;

    public static void checkIntegrity(int hash, Object object) throws IntegrityCorruption
    {

    }
}
