package data.tokens;

import java.util.*;

public class Combination
{
    /* ATTRIBUTES */

    protected ArrayList<Integer> bPins;

    /* CONSTRUCTION FUNCTIONS */

    public Combination()
    {

    }

    public Combination(Combination combination)
    {
        setBPins(combination.getBPins());
    }

    /* SET FUNCTIONS */

    public boolean setBPins(ArrayList<Integer> bPins)
    {
        boolean b = bPins.size() == 4;

        bPins = new ArrayList<>(bPins.size());
        this.bPins.addAll(bPins);

        return b;
    }

    /* GET FUNCTIONS */

    public ArrayList<Integer> getBPins()
    {
        return bPins;
    }

    /* TEST FUNCTIONS */

    public boolean isValid()
    {
        return bPins.size() == 4;
    }

    /* SUPERCLASS OVERWRITTEN FUNCTIONS */

    public Object clone() throws CloneNotSupportedException
    {
        Combination combination = (Combination) super.clone();

        combination.setBPins(this.getBPins());

        return combination;
    }
}
