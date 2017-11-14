package domain.controllers;

import domain.classes.*;
import enums.Color;
import enums.Difficulty;
import exceptions.ReservedKeywordException;

import java.util.List;

public class HumanController extends PlayerController
{
    @Deprecated
    public HumanController(final DomainController domainController)
    {
        player = new Human(this);
    }

    public HumanController(final DomainController domainController, final Human human)
    {
        super(human);
    }

    /* INSTANTIATION METHODS */

    public Player newPlayer(final String id)
    {
        player = new Human(id, this);
        return player;
    }

    public Player newPlayer(final String id, final String password)
    {
        player = new Human(id, password, this);
        return player;
    }

    /* VALIDATION METHODS */

    public boolean checkPassword(final String password)
    {
        return ((Human) player).checkPassword(password);
    }
}
