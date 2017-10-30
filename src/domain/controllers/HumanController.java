package domain.controllers;

import domain.classes.*;

public class HumanController extends PlayerController
{
    /* CONSTRUCTION METHODS */
    public HumanController()
    {
        player = new Human();
    }

    public HumanController(Player player)
    {
        super((Human) player);
    }

    /* INSTANTIATION METHODS */

    public Player newPlayer(String id)
    {
        player = new Human(id);
        return player;
    }

    public HumanController(String id, String password)
    {
        player = new Human(id, password);
    }

    public Player newPlayer(String id, String password)
    {
        player = new Human(id, password);
        return player;
    }

    /* OTHER METHODS */

    protected Action codeMake()
    {
        return null;
    }

    protected Action codeBreak()
    {
        return null;
    }

    protected Action codeCorrect(Combination combination)
    {
        return null;
    }

    public boolean checkPassword(String password)
    {
        return ((Human) player).checkPassword(password);
    }
}
