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

    public Turn(int n) throws IllegalArgumentException
    {
        super(n);
        sPins = new ArrayList<>(n);
    }

    public Turn(Turn turn) throws IllegalArgumentException, NullPointerException
    {
        super(turn);

        setSPins(turn.getSPins());
    }

    /* SET METHODS */

    public void setSPins(ArrayList<Integer> sPins) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValid(sPins);
        if(!b) throw new IllegalArgumentException();

        this.sPins = new ArrayList<>(sPins.size());
        this.sPins.addAll(sPins);
    }

    /* GET METHODS */

    public ArrayList<Integer> getSPins()
    {
        return sPins;
    }

    /* CONSULTING METHODS */

    public int size() throws NullPointerException
    {
        return sPins.size();
    }

    /* TESTING METHODS */

    public boolean isValid(ArrayList<Integer> sPins) throws NullPointerException
    {
        return super.size() == sPins.size();
    }

    public boolean isValid() throws NullPointerException
    {
        return isValid(sPins);
    }

    /* CLONING METHODS */

    public Turn deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Turn(this);
    }
}