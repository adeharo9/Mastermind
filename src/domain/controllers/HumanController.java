package domain.controllers;

import domain.classes.Action;

public class HumanController extends PlayerController
{
    public HumanController()
    {

    }

    public Action play()
    {
        return new Action();
    }
}
