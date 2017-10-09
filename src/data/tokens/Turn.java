package data.tokens;

import java.util.*;

public class Turn extends Combination
{
    /* ATTRIBUTES */

    private ArrayList<Integer> sPins;

    /* CONSTRUCTION FUNCTIONS */

    public Turn()
    {
        super();
        sPins = new ArrayList<>();
    }

    public Turn(int n)
    {
        super(n);
        sPins = new ArrayList<>(n);
    }

    public Turn(Turn turn)
    {
        super(turn);
        setSPins(turn.getSPins());
    }

    /* SET FUNCTIONS */

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

    /* GET FUNCTIONS */

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

    /* TESTING FUNCTIONS */

    public boolean isValid()
    {
        return isValid(sPins);
    }

    public boolean isValid(int n)
    {
        return super.size() == n;
    }

    public boolean isValid(ArrayList<Integer> sPins)
    {
        return isValid(sPins.size());
    }

    /* CLONING FUNCTIONS */

    public Turn cClone()
    {
        return new Turn(this);
    }
}
