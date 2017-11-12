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

    protected boolean firstTurn;
    protected Role role;
    protected Player player;
    //protected DomainController domainController;

    /* CONSTRUCTION METHODS */

    public PlayerController()
    {
        firstTurn = true;
    }

    public PlayerController(Role role)
    {
        firstTurn = true;
        setRole(role);
    }

    public PlayerController(Player player)
    {
        firstTurn = true;
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

    public final Action play(Turn lastTurn, Code solution, Difficulty difficulty) throws IllegalArgumentException, ReservedKeywordException
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
                    action = codeCorrect(new Code(lastTurn.getPins()), solution, difficulty);
                    /* WARNING: APAÑO
                    CAMBIAR EL new Code(cosas) POR ALGUNA FUNCIÓN ESPECÍFICA (getSolution() Y TAL)
                     */
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

    protected abstract Action codeMake(Difficulty difficulty) throws ReservedKeywordException;

    protected abstract Action codeBreak(Difficulty difficulty, Turn lastTurn) throws ReservedKeywordException;

    protected abstract Action codeCorrect(Code code, Code solution, Difficulty difficulty) throws ReservedKeywordException;
}

