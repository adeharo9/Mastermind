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

    public boolean loadPlayer(String username)
    {
        player = playerPersistence.load(username);

        return player != null;
    }

    public boolean logIn(String username, String password)
    {
        boolean b;

        b = loadPlayer(username);

        if(b)
        {
            Human human = (Human)player;
            b = human.checkPassword(password);
        }

        return b;
    }
}
