package domain.controllers;

import domain.classes.Action;

public class CPUController extends PlayerController
{
    public CPUController()
    {

    }

    public Action play()
    {
        return new Action();
    }
}
