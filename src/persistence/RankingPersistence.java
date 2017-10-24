package persistence;

import domain.classes.Ranking;

public class RankingPersistence extends AbstractPersistence
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

    public boolean save(Object ranking)
    {
        return true;
    }
}
