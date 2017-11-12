package domain.controllers;

import domain.classes.*;
import enums.Difficulty;

public class HumanController extends PlayerController
{
    /* CONSTRUCTION METHODS */
    private final DomainController domainController;

    public HumanController(DomainController domainController)
    {
        this.domainController = domainController;
        player = new Human();
    }

    public HumanController(DomainController domainController, Player player)
    {
        super((Human) player);
        this.domainController = domainController;
    }

    /* INSTANTIATION METHODS */

    public Player newPlayer(String id)
    {
        player = new Human(id);
        return player;
    }

    public Player newPlayer(String id, String password)
    {
        player = new Human(id, password);
        return player;
    }

    /* OTHER METHODS */

    protected Action codeMake(Difficulty difficulty)
    {
        return null;
    }

    protected Action codeBreak(Difficulty difficulty, Turn lastTurn)
    {
        return null;
    }

    protected Action codeCorrect(Code code, Code solution, Difficulty difficulty)
    {
        return null;
    }

    public boolean checkPassword(String password)
    {
        return ((Human) player).checkPassword(password);
    }
}
