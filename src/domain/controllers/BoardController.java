package domain.controllers;

import domain.classes.Board;
import util.Difficulty;

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

    public boolean newBoard(Difficulty difficulty) throws Exception
    {
        board = new Board();

        board.setNColumns(Board.getNColumnsByDifficulty(difficulty));
        board.setMaxAttempts(Board.getMaxAttemptsByDifficulty(difficulty));

        board.setTurnSet(new ArrayList<>());

        return board.isValid();
    }
}
