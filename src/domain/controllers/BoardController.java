package domain.controllers;

import domain.classes.Board;
import domain.classes.Code;
import enums.Difficulty;

public class BoardController
{
    /* ATTRIBUTES */

    private Board board;

    /* PRIVATE METHODS */

    /* CONSTRUCTION METHODS */

    public BoardController()
    {
        board = new Board();
    }

    /* INITIALIZATION METHODS */

    public Board newBoard(Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        board = new Board(difficulty);

        return board;
    }

    /* SET METHODS */

    public boolean setTurn()
    {
        // Check turn validity
        //Board.setTurn();
        return true;
    }

    public void setBoardByReference(Board board) throws IllegalArgumentException
    {
        boolean b = board.isValid();
        if(!b) throw new IllegalArgumentException();

        this.board = board;
    }

    /* GET METHODS */

    public Board getBoard()
    {
        return board;
    }

    public Code getCode()
    {
        return board.getCode();
    }

    public boolean finished() throws NullPointerException
    {
        return board.finished();
    }
}
