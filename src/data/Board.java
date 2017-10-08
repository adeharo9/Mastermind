package data;

import java.util.*;
import data.tokens.*;

public class Board
{
    /* ATTRIBUTES */

    private int nColors;
    private int maxAttempts;

    private Code code;
    private ArrayList<Turn> turnSet;

    /* CONSTRUCTION FUNCTIONS */

    public Board()
    {

    }

    public Board(Board board)
    {
        setNColors(board.nColors);
        setMaxAttempts(board.maxAttempts);
        setCode(board.code);
        setTurnSet(board.turnSet);
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

    /* SUPERCLASS OVERWRITTEN FUNCTIONS */

    public Object clone() throws CloneNotSupportedException
    {
        Board board = (Board) super.clone();

        board.setCode(this.code);
        board.setTurnSet(this.turnSet);

        return board;
    }
}
