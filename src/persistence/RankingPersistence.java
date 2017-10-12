package persistence;

import domain.classes.Ranking;

public class RankingPersistence extends AbstractPersistence
{
    public RankingPersistence()
    {

    }

    public Ranking load()
    {
        return new Ranking();
    }

    public boolean save(Object ranking)
    {
        return true;
    }
}
