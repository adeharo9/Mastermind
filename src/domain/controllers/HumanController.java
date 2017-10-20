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
}
