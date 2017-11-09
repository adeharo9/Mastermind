package persistence;

import domain.classes.Ranking;
import exceptions.IntegrityCorruption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class RankingPersistence extends AbstractPersistence
{
    public RankingPersistence()
    {

    }

    public boolean exists(String key)
    {
        File fileRanking = new File(getDirPath() + RANKING_PATH + "ranking.mm");
        return fileRanking.exists();
    }

    public String getDirPath()
    {
        return BASE_PATH + RANKING_PATH;
    }

    public Ranking load(String key) throws IOException, ClassNotFoundException
    {
        return (Ranking) super.load("ranking");
    }

    public void save(Object ranking) throws IOException
    {
        super.save("ranking", ranking);
    }

    public void delete(String key) throws FileNotFoundException
    {

    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
