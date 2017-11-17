package domain.controllers;

import domain.classes.Action;
import domain.classes.Board;
import domain.classes.Code;
import domain.classes.Turn;
import enums.Difficulty;
import exceptions.IllegalActionException;

import java.util.List;

/**
 * BoardController.
 *
 * @author Alejandro de Haro
 */

public class BoardController
{
    /* ATTRIBUTES */

    private Board board;

    /* PRIVATE METHODS */

    /* CONSTRUCTION METHODS */

    public BoardController()
    {
        //this.board = new sBoard();
    }

    /* INITIALIZATION METHODS */

    public Board newBoard(Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        this.board = new Board(difficulty);

        return this.board;
    }

    /* SET METHODS */

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

    public boolean isFirstTurn()
    {
        return board.isFirstTurn();
    }

    public boolean hasCodeToCorrect()
    {
        Turn lastTurn = getLastTurn();

        return lastTurn != null && lastTurn.getCorrectionCode().size() == 0;
    }

    /* VALIDATION METHODS */

    public void checkAction(Action action) throws IllegalActionException
    {
        Code code;

        if(getLastTurn() == null)
        {
            code = null;
        }
        else
        {
            code = getLastTurn().getCode();
        }

        action.checkAction(getDifficulty(), code, getSolution());
    }

    public void addAction(Action action)
    {
        action.addSelfToBoard(board);
    }
}
