package domain.controllers;

import domain.classes.Game;
import domain.classes.Player;
import util.Pair;
import util.Role;

import java.util.ArrayList;

public class GameController
{
    private Game game;

    public GameController()
    {
        game = new Game();
    }

    public boolean newGame(String difficulty, Pair<Player, Role> playerAndRole)
    {

        return true;
    }
}
