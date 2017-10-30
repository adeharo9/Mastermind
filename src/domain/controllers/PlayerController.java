package domain.controllers;

import domain.classes.Action;
import domain.classes.Player;
import util.Role;

public abstract class PlayerController
{
    /* ATTRIBUTES */

    protected Role role;
    protected Player player;

    /* CONSTRUCTION METHODS */

    public PlayerController()
    {

    }

    public PlayerController(Role role)
    {
        setRole(role);
    }

    public PlayerController(Player player)
    {
        setPlayerByReference(player);
    }

    /* INSTANTIATION METHODS */

    public abstract Player newPlayer(String id);

    /* SET METHODS */

    public void setRole(Role role) throws IllegalArgumentException
    {
        boolean b = role != null;
        if(!b) throw new IllegalArgumentException();

        this.role = role;
    }

    public void setPlayerByReference(Player player) throws IllegalArgumentException
    {
        boolean b = player.isValid();
        if(!b) throw new IllegalArgumentException();

        this.player = player;
    }

    /* GET METHODS */

    public Role getRole()
    {
        return role;
    }

    public Player getPlayer()
    {
        return player;
    }

    /* OTHER METHODS */

    public abstract Action play();
}
