package domain.controllers;

import domain.classes.Board;
import domain.classes.Game;
import domain.classes.Player;
import domain.classes.Turn;
import enums.Color;
import enums.Difficulty;
import util.Constants;
import util.Pair;
import enums.Role;

import java.util.ArrayList;
import java.util.List;

public class GameController
{
    private Game game;

    public GameController()
    {
        game = null;
    }

    public void newGame(String id, Difficulty difficulty, Board board, ArrayList<Pair<Player, Role>> playerRolePair)
    {
        game = new Game(id, difficulty);

        game.setBoard(board);
        game.setPlayerRolePairs(playerRolePair);

        //return game;
    }

    public void setGameByReference(Game game) throws IllegalArgumentException
    {
        boolean b = game.isValid();
        if(!b) throw new IllegalArgumentException();

        this.game = game;
    }

    public Game getGame()
    {
        return game;
    }

    public String getId()
    {
        return game.getId();
    }

    public void pointsEndTurn()
    {
        int newpoints = game.getPoints();
        int modify = game.getBoard().getCurrentTurnNumber();
        newpoints = newpoints + modify * Constants.POINTS_ROUND;
        Turn lt = game.getBoard().getLastTurn();
        for (int i = 0; i<lt.getCorrection().size();++i)
        {
            Color c = lt.getCorrectionAt(i);
            switch (c) {
                case BLACK:
                    newpoints = newpoints + Constants.POINTS_BLACK;
                    break;
                case WHITE:
                    newpoints = newpoints + Constants.POINTS_WHITE;
                    break;
                case NONE:
                    break;
            }
        }
        game.setPoints(newpoints);
    }

    public void pointsEndGame()
    {
        int newpoints = game.getPoints();
        long modify = game.getTime();
        newpoints = newpoints + (int)modify * Constants.POINTS_TIME;
        game.setPoints(newpoints);
    }

    public void pointsClue()
    {
        int newpoints = game.getPoints() + Constants.POINTS_CLUE;
        game.setPoints(newpoints);
    }
}
