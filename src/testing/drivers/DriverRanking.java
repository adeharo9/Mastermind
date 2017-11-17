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
       testToTopTen();
   }

   private void testAddInAEmptyTopTen()
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

       LinkedList<Pair<String, Integer>> topTen = ranking.getTopTen();


       for(int i = 0; i < topTen.size(); ++i)
       {
           ioUtils.printOutLn(Integer.toString(i+1) + ". Player: " + topTen.get(i).first + " Points: " + topTen.get(i).second);
       }
       ioUtils.endLine();
       this.rankingTest = ranking;
   }

   private void testAddInAFullTopTen()
   {
       ioUtils.printOutLn("Now, we are going to try to add a player and point in a full top ten." +
               " Introduce a pair of id and point which is in top ten: ");

       String id = ioUtils.input();
       int points = Integer.parseInt(ioUtils.input());
       if(rankingTest.toTopTen(id, points))
       {
           rankingTest.addToTopTen(id, points);
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
       rankingTest.addToTopTen(rankingTest.getTopTen().get(5).first, rankingTest.getTopTen().get(5).second);

       LinkedList<Pair<String, Integer>> topTen = rankingTest.getTopTen();
       for(int i = 0; i < topTen.size(); ++i)
       {
           ioUtils.printOutLn(Integer.toString(i+1) + ". Player: " + topTen.get(i).first + " Points: " + topTen.get(i).second);
       }
       ioUtils.endLine();
   }

   private void testToTopTen()
   {
       ioUtils.printOutLn("Now, we're going to test the function toTopTen that returns " +
               "if a pair of player and point are in the top ten.\n");
       ioUtils.printOutLn("Introduce a pair of id and point: ");
       String id = ioUtils.input();
       int points = Integer.parseInt(ioUtils.input());
       if(rankingTest.toTopTen(id, points))
       {
           ioUtils.printOutLn("This pair should be in the top ten");
       }
       else{ioUtils.printOutLn("This pair shouldn't be in the top ten");}
   }

}
