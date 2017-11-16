package domain.classes;

import enums.Difficulty;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Board implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private final Code solutionCorrection;

    private int nColumns;
    private int maxAttempts;
    private Difficulty difficulty;

    private Code solution;
    private ArrayList<Turn> turnSet;

    private static boolean isValidNColumns(final int nColumns)
    {
        return nColumns > 0;
    }

    private static boolean isValidMaxAttempts(final int maxAttempts)
    {
        return maxAttempts > 0;
    }

    private static boolean isValidTurnSet(final Collection<Turn> turnSet) throws NullPointerException
    {
        boolean b = turnSet.isEmpty();

        if(!b)
        {
            for(final Turn turn : turnSet)
            {
                b = turn.isValid();
                if(!b) break;
            }
        }

        return b;
    }

    @Deprecated
    private boolean isValidCode(final Code code) throws NullPointerException
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

        solutionCorrection = Code.getSolutionCorrection(Difficulty.EASY);
    }

    public Board(final Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        setNColumns(Constants.getNumPinsByDifficulty(difficulty));
        setMaxAttempts(Constants.getMaxRoundsByDifficulty(difficulty));
        setDifficulty(difficulty);

        solution = null;
        turnSet = new ArrayList<>();

        solutionCorrection = Code.getSolutionCorrection(difficulty);
    }

    public Board(final Board board) throws IllegalArgumentException, NullPointerException
    {
        setNColumns(board.getNColumns());
        setMaxAttempts (board.getMaxAttempts());
        setDifficulty(board.getDifficulty());
        setSolution(board.getSolution());
        setTurnSet(board.getTurnSet());

        solutionCorrection = Code.getSolutionCorrection(board.getDifficulty());
    }

    /* SET METHODS */

    public void setNColumns(final int nColumns) throws IllegalArgumentException
    {
        boolean b = isValidNColumns(nColumns);
        if(!b) throw new IllegalArgumentException();

        this.nColumns = nColumns;
    }

    public void setMaxAttempts(final int maxAttempts) throws IllegalArgumentException
    {
        boolean b = isValidMaxAttempts(maxAttempts);
        if(!b) throw new IllegalArgumentException();

        this.maxAttempts = maxAttempts;
    }

    public void setDifficulty(final Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        boolean b = difficulty != null;
        if(!b) throw new IllegalArgumentException();

        this.difficulty = difficulty;
    }

    public void setSolution(final Code solution) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidCode(solution);
        if(!b) throw new IllegalArgumentException();

        this.solution = solution.deepCopy();
    }

    public void setTurnSet(final Collection<Turn> turnSet) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidTurnSet(turnSet);
        if(!b) throw new IllegalArgumentException();

        this.turnSet = new ArrayList<>(turnSet.size());

        for(final Turn turn : turnSet)
        {
            addTurn(turn);
        }
    }

    public void addTurn(final Turn turn) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidCode(turn);
        if(!b) throw new IllegalArgumentException();

        if(turnSet == null)
        {
            turnSet = new ArrayList<>();
        }

        turnSet.add(turn.deepCopy());
    }

    public void addCode(final Code code) throws IllegalArgumentException
    {
        boolean b = isValidCode(code);
        if(!b) throw new IllegalArgumentException();

        turnSet.add(new Turn(code));
    }

    public void addCorrection(final Code code)
    {
        boolean b = isValidCode(code);
        if(!b) throw new IllegalArgumentException();

        getLastTurn().setCorrection(code);
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

    public Turn getTurn(final int i) throws IndexOutOfBoundsException, NullPointerException
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

    public int getCurrentTurnNumber()
    {
        return turnSet.size();
    }

    public boolean isFirstTurn()
    {
        return turnSet.isEmpty();
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
