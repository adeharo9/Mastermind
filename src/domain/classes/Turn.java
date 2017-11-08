package domain.classes;

import enums.Color;
import enums.Difficulty;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Turn extends Code implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private ArrayList<Color> sPins;

    /* CONSTRUCTION METHODS */

    @Deprecated
    public Turn()
    {
        super();
        sPins = new ArrayList<>();
    }

    @Deprecated
    public Turn(int n) throws IllegalArgumentException
    {
        super(n);
        sPins = new ArrayList<>(n);
    }

    public Turn(Difficulty difficulty)
    {
        super(difficulty);
        sPins = new ArrayList<>();
    }

    public Turn(Turn turn) throws IllegalArgumentException, NullPointerException
    {
        super(turn);

        setSPins(turn.getSPins());
    }

    /* SET METHODS */

    public void setSPins(ArrayList<Color> sPins) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValid(sPins);
        if(!b) throw new IllegalArgumentException();

        this.sPins = new ArrayList<>(sPins.size());
        this.sPins.addAll(sPins);
    }

    /* GET METHODS */

    public ArrayList<Color> getSPins()
    {
        return sPins;
    }

    public Color getSPinAt(int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return sPins.get(i);
    }

    /* CONSULTING METHODS */

    public int size() throws NullPointerException
    {
        return sPins.size();
    }

    /* TESTING METHODS */

    @Deprecated
    private boolean isValid(ArrayList<Color> sPins) throws NullPointerException
    {
        return super.size() == sPins.size();
    }

    @Deprecated
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