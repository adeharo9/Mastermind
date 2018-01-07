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
        ioUtils.printOutLn("Test addInEmptyTopTen");
        ioUtils.printOutLn("Introduce a sequence of 10 of id and points (Input example: " +
                "alex 1000 pep 30 juan 2000 ...):");
        Ranking ranking = new Ranking();

        for(int i = 0; i < 10; ++i)
        {
            String id = ioUtils.input();
            int points = Integer.parseInt(ioUtils.input());
            ranking.addToTopTen(id, points);
        }

        RankingPersistence rankingPersistence = new RankingPersistence();

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
