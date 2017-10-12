package domain.classes;

import util.*;
import java.util.*;

public class Combination implements DeepCopyable
{
    /* ATTRIBUTES */

    protected ArrayList<Integer> bPins;

    /* CONSTRUCTION METHODS */

    public Combination()
    {
        bPins = new ArrayList<>();
    }

    public Combination(int n)
    {
        try
        {
            if(n >= 0)
            {
                bPins = new ArrayList<>(n);
            }
            else
            {
                throw new Exception("Exception thrown on Combination(int n): n must be >= 0");
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    public Combination(Combination combination)
    {
        boolean b;
        try
        {
            b = setBPins(combination.getBPins());

            if(!b) throw new Exception("Exception thrown on Combination(Combination combination): error when executing setBPins()");
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    /* SET METHODS */

    public boolean setBPins(ArrayList<Integer> bPins)
    {
        boolean b = bPins.size() == 4;

        bPins = new ArrayList<>(bPins.size());
        this.bPins.addAll(bPins);

        return b;
    }

    /* GET METHODS */

    public ArrayList<Integer> getBPins()
    {
        return bPins;
    }

    /* CONSULTING METHODS */

    public int size()
    {
        return bPins.size();
    }

    /* TESTING METHODS */

    public boolean isValid(int n)
    {
        return size() == n;
    }

    /* CLONING METHODS */

    public Combination deepCopy()
    {
        return new Combination(this);
    }
}