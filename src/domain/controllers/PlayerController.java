package domain.controllers;

import domain.classes.Action;
import domain.classes.Player;
import persistence.PlayerPersistence;

public abstract class PlayerController
{
    /* ATTRIBUTES */

    protected Player player;

    /* CONSTRUCTION METHODS */

    public PlayerController()
    {

    }

    public PlayerController(Player player)
    {
        setPlayerByReference(player);
    }

    /* INSTANTIATION METHODS */

    public abstract Player newPlayer(String id);

    /* SET METHODS */

    public void setPlayerByReference(Player player) throws IllegalArgumentException
    {
        boolean b = player.isValid();
        if(!b) throw new IllegalArgumentException();

        this.player = player;
    }

    /* GET METHODS */

    public Player getPlayer()
    {
        return player;
    }

    /* OTHER METHODS */

    public abstract Action play();
}
