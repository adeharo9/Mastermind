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

    private static boolean isValidNColumns(int nColumns)
    {
        return nColumns > 0;
    }

    private static boolean isValidMaxAttempts(int maxAttempts)
    {
        return maxAttempts > 0;
    }

    private static boolean isValidTurnSet(ArrayList<Turn> turnSet) throws NullPointerException
    {
        boolean b = turnSet.isEmpty();

        if(!b)
        {
            for(Turn turn : turnSet)
            {
                b = turn.isValid();
                if(!b) return b;
            }
        }

        return b;
    }

    private boolean isValidComb (Combination combination) throws NullPointerException
    {
        return combination.size () == nColumns;
    }

    /* CONSTRUCTION METHODS */

    public Board ()
    {
        nColumns = -1;
        maxAttempts = -1;
        code = null;
        turnSet = null;
    }

    public Board (Board board) throws IllegalArgumentException, NullPointerException
    {
        setNColumns(board.getNColumns());
        setMaxAttempts (board.getMaxAttempts());
        setCode(board.getCode());
        setTurnSet(board.getTurnSet());
    }

    /* SET METHODS */

    public void setNColumns(int nColumns) throws IllegalArgumentException
    {
        boolean b = isValidNColumns(nColumns);
        if(!b) throw new IllegalArgumentException();

        this.nColumns = nColumns;
    }

    public void setMaxAttempts(int maxAttempts) throws IllegalArgumentException
    {
        boolean b = isValidMaxAttempts(maxAttempts);
        if(!b) throw new IllegalArgumentException();

        this.maxAttempts = maxAttempts;
    }

    public void setCode(Code code) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidComb(code);
        if(!b) throw new IllegalArgumentException();

        this.code = code.deepCopy();
    }

    public void setTurnSet(ArrayList<Turn> turnSet) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidTurnSet(turnSet);
        if(!b) throw new IllegalArgumentException();

        this.turnSet = new ArrayList<>(turnSet.size());

        for(Turn turn : turnSet)
        {
            addTurn(turn);
        }
    }

    public void addTurn(Turn turn) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidComb(turn);
        if(!b) throw new IllegalArgumentException();

        if(turnSet == null)
        {
            turnSet = new ArrayList<>();
        }

        turnSet.add(turn.deepCopy());
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

    public Turn getTurn(int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return turnSet.get(i);
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

    public Board deepCopy () throws IllegalArgumentException, NullPointerException
    {
        return new Board (this);
    }
}
