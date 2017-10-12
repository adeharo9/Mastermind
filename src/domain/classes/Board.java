package domain.classes;

import util.*;
import java.util.*;

public class Board implements DeepCopyable
{
    /* ATTRIBUTES */

    private int nColumns;
    private int maxAttempts;

    private Code code;
    private ArrayList<Turn> turnSet;

    private boolean isValidComb (Combination combination)
    {
        return combination.size () == nColumns;
    }

    /* CONSTRUCTION METHODS */

    public Board ()
    {
        nColumns = 0;
        nColumns = 0;
        maxAttempts = 0;
        code = new Code ();
        turnSet = new ArrayList<> ();
    }

    public Board (Board board)
    {
        boolean b;
        try
        {
            b = setNColors (board.getNColors ());
            if (!b) throw new Exception ("Exception thrown on Board (Board board): error executing setNColors ()");

            b = setMaxAttempts (board.getMaxAttempts ());
            if (!b) throw new Exception ("Exception thrown on Board (Board board): error executing setMaxAttempts ()");

            b = setCode (board.getCode ());
            if (!b) throw new Exception ("Exception thrown on Board (Board board): error executing setCode ()");

            b = setTurnSet (board.getTurnSet ());
            if (!b) throw new Exception ("Exception thrown on Board (Board board): error executing setTurnSet ()");
        }
        catch (Exception e)
        {
            System.err.println (e.getMessage ());
        }
    }

    /* SET METHODS */

    public boolean setNColors (int nColumns)
    {
        boolean b = nColumns > 0;

        if (b)
        {
            this.nColumns = nColumns;
        }

        return b;
    }

    public boolean setMaxAttempts (int maxAttempts)
    {
        boolean b = maxAttempts > 0;

        if (b)
        {
            this.maxAttempts = maxAttempts;
        }

        return b;
    }

    public boolean setCode (Code code)
    {
        boolean b = isValidComb (code);

        if (b)
        {
            this.code = new Code (code);
        }

        return b;
    }

    public boolean setTurnSet (ArrayList<Turn> turnSet)
    {
        boolean b = true;

        this.turnSet = new ArrayList<> (turnSet.size ());

        for (Turn turn : turnSet)
        {
            b &= isValidComb (turn);
            this.turnSet.add (new Turn (turn));
        }

        return b;
    }

    /* GET METHODS */

    public int getNColors ()
    {
        return nColumns;
    }

    public int getMaxAttempts ()
    {
        return maxAttempts;
    }

    public Code getCode ()
    {
        return code;
    }

    public ArrayList<Turn> getTurnSet ()
    {
        return turnSet;
    }

    /* COPY METHODS */

    public Board deepCopy ()
    {
        return new Board (this);
    }
}
