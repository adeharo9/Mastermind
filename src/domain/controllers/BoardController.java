package domain.controllers;

import domain.classes.Board;
import util.Difficulty;

import java.util.ArrayList;

public class BoardController
{
    /* ATTRIBUTES */

    private Board board;

    /* PRIVATE METHODS */

    private static Difficulty getDifficultyByInt(int diff)
    {
        Difficulty difficulty;

        switch(diff)
        {
            case 0:
                difficulty = Difficulty.easy;
                break;
            case 1:
                difficulty = Difficulty.medium;
                break;
            case 2:
                difficulty = Difficulty.hard;
                break;
            default:
                difficulty = null;
                break;
        }

        return difficulty;
    }

    /* CONSTRUCTION METHODS */

    public BoardController()
    {
        board = new Board();
    }

    /* INITIALIZATION METHODS */

    public void newBoard(int diff) throws IllegalArgumentException, NullPointerException
    {
        Difficulty difficulty = getDifficultyByInt(diff);
        board = new Board(difficulty);
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
}
