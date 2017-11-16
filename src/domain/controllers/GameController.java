package domain.controllers;

import domain.classes.Board;
import domain.classes.Game;
import domain.classes.Player;
import domain.classes.Turn;
import enums.Color;
import enums.Difficulty;
import enums.Mode;
import util.Constants;

import java.util.List;

public class GameController
{
    private Game game;

    public GameController()
    {
        game = null;
    }

    public void newGame(String id, Difficulty difficulty, Mode mode, Board board, List<Player> players)
    {
        game = new Game(id, difficulty, mode);

        game.setBoard(board);
        game.setPlayers(players);
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

    public int getPoints()
    {
        return game.getPoints();
    }

    public Mode getMode()
    {
        return game.getMode();
    }

    public boolean hasStarted()
    {
        return game.hasStarted();
    }

    public boolean hasFinished()
    {
        return game.hasFinished();
    }

    public void pointsEndTurn()
    {
        int newPoints = game.getPoints();
        int modify = game.getBoard().getCurrentTurnNumber();
        newPoints = newPoints + modify * Constants.POINTS_ROUND;
        Turn lastTurn = game.getBoard().getLastTurn();
        for (int i = 0; i < lastTurn.getCorrectionPins().size(); ++i)
        {
            Color c = lastTurn.getCorrectionPinAt(i);
            switch (c)
            {
                case BLACK:
                    newPoints = newPoints + Constants.POINTS_BLACK;
                    break;
                case WHITE:
                    newPoints = newPoints + Constants.POINTS_WHITE;
                    break;
                case NONE:
                    break;
            }
        }
        game.setPoints(newPoints);
    }

    public void pointsEndGame()
    {
        int newpoints = game.getPoints();
        long modify = game.getTime();
        newpoints = newpoints + ((int)System.currentTimeMillis()-(int)modify) * Constants.POINTS_TIME;
        if (newpoints<0) newpoints = 0;
        game.setPoints(newpoints);
    }

    public void pointsClue()
    {
        int newpoints = game.getPoints() + Constants.POINTS_CLUE;
        game.setPoints(newpoints);
    }
}
