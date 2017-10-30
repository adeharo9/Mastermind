package domain.controllers;

import domain.classes.Action;
import domain.classes.Combination;
import domain.classes.Player;
import enums.Role;

public abstract class PlayerController
{
    /* ATTRIBUTES */

    protected Role role;
    protected Player player;
    protected DomainController domainController;

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

    public final Action play(Action inAction) throws IllegalArgumentException
    {
        Action action;

        switch (role) {
            case CODE_MAKER:
                if(inAction == null)
                {
                    action = codeMake();
                }
                else
                {
                    action = codeCorrect(inAction.getCombination());
                }
                break;
            case CODE_BREAKER:
                action = codeBreak();
                break;
            case WATCHER:
                action = null;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return action;
    }

    protected abstract Action codeMake();

    protected abstract Action codeBreak();

    protected abstract Action codeCorrect(Combination combination);
}
