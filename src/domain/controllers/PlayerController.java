package domain.controllers;

import domain.classes.*;
import enums.Difficulty;
import enums.Role;
import exceptions.ReservedKeywordException;

/**
 * PlayerController.
 *
 * @author Alejandro de Haro, Alex
 */

public class PlayerController
{
    /* ATTRIBUTES */

    protected Player player;

    /* CONSTRUCTION METHODS */

    public PlayerController()
    {

    }

    public PlayerController(final Player player)
    {
        setPlayerByReference(player);
    }

    /* INSTANTIATION METHODS */

    public Player newHuman(final String id)
    {
        return newHuman(id, "");
    }

    public Player newHuman(final String id, final String password)
    {
        player = new Human(id, password);
        return player;
    }

    public Player newCPU(final String id)
    {
        player = new CPU(id);
        return player;
    }

    /* INSTANCE CONTROL METHODS */

    public void restart()
    {
        player.restart();
    }

    /* SET METHODS */

    public void setPlayerByReference(final Player player) throws IllegalArgumentException
    {
        boolean b = player.isValid();
        if(!b) throw new IllegalArgumentException();

        this.player = player;
    }

    public void setRole(final Role role) throws IllegalArgumentException
    {
        player.setRole(role);
    }

    /* GET METHODS */

    public Player getPlayer()
    {
        return player;
    }

    public String getId()
    {
        return player.getId();
    }

    public Role getRole()
    {
        return player.getRole();
    }

    /* PLAYING METHODS */

    public final Action play(final Difficulty difficulty, final Turn lastTurn, final Code solution, final boolean isFirstTurn) throws IllegalArgumentException, ReservedKeywordException
    {
        Action action;

        switch (player.getRole()) {
            case CODE_MAKER:
                if(isFirstTurn)
                {
                    action = codeMake(difficulty);
                }
                else
                {
                    action = codeCorrect(difficulty, lastTurn.getCode(), solution);
                }
                break;
            case CODE_BREAKER:
                action = codeBreak(difficulty, lastTurn, isFirstTurn);
                break;
            case WATCHER:
                action = null;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return action;
    }

    private Action codeMake(final Difficulty difficulty) throws ReservedKeywordException
    {
        return player.codeMake(difficulty);
    }

    private Action codeBreak(final Difficulty difficulty, final Turn lastTurn, final boolean isFirstTurn) throws ReservedKeywordException
    {
        return player.codeBreak(difficulty, lastTurn, isFirstTurn);
    }

    private Action codeCorrect(final Difficulty difficulty, final Code code, final Code solution) throws ReservedKeywordException
    {
        return player.codeCorrect(difficulty, code, solution);
    }

    /* VALIDATION METHODS */

    public boolean checkPassword(final String password)
    {
        return player.checkPassword(password);
    }
}

