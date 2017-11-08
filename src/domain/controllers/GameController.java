package domain.controllers;

import domain.classes.Board;
import domain.classes.Game;
import domain.classes.Player;
import enums.Difficulty;
import util.Pair;
import enums.Role;

import java.util.ArrayList;

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
}
