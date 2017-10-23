package domain.classes;

import util.*;
import java.util.*;

public class Board implements DeepCopyable
{
    /* ATTRIBUTES */

    private int nColumns;
    private int maxAttempts;

    public static int getNColumnsByDifficulty(Difficulty difficulty)
    {
        int nColumns;

        switch(difficulty)
        {
            case easy:
                nColumns = 4;
                break;
            case medium:
                nColumns = 4;
                break;
            case hard:
                nColumns = 6;
                break;
            default:
                nColumns = -1;
                break;
        }

        return nColumns;
    }

    public static int getMaxAttemptsByDifficulty(Difficulty difficulty)
    {
        int maxAttempts;

        switch(difficulty)
        {
            case easy:
                maxAttempts = 30;
                break;
            case medium:
                maxAttempts = 25;
                break;
            case hard:
                maxAttempts = 20;
                break;
            default:
                maxAttempts = -1;
                break;
        }

        return maxAttempts;
    }

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

    /*private boolean isValidTurnSet()
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
    }*/

    private boolean isValidComb (Combination combination)
    {
        return combination != null && combination.size () == nColumns;
    }

    /* CONSTRUCTION METHODS */

    public Board ()
    {
        nColumns = -1;
        maxAttempts = -1;
        code = null;
        turnSet = null;
    }

    public Board (Board board) throws Exception
    {
        boolean b = setNColumns(board.getNColumns());
        if (!b) throw new IllegalArgumentException();

        b = setMaxAttempts (board.getMaxAttempts());
        if (!b) throw new IllegalArgumentException();

        b = setCode (board.getCode());
        if (!b) throw new IllegalArgumentException();

        b = setTurnSet (board.getTurnSet());
        if (!b) throw new IllegalArgumentException();
    }

    /* SET METHODS */

    public boolean setNColumns(int nColumns)
    {
        boolean b = isValidNColumns(nColumns);

        if (b)
        {
            this.nColumns = nColumns;
        }

        return b;
    }

    public boolean setMaxAttempts(int maxAttempts)
    {
        boolean b = isValidMaxAttempts(maxAttempts);

        if (b)
        {
            this.maxAttempts = maxAttempts;
        }

        return b;
    }

    public boolean setCode(Code code) throws Exception
    {
        boolean b = isValidComb(code);

        if (b)
        {
            this.code = code.deepCopy();
        }

        return b;
    }

    public boolean setTurnSet(ArrayList<Turn> turnSet) throws Exception
    {
        boolean b = turnSet != null;

        if(b)
        {
            this.turnSet = new ArrayList<>(turnSet.size());

            for (Turn turn : turnSet) {
                b = addTurn(turn);
                if(!b) return b;
            }
        }

        return b;
    }

    public boolean addTurn(Turn turn) throws Exception
    {
        boolean b = turn != null && isValidComb(turn);

        if(b)
        {
            if(turnSet == null)
            {
                turnSet = new ArrayList<>();
            }

            turnSet.add(turn.deepCopy());
        }

        return b;
    }

    /* GET METHODS */

    public int getNColumns()
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

    public Board deepCopy () throws Exception
    {
        return new Board (this);
    }
}
