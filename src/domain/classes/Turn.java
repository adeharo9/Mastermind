package domain.classes;

import util.*;
import java.util.*;

public class Turn extends Combination implements DeepCopyable
{
    /* ATTRIBUTES */

    private ArrayList<Integer> sPins;

    /* CONSTRUCTION METHODS */

    public Turn()
    {
        super();
        sPins = new ArrayList<>();
    }

    public Turn(int n)
    {
        super(n);

        try
        {
            if(n >= 0)
            {
                sPins = new ArrayList<>(n);
            }
            else
            {
                throw new Exception("Exception thrown on Turn(int n): n must be >= 0");
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    public Turn(Turn turn)
    {
        super(turn);

        boolean b;

        try
        {
            b = setSPins(turn.getSPins());
            if(!b) throw new Exception("Exception thrown on Turn(Turn turn): error when executing setSPins()");
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    /* SET METHODS */

    public boolean setSPins(ArrayList<Integer> sPins)
    {
        boolean b = isValid(sPins);

        if(b)
        {
            this.sPins = new ArrayList<>(sPins.size());
            this.sPins.addAll(sPins);
        }

        return b;
    }

    /* GET METHODS */

    public ArrayList<Integer> getSPins()
    {
        ArrayList<Integer> sPins;
        boolean b = isValid();

        if(b)
        {
            sPins = this.sPins;
        }
        else
        {
            sPins = new ArrayList<>();
        }

        return sPins;
    }

    /* TESTING METHODS */

    public boolean isValid(ArrayList<Integer> sPins)
    {
        return isValid(sPins.size());
    }

    public boolean isValid()
    {
        return isValid(sPins);
    }

    /* CLONING METHODS */

    public Turn deepCopy()
    {
        return new Turn(this);
    }
}