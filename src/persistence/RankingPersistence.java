package persistence;

import domain.classes.Ranking;
import exceptions.IntegrityCorruptionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RankingPersistence extends AbstractPersistence
{
    public RankingPersistence()
    {

    }

    public boolean exists()
    {
        File fileRanking = new File(getDirPath() + "ranking.mm");
        return fileRanking.exists();
    }

    public boolean exists(String key)
    {
        File fileRanking = new File(getDirPath() + "ranking.mm");
        return fileRanking.exists();
    }

    public String getDirPath()
    {
        return BASE_PATH + RANKING_PATH;
    }

    public Ranking load() throws IOException, ClassNotFoundException
    {
        return (Ranking) super.load("ranking");
    }

    public Ranking load(String key) throws IOException, ClassNotFoundException
    {
        return (Ranking) super.load("ranking");
    }

    public void save(Object ranking) throws IOException
    {
        super.save("ranking", ranking);
    }

    public void delete() throws IOException
    {
        super.delete("ranking");
    }

    public void delete(String key) throws IOException
    {
        super.delete("ranking");
    }

    public void checkIntegrity() throws IntegrityCorruptionException
    {

    }
}
