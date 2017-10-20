package persistence;

import domain.classes.Game;

public class GamePersistence extends AbstractPersistence
{
    public GamePersistence()
    {

    }

    public Game load(Object id)
    {
        return new Game();
    }

    public boolean save(Object game)
    {
        return true;
    }
}
