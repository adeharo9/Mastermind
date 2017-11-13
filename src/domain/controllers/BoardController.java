package domain.controllers;

import domain.classes.Action;
import domain.classes.Board;
import domain.classes.Code;
import domain.classes.Turn;
import enums.Difficulty;

import java.util.List;

public class BoardController
{
    /* ATTRIBUTES */

    private Board board;

    /* PRIVATE METHODS */

    /* CONSTRUCTION METHODS */

    @Deprecated
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

    public Difficulty getDifficulty()
    {
        return board.getDifficulty();
    }

    public Code getSolution()
    {
        return board.getSolution();
    }

    public List<Turn> getTurnSet()
    {
        return board.getTurnSet();
    }

    public Turn getLastTurn()
    {
        return board.getLastTurn();
    }

    /* VALIDATION METHODS */

    public boolean finished() throws NullPointerException
    {
        return board.finished();
    }

    public void checkAction(Action action)
    {

    }

    public void addAction(Action action)
    {
        action.addSelfToBoard(board);
    }
}
