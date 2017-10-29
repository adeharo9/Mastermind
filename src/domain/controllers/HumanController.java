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

    public Player newPlayer(String id)
    {
        player = new Human(id);
        return player;
    }

    public Player newPlayer(String id, String password)
    {
        player = new Human(id, password);
        return player;
    }

    /* OTHER METHODS */

    public Action play()
    {
        return new CodeBreak();
    }
}
