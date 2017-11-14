package domain.controllers;

import domain.classes.*;
import enums.Difficulty;
import enums.Color;
import util.Constants;

import java.util.*;

public class CPUController extends PlayerController
{

    /* CONSTRUCTION METHODS */

    public CPUController()
    {

        player = new CPU();
    }

    public CPUController(final CPU cpu)
    {
        super(cpu);
    }

    /* INSTANTIATION METHODS */

    public Player newPlayer(final String id)
    {
        player = new CPU(id);
        return player;
    }
}
