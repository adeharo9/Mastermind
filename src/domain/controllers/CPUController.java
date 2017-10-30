package domain.controllers;

import domain.classes.*;

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
}
