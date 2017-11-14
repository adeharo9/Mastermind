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

    /* OTHER METHODS */

    public final Action play(final Difficulty difficulty, final Turn lastTurn, final Code solution) throws IllegalArgumentException, ReservedKeywordException
    {
        Action action;

        switch (player.getRole()) {
            case CODE_MAKER:
                if(player.isFirstTurn())
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

        player.passFirstTurn();

        return action;
    }

    private Action codeMake(final Difficulty difficulty) throws ReservedKeywordException
    {
        return player.codeMake(difficulty);
    }

    private Action codeBreak(final Difficulty difficulty, final Turn lastTurn) throws ReservedKeywordException
    {
        return player.codeBreak(difficulty, lastTurn);
    }

    private Action codeCorrect(final Difficulty difficulty, final Code code, final Code solution) throws ReservedKeywordException
    {
        return player.codeCorrect(difficulty, code, solution);
    }
}

