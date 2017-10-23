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

    public boolean load(String username)
    {
        player = playerPersistence.load(username);

        return player != null;
    }

    public boolean logIn(String username, String password)
    {
        boolean b;

        b = load(username);

        if(b)
        {
            b = ((Human)player).checkPassword(password);
        }

        return b;
    }

    public boolean registerUser(String username, String password)
    {
        boolean b;

        b = load(username);

        if(!b)
        {
            player = new Human(username, password);
            b = playerPersistence.save(player);
        }

        return b;
    }
}
