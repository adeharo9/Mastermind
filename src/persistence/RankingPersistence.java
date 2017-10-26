package persistence;

import domain.classes.Ranking;
import exceptions.IntegrityCorruption;

import java.io.FileNotFoundException;

public abstract class RankingPersistence extends AbstractPersistence
{
    public RankingPersistence()
    {

    }

    public boolean exists(String key)
    {
        return true;
    }

    public Ranking load(String key)
    {
        return new Ranking();
    }

    public void save(Object ranking)
    {
    }

    public void delete(String key) throws FileNotFoundException
    {

    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
