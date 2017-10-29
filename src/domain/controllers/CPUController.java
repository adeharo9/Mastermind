package domain.controllers;

import domain.classes.Action;
import domain.classes.CPU;
import domain.classes.CodeBreak;
import domain.classes.Player;

public class CPUController extends PlayerController
{
    /* CONSTRUCTION METHODS */

    public CPUController()
    {
        player = new CPU();
    }

    public CPUController(Player player)
    {
        super((CPU) player);
    }

    /* INSTANTIATION METHODS */

    public Player newPlayer(String id)
    {
        player = new CPU(id);
        return player;
    }

    /* OTHER METHODS */

    public Action play()
    {
        return new CodeBreak();
    }
}
