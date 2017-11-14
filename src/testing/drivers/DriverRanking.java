package testing.drivers;

import domain.classes.Ranking;
import util.*;

import java.util.LinkedList;

public class DriverRanking {

   public static void main(String[] args)
   {
       DriverRanking driverRanking = new DriverRanking();
       driverRanking.exe();
   }

   private void exe()
   {
       testAddInTopTen();
   }

   private void testAddInTopTen()
   {
       Ranking ranking = new Ranking();

       ranking.addInTopTen("alex", 1000);
       ranking.addInTopTen("juan", 2000);
       ranking.addInTopTen("pepe", 300);
       ranking.addInTopTen("manuel", 2343);
       ranking.addInTopTen("lsd", 1818);

       LinkedList<Pair<String, Integer>> topTen = ranking.getTopTen();

       for(int i = 0; i < topTen.size(); ++i)
       {
           ioUtils.printOutLn(Integer.toString(i+1) + ". player: " + topTen.get(i).first + " points: " + topTen.get(i).second);
       }
   }
}
