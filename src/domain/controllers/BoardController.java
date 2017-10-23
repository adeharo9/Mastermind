package domain.controllers;

import domain.classes.Board;

import java.util.ArrayList;

public class BoardController
{
    private Board board;

    public BoardController()
    {
        board = new Board();
    }

    public boolean setTurn()
    {
        // Check turn validity
        //Board.setTurn();
        return true;
    }

    public boolean newBoard(int nColumns, int maxAttempts) throws Exception
    {
        board = new Board();

        board.setNColumns(nColumns);
        board.setMaxAttempts(maxAttempts);

        board.setTurnSet(new ArrayList<>());

        return board.isValid();
    }
}
