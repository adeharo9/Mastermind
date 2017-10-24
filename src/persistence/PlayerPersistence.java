package persistence;

import domain.classes.Human;
import domain.classes.Player;

public class PlayerPersistence extends AbstractPersistence
{
    public PlayerPersistence()
    {

    }

    public boolean exists(String key)
    {
        return true;
    }

    public Player load(Object object)
    {
        Player player = new Human();
        return player;
    }

    public boolean save(Object player)
    {
        return true;
    }
}
