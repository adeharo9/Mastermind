package domain.classes;

import enums.Color;
import enums.Difficulty;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Combination implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    protected ArrayList<Color> bPins;

    /* CONSTRUCTION METHODS */

    @Deprecated
    public Combination()
    {
        bPins = new ArrayList<>();
    }

    @Deprecated
    public Combination(int n) throws IllegalArgumentException
    {
        bPins = new ArrayList<>(n);
    }

    public Combination(Difficulty difficulty) throws IllegalArgumentException
    {
        bPins = new ArrayList<>();
    }

    public Combination(Combination combination) throws IllegalArgumentException, NullPointerException
    {
        setBPins(combination.getBPins());
    }

    /* SET METHODS */

    public void setBPins(ArrayList<Color> bPins) throws IllegalArgumentException, NullPointerException
    {
        boolean b = Utils.isValidArrayList(bPins);
        if(!b) throw new IllegalArgumentException();

        bPins = new ArrayList<>(bPins.size());
        this.bPins.addAll(bPins);
    }

    /* GET METHODS */

    public ArrayList<Color> getBPins()
    {
        return bPins;
    }

    public Color getBPinAt(int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return bPins.get(i);
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

    @Deprecated
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