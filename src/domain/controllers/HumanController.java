package domain.controllers;

import domain.classes.Action;
import domain.classes.CodeBreak;
import domain.classes.Human;
import domain.classes.Player;

public class HumanController extends PlayerController
{
    /* CONSTRUCTION METHODS */
    public HumanController()
    {
        player = new Human();
    }

    public HumanController(String username, String password)
    {
        player = new Human(username, password);
    }

    /* INSTANTIATION METHODS */

    public Player newPlayer(int id)
    {
        player = new Human(id);
        return player;
    }

    /* OTHER METHODS */

    public Action play()
    {
        return new CodeBreak();
    }
}
