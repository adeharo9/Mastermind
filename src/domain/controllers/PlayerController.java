package domain.controllers;

import domain.classes.*;
import enums.Difficulty;
import enums.Role;
import exceptions.ReservedKeywordException;

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

    @Deprecated
    public PlayerController(final Human human)
    {
        setPlayerByReference(human);
    }

    @Deprecated
    public PlayerController(final CPU cpu)
    {
        setPlayerByReference(cpu);
    }

    /* INSTANTIATION METHODS */

    //public abstract Player newPlayer(final String id);

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

    /* VALIDATION METHODS */

    public boolean checkPassword(final String password)
    {
        return player.checkPassword(password);
    }
}

