package domain.controllers;

import domain.classes.Action;
import domain.classes.CPU;

public class CPUController extends PlayerController
{
    public CPUController()
    {
        player = new CPU();
    }

    public Action play()
    {
        return new Action();
    }
}
