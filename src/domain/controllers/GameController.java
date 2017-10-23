package domain.controllers;

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

    public boolean newGame(Difficulty difficulty, Pair<Player, Role> playerRolePair)
    {
        game = new Game();

        game.setId(0);
        game.setDifficulty(difficulty);
        game.setTime();

        game.addPlayerRolePair(playerRolePair);

        return game.isValid();
    }
}
