package persistence;

import domain.classes.Human;
import domain.classes.Player;

public class PlayerPersistence extends AbstractPersistence
{
    public PlayerPersistence()
    {

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
