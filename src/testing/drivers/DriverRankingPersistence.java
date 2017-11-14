package testing.drivers;

import domain.classes.Ranking;
import persistence.AbstractPersistence;
import persistence.RankingPersistence;
import util.ioUtils;

import java.io.IOException;

public class DriverRankingPersistence {

    public static void main(String[] args){
        DriverRankingPersistence driverRankingPersistence = new DriverRankingPersistence();
        driverRankingPersistence.exe();
    }

    private void exe()
    {
        testSaveRanking();
    }

    private void testSaveRanking()
    {
        Ranking ranking = new Ranking();
        RankingPersistence rankingPersistence = new RankingPersistence();

        ranking.addInTopTen("alex", 1000);
        ranking.addInTopTen("juan", 2000);
        ranking.addInTopTen("pepe", 300);
        ranking.addInTopTen("manuel", 2343);
        ranking.addInTopTen("lsd", 1818);
        ranking.addInTopTen("josejuan", 10000);
        ranking.addInTopTen("marco", 923);
        ranking.addInTopTen("maria", 48517);
        ranking.addInTopTen("lope", 151);
        ranking.addInTopTen("narciso", 5151);

        try
        {
            rankingPersistence.save(ranking);
        }
        catch (IOException e)
        {
            ioUtils.printOutLn("Test not passed. Error in saveRanking. Possibly because of ranking already exists.");
        }

        if(rankingPersistence.exists("ranking"))
        {
            ioUtils.printOutLn("The ranking has saved correctly!");
        }
        else
        {
            ioUtils.printOutLn("Test not passed. Error in saveRanking.");
        }
    }
}
