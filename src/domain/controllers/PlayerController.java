package domain.controllers;

import domain.classes.Action;
import domain.classes.Turn;
import domain.classes.Code;
import domain.classes.Player;
import enums.Difficulty;
import enums.Role;

public abstract class PlayerController
{
    /* ATTRIBUTES */

    protected Role role;
    protected Player player;
    //protected DomainController domainController;

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

    public String getId() { return player.getId(); }

    /* OTHER METHODS */

    public final Action play(Action inAction, Code solution, Difficulty difficulty, Turn lastTurn) throws IllegalArgumentException
    {
        Action action;

        switch (role) {
            case CODE_MAKER:
                if(inAction == null)
                {
                    action = codeMake(difficulty);
                }
                else
                {
                    action = codeCorrect(inAction.getCode(), solution, difficulty);
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

        return action;
    }

    protected abstract Action codeMake(Difficulty difficulty);

    protected abstract Action codeBreak(Difficulty difficulty, Turn lastTurn);

    protected abstract Action codeCorrect(Code code, Code solution, Difficulty difficulty);
}

