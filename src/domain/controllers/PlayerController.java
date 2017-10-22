package domain.controllers;

import domain.classes.Action;
import domain.classes.Player;
import persistence.PlayerPersistence;

public abstract class PlayerController
{
    protected Player player;
    protected PlayerPersistence playerPersistence;

    public abstract Action play();

    public boolean load(String username)
    {
        return true;
    }

    public boolean logIn(String username, String password)
    {
        return true;
    }

    public boolean registerUser(String username, String password)
    {
        return true;
    }
}
