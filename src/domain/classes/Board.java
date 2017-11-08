package domain.classes;

import enums.Difficulty;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Board implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private int nColumns;
    private int maxAttempts;
    private Difficulty difficulty;

    private Code solution;
    private ArrayList<Turn> turnSet;

    @Deprecated
    private static int getNColumnsByDifficulty(Difficulty difficulty) throws IllegalArgumentException
    {
        int nColumns;

        switch(difficulty)
        {
            case EASY:
                nColumns = 4;
                break;
            case MEDIUM:
                nColumns = 4;
                break;
            case HARD:
                nColumns = 6;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return nColumns;
    }

    @Deprecated
    private static int getMaxAttemptsByDifficulty(Difficulty difficulty) throws IllegalArgumentException
    {
        int maxAttempts;

        switch(difficulty)
        {
            case EASY:
                maxAttempts = 30;
                break;
            case MEDIUM:
                maxAttempts = 25;
                break;
            case HARD:
                maxAttempts = 20;
                break;
            default:
                throw new IllegalArgumentException();
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
                if(!b) break;
            }
        }

        return b;
    }

    @Deprecated
    private boolean isValidComb (Code code) throws NullPointerException
    {
        return code.size () == nColumns;
    }

    /* CONSTRUCTION METHODS */

    @Deprecated
    public Board ()
    {
        nColumns = -1;
        maxAttempts = -1;
        difficulty = null;

        solution = null;
        turnSet = new ArrayList<>();
    }

    public Board(Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        setNColumns(getNColumnsByDifficulty(difficulty));
        setMaxAttempts(getMaxAttemptsByDifficulty(difficulty));
        setDifficulty(difficulty);

        solution = null;
        turnSet = new ArrayList<>();
    }

    public Board (Board board) throws IllegalArgumentException, NullPointerException
    {
        setNColumns(board.getNColumns());
        setMaxAttempts (board.getMaxAttempts());
        setDifficulty(board.getDifficulty());
        setSolution(board.getSolution());
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

    public void setDifficulty(Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        boolean b = difficulty != null;
        if(!b) throw new IllegalArgumentException();

        this.difficulty = difficulty;
    }

    public void setSolution(Code solution) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidComb(solution);
        if(!b) throw new IllegalArgumentException();

        this.solution = solution.deepCopy();
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

    public Difficulty getDifficulty()
    {
        return difficulty;
    }

    public Code getSolution()
    {
        return solution;
    }

    public ArrayList<Turn> getTurnSet ()
    {
        return turnSet;
    }

    public Turn getTurn(int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return turnSet.get(i);
    }

    public Turn getLastTurn()
    {
        Turn turn;

        if(turnSet.isEmpty())
        {
            turn = null;
        }
        else
        {
            turn = getTurn(turnSet.size() - 1);
        }
        return turn;
    }

    public boolean finished() throws NullPointerException
    {
        return turnSet.size() == maxAttempts;
    }

    /* TESTING METHODS */

    public boolean isValid()
    {
        boolean b;

        b = isValidNColumns(nColumns);
        if(!b) return b;

        b = isValidMaxAttempts(maxAttempts);
        if(!b) return b;

        b = difficulty != null;
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
