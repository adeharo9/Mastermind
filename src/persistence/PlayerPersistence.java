package persistence;

import domain.classes.Player;

public class PlayerPersistence extends AbstractPersistence
{
    public PlayerPersistence()
    {

    }

    public Player load(Object object)
    {
        return new Player();
    }

    public boolean save(Object player)
    {
        return true;
    }
}
