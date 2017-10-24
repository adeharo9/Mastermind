package persistence;

import domain.classes.Human;
import domain.classes.Player;
import exceptions.FileDoesNotExist;

public class PlayerPersistence extends AbstractPersistence
{
    public PlayerPersistence()
    {

    }

    public boolean exists(String key)
    {
        return true;
    }

    public Player load(String key) throws FileDoesNotExist
    {
        boolean b = exists(key);
        if(!b) throw new FileDoesNotExist();

        int id = Integer.parseInt(key);
        return new Human(id);
    }

    public boolean save(Object player)
    {
        return true;
    }
}
