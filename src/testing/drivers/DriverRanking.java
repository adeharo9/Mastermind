package testing.drivers;

import domain.classes.Ranking;
import util.*;

import java.util.LinkedList;

public class DriverRanking {

    private Ranking rankingTest;

   public static void main(String[] args)
   {
       DriverRanking driverRanking = new DriverRanking();
       driverRanking.exe();
   }

   private void exe()
   {
       testAddInAEmptyTopTen();
       testAddInAFullTopTen();
       testAddARepeatedPlayer();
       testInTopTen();
   }

   private void testAddInAEmptyTopTen()
   {
       Ranking ranking = new Ranking();

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

       LinkedList<Pair<String, Integer>> topTen = ranking.getTopTen();

       ioUtils.printOutLn("Add 10 players and points in a empty Ranking.");

       for(int i = 0; i < topTen.size(); ++i)
       {
           ioUtils.printOutLn(Integer.toString(i+1) + ". Player: " + topTen.get(i).first + " Points: " + topTen.get(i).second);
       }
       ioUtils.endLine();
       this.rankingTest = ranking;
   }

   private void testAddInAFullTopTen()
   {
       ioUtils.printOutLn("Now, we going to try to add a player and point in a full top ten: ");

       if(rankingTest.toTopTen("juanfran", 600))
       {
           rankingTest.addToTopTen("juanfran", 600);
       }

       LinkedList<Pair<String, Integer>> topTen = rankingTest.getTopTen();

       for(int i = 0; i < topTen.size(); ++i)
       {
           ioUtils.printOutLn(Integer.toString(i+1) + ". Player: " + topTen.get(i).first + " Points: " + topTen.get(i).second);
       }
       ioUtils.endLine();
   }

   private void testAddARepeatedPlayer()
   {
       ioUtils.printOutLn("Now, we're going to check the behavior of the " +
               "add function when the pair introduced is already in the top ten.\n");
       rankingTest.addToTopTen("juanfran", 600);

       LinkedList<Pair<String, Integer>> topTen = rankingTest.getTopTen();
       for(int i = 0; i < topTen.size(); ++i)
       {
           ioUtils.printOutLn(Integer.toString(i+1) + ". Player: " + topTen.get(i).first + " Points: " + topTen.get(i).second);
       }
       ioUtils.endLine();
   }

   private void testInTopTen()
   {
       ioUtils.printOutLn("Now, we're going to test the function toTopTen that returns " +
               "if a pair of player and point are in the top ten.\n");
       LinkedList<Pair<String, Integer>> topTen = rankingTest.getTopTen();
       boolean error = false;
       for(int i = 0; i < topTen.size() - 1; ++i)
       {
           Pair<String, Integer> playerPoints = topTen.get(i);
           if(!rankingTest.toTopTen(playerPoints.first, playerPoints.second)){
               error = true;
               break;
           }
       }
       if(error)
       {
           ioUtils.printOutLn("Test not passed!");
       }
       else
       {
           ioUtils.printOutLn("The function toTopTen works correctly!");
       }
   }

}
