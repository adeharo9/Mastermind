package domain.controllers;

import domain.classes.Action;
import domain.classes.Player;
import persistence.PlayerPersistence;

public abstract class PlayerController
{
    /* ATTRIBUTES */

    protected Player player;

    /* CONSTRUCTION METHODS */

    /* INSTANTIATION METHODS */

    public abstract Player newPlayer(String id);

    /* SET METHODS */

    /* GET METHODS */

    public Player getPlayer()
    {
        return player;
    }

    /* OTHER METHODS */

    public abstract Action play();
}
