package domain.controllers;

import domain.classes.Action;
import domain.classes.Turn;
import domain.classes.Code;
import domain.classes.Player;
import enums.Difficulty;
import enums.Role;
import exceptions.ReservedKeywordException;

public abstract class PlayerController
{
    /* ATTRIBUTES */

    protected boolean firstTurn = true;
    protected Role role;
    protected Player player;

    /* CONSTRUCTION METHODS */

    @Deprecated
    protected PlayerController()
    {

    }

    protected PlayerController(final Player player)
    {
        setPlayerByReference(player);
    }

    /* INSTANTIATION METHODS */

    public abstract Player newPlayer(final String id);

    /* INSTANCE CONTROL METHODS */

    public void restart()
    {
        firstTurn = true;
    }

    /* SET METHODS */

    public void setRole(final Role role) throws IllegalArgumentException
    {
        boolean b = role != null;
        if(!b) throw new IllegalArgumentException();

        this.role = role;
    }

    public void setPlayerByReference(final Player player) throws IllegalArgumentException
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

    public String getId()
    {
        return player.getId();
    }

    /* OTHER METHODS */

    public final Action play(final Difficulty difficulty, final Turn lastTurn, final Code solution) throws IllegalArgumentException, ReservedKeywordException
    {
        Action action;

        switch (role) {
            case CODE_MAKER:
                if(firstTurn)
                {
                    action = codeMake(difficulty);
                }
                else
                {
                    if(lastTurn == null)
                    {
                        int i = 0;
                    }
                    action = codeCorrect(difficulty, lastTurn.getCode(), solution);
                }
                break;
            case CODE_BREAKER:
                action = codeBreak(difficulty, lastTurn);
                break;
            case WATCHER:
                action = null;
                break;
            default:
                throw new IllegalArgumentException();
        }

        firstTurn = false;

        return action;
    }

    protected abstract Action codeMake(final Difficulty difficulty) throws ReservedKeywordException;

    protected abstract Action codeBreak(final Difficulty difficulty, final Turn lastTurn) throws ReservedKeywordException;

    protected abstract Action codeCorrect(final Difficulty difficulty, final Code code, final Code solution) throws ReservedKeywordException;
}

