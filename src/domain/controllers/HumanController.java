package domain.controllers;

import domain.classes.Action;
import domain.classes.Human;

public class HumanController extends PlayerController
{
    public HumanController()
    {
        player = new Human();
    }

    public HumanController(String username, String password)
    {
        player = new Human(username, password);
    }

    public Action play()
    {
        return new Action();
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
