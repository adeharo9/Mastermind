package domain.controllers;

import domain.classes.Board;
import util.Difficulty;
import util.Translate;

import java.util.ArrayList;
import java.util.TooManyListenersException;

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

    /* GET METHODS */

    public Board getBoard()
    {
        return board;
    }

    public boolean finished() throws NullPointerException
    {
        return board.finished();
    }
}
