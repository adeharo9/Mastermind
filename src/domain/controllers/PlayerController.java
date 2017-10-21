package domain.controllers;

import domain.classes.Action;
import domain.classes.Player;
import persistence.PlayerPersistence;

public abstract class PlayerController
{
    protected Player player;
    protected PlayerPersistence playerPersistence;

    public abstract Action play();

    public boolean loadPlayer(String username)
    {
        return true;
    }

    public boolean logIn(String username, String password)
    {
        return true;
    }
}
