package testing.drivers;

import domain.classes.Ranking;
import persistence.RankingPersistence;
import util.Pair;
import util.ioUtils;

import java.io.IOException;
import java.util.LinkedList;

public class DriverRankingPersistence {

    private Ranking ranking;

    public static void main(String[] args){
        DriverRankingPersistence driverRankingPersistence = new DriverRankingPersistence();
        driverRankingPersistence.exe();
    }

    private void exe()
    {
        testSaveRanking();
        testLoadRanking();
    }

    private void testSaveRanking()
    {
        Ranking ranking = new Ranking();
        RankingPersistence rankingPersistence = new RankingPersistence();

        ranking.addToTopTen("alex", 1000);
        ranking.addToTopTen("juan", 2000);
        ranking.addToTopTen("pepe", 300);
        ranking.addToTopTen("manuel", 2343);
        ranking.addToTopTen("lsd", 1818);
        ranking.addToTopTen("josejuan", 10000);
        ranking.addToTopTen("marco", 923);
        ranking.addToTopTen("maria", 48517);
        ranking.addToTopTen("lope", 151);
        ranking.addToTopTen("narciso", 5151);

        try
        {
            rankingPersistence.save(ranking);
        }
        catch (IOException e)
        {
            ioUtils.printOutLn("Test not passed. Error in saveRanking. Possibly because of ranking already exists.");
            ioUtils.endLine();
        }

        if(rankingPersistence.exists("ranking"))
        {
            ioUtils.printOutLn("The ranking has saved correctly!");
            ioUtils.endLine();
            this.ranking = ranking;
        }
        else
        {
            ioUtils.printOutLn("Test not passed. Error in saveRanking.");
            ioUtils.endLine();
        }
    }

    private void testLoadRanking()
    {
        RankingPersistence rankingPersistence = new RankingPersistence();
        boolean error = false;
        Ranking rankingLoaded = new Ranking();

        try
        {
            rankingLoaded = rankingPersistence.load("ranking");
        }
        catch(IOException | ClassNotFoundException e)
        {
            error = true;
        }

        if(!error)
        {
            ioUtils.printOutLn("The ranking has loaded correctly!");
            ioUtils.endLine();

            LinkedList<Pair<String, Integer>> topTen = rankingLoaded.getTopTen();
            for(int i = 0; i < topTen.size(); ++i)
            {
                ioUtils.printOutLn(Integer.toString(i+1) + ". Player: " + topTen.get(i).first + " Points: " + topTen.get(i).second);
            }
            ioUtils.endLine();
        }
        else
        {
            ioUtils.printOutLn("Test not passed. Error in the load of the ranking!");
        }

    }
}
