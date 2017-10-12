package persistence;

public abstract class AbstractPersistence
{
    public abstract Object load();

    public abstract boolean save(Object object);
}
