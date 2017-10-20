package domain.controllers;

import domain.classes.Action;
import domain.classes.Player;

public abstract class PlayerController
{
    protected Player player;

    public abstract Action play();
}
