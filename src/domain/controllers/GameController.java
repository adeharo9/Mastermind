package domain.controllers;

import domain.classes.Board;
import domain.classes.Game;
import domain.classes.Player;
import util.Difficulty;
import util.Pair;
import util.Role;

import java.util.ArrayList;

public class GameController
{
    private Game game;

    public GameController()
    {
        game = null;
    }

    public Game newGame(int id, Difficulty difficulty, Board board, ArrayList<Pair<Player, Role>> playerRolePair)
    {
        game = new Game(id, difficulty);

        game.setBoardByReference(board);
        game.setPlayerRolePairsByReference(playerRolePair);

        return game;
    }
}
