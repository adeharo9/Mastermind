package persistence;

public abstract class AbstractPersistence
{

    static String basePath = "./Mastermind/";
    static String usersPath = "users/";
    static String gamesPath = "games/";

    public abstract Object load(Object object);

    public abstract boolean save(Object object);
}
