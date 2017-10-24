package persistence;

import domain.classes.Human;
import domain.classes.Player;
import exceptions.FileDoesNotExist;
import exceptions.IntegrityCorruption;

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

        return new Human();
    }

    public boolean save(Object player)
    {
        return true;
    }

    public void delete(String key) throws FileDoesNotExist
    {

    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
