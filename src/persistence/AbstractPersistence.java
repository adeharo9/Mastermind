package persistence;

public abstract class AbstractPersistence
{

    private static String basePath = "./Mastermind/";
    private static String usersPath = "/users/";

    public abstract Object load(Object object);

    public abstract boolean save(Object object);
}
