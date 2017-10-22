package persistence;

public abstract class AbstractPersistence
{

    public static String basePath = "./Mastermind/";
    public static String usersPath = "users/";
    public static String gamesPath = "games/";

    public abstract Object load(Object object);

    public abstract boolean save(Object object);
}
