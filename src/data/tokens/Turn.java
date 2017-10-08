package data.tokens;

import java.util.*;

public class Turn extends Combination
{
    /* ATTRIBUTES */

    private ArrayList<Integer> sPins;

    /* CONSTRUCTION FUNCTIONS */

    public Turn()
    {

    }

    public Turn(Turn turn)
    {
        setBPins(turn.getBPins());
        setSPins(turn.getSPins());
    }

    /* SET FUNCTIONS */

    public boolean setSPins(ArrayList<Integer> sPins)
    {
        boolean b = sPins.size() == 4;

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
        return sPins;
    }

    /* CLONING FUNCTIONS */

    public Turn cClone()
    {
        return new Turn(this);
    }
}
