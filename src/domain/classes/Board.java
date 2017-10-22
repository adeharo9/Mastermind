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

    private static boolean isValidNColumns(int nColumns)
    {
        return nColumns > 0;
    }

    private static boolean isValidMaxAttempts(int maxAttempts)
    {
        return maxAttempts > 0;
    }

    private boolean isValidTurnSet()
    {
        boolean b = turnSet != null;

        if(b)
        {
            if(!turnSet.isEmpty())
            {
                for(Turn turn : turnSet)
                {
                    b = turn.isValid();
                    if(!b) return b;
                }
            }
        }

        return b;
    }

    private boolean isValidComb (Combination combination)
    {
        return combination != null && combination.size () == nColumns;
    }

    /* CONSTRUCTION METHODS */

    public Board ()
    {
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
        boolean b = isValidNColumns(nColumns);

        if (b)
        {
            this.nColumns = nColumns;
        }

        return b;
    }

    public boolean setMaxAttempts (int maxAttempts)
    {
        boolean b = isValidMaxAttempts(maxAttempts);

        if (b)
        {
            this.maxAttempts = maxAttempts;
        }

        return b;
    }

    public boolean setCode (Code code)
    {
        boolean b = isValidComb(code);

        if (b)
        {
            this.code = code.deepCopy();
        }

        return b;
    }

    public boolean setTurnSet(ArrayList<Turn> turnSet)
    {
        boolean b = turnSet != null;

        if(b)
        {
            this.turnSet = new ArrayList<>(turnSet.size());

            for (Turn turn : turnSet) {
                b = isValidComb(turn);
                if(!b) return b;

                this.turnSet.add(turn.deepCopy());
            }
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

    public Turn getTurn(int i)
    {
        Turn turn = null;
        boolean b = turnSet != null && i >= 0 && i < turnSet.size();

        if(b)
        {
            turn = turnSet.get(i);
        }

        return turn;
    }

    /* TESTING METHODS */

    public boolean isValid()
    {
        boolean b;

        b = isValidNColumns(nColumns);
        if(!b) return b;

        b = isValidMaxAttempts(maxAttempts);
        if(!b) return b;

        b = code != null;
        if(!b) return b;

        b = turnSet != null;

        return b;
    }

    /* COPY METHODS */

    public Board deepCopy ()
    {
        return new Board (this);
    }
}
