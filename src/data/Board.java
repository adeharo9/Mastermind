package data;

import java.util.*;
import data.tokens.*;

public class Board
{
    /* ATTRIBUTES */

    private int nColors;
    private int nColumns;
    private int maxAttempts;

    private Code code;
    private ArrayList<Turn> turnSet;

    /* CONSTRUCTION FUNCTIONS */

    public Board()
    {
        nColors = 0;
        maxAttempts = 0;
        code = new Code();
        turnSet = new ArrayList<>();
    }

    public Board(Board board)
    {
        setNColors(board.getNColors());
        setMaxAttempts(board.getMaxAttempts());
        setCode(board.getCode());
        setTurnSet(board.getTurnSet());
    }

    /* SET FUNCTIONS */

    public boolean setNColors(int nColors)
    {
        boolean b = nColors > 0;

        if(b)
        {
            this.nColors = nColors;
        }

        return b;
    }

    public boolean setMaxAttempts(int maxAttempts)
    {
        boolean b = maxAttempts > 0;

        if(b)
        {
            this.maxAttempts = maxAttempts;
        }

        return b;
    }

    public boolean setCode(Code code)
    {
        boolean b = code.isValid();

        if(b)
        {
            this.code = new Code(code);
        }

        return b;
    }

    public boolean setTurnSet(ArrayList<Turn> turnSet)
    {
        boolean b = turnSet.size() >= 0;

        if(b)
        {
            this.turnSet = new ArrayList<>(turnSet.size());

            for(Turn turn : turnSet)
            {
                this.turnSet.add(new Turn(turn));
            }

        }

        return b;
    }

    /* GET FUNCTIONS */

    public int getNColors()
    {
        return nColors;
    }

    public int getMaxAttempts()
    {
        return maxAttempts;
    }

    public Code getCode()
    {
        return code;
    }

    public ArrayList<Turn> getTurnSet()
    {
        return turnSet;
    }

    /* CLONING FUNCTIONS */

    public Board cClone()
    {
        return new Board(this);
    }
}
