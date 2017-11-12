package domain.classes;

import util.*;

import java.io.Serializable;
import java.util.*;

public class Ranking implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private LinkedList<Pair<String, Integer>> topTen;

    /* CONSTRUCTION METHODS */

    public Ranking()
    {

    }

    public Ranking(Ranking ranking)
    {

    }

    public Ranking(LinkedList<Pair<String, Integer>> topTen)
    {
        this.topTen = topTen;
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    public boolean inTopTen(String idPlayer, Integer points)
    {
        return ((topTen.getLast().second < points) || (topTen.size() < Constants.RANKING_SIZE));
    }

    public void addInTopTen(String idPlayer, Integer points)
    {
        int i = correctPosition(points);
        Pair<String, Integer> toAdd = new Pair<>(idPlayer, points);
        topTen.add(i, toAdd);
        if(topTen.size() > Constants.RANKING_SIZE) topTen.pop();
    }

    private int correctPosition(Integer points)
    {
        int left = 0;
        int right = topTen.size() - 1;
        int mid, cmp;

        while (left <= right)
        {
            mid = (left + right) / 2;
            Integer midVal = topTen.get(mid).second;
            cmp = midVal.compareTo(points);

            if (cmp > 0)
            {
                left = mid + 1;
            }

            else if (cmp < 0)
            {
                right = mid - 1;
            }

            else
            {
                return mid;
            }
        }

        return left;
    }

    /* CLONING METHODS */

    public Ranking deepCopy()
    {
        return new Ranking(this);
    }
}