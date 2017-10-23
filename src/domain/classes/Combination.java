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
        bPins = null;
    }

    public Combination(int n)
    {
        try
        {
            bPins = new ArrayList<>(n);
        }
        catch(IllegalArgumentException e)
        {
            ioUtils.printErrLn(e.getMessage());
        }
    }

    public Combination(Combination combination)
    {
        try
        {
            boolean b = setBPins(combination.getBPins());
            if(!b) throw new IllegalArgumentException("");
        }
        catch(Exception e)
        {
            ioUtils.printErrLn(e.getMessage());
        }
    }

    /* SET METHODS */

    public boolean setBPins(ArrayList<Integer> bPins)
    {
        boolean b = bPins != null && bPins.size() > 0;

        if(b)
        {
            bPins = new ArrayList<>(bPins.size());
            this.bPins.addAll(bPins);
        }

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
        int size;

        if(bPins != null)
        {
            size = bPins.size();
        }
        else
        {
            size = 0;
        }

        return size;
    }

    /* TESTING METHODS */

    public boolean isValid()
    {
        return bPins != null;
    }

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