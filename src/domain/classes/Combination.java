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

    public Combination(int n) throws IllegalArgumentException
    {
        bPins = new ArrayList<>(n);
    }

    public Combination(Combination combination) throws IllegalArgumentException, NullPointerException
    {
        setBPins(combination.getBPins());
    }

    /* SET METHODS */

    public void setBPins(ArrayList<Integer> bPins) throws IllegalArgumentException, NullPointerException
    {
        boolean b = Utils.isValidArrayList(bPins);
        if(!b) throw new IllegalArgumentException();

        bPins = new ArrayList<>(bPins.size());
        this.bPins.addAll(bPins);
    }

    /* GET METHODS */

    public ArrayList<Integer> getBPins()
    {
        return bPins;
    }

    /* CONSULTING METHODS */

    public int size() throws NullPointerException
    {
        return bPins.size();
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

    public Combination deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Combination(this);
    }
}