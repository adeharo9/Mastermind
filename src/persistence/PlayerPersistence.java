package persistence;

import domain.classes.Human;
import domain.classes.Player;
import exceptions.IntegrityCorruption;

import java.io.FileNotFoundException;

public class PlayerPersistence extends AbstractPersistence
{
    public PlayerPersistence()
    {

    }

    public boolean exists(String key)
    {
        return true;
    }

    public Player load(String key) throws FileNotFoundException
    {
        boolean b = exists(key);
        if(!b) throw new FileNotFoundException();

        return new Human();
    }

    public void save(Object player)
    {
    }

    public void delete(String key) throws FileNotFoundException
    {

    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
