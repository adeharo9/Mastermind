package domain.classes;

import enums.Difficulty;
import enums.Role;
import exceptions.ReservedKeywordException;
import util.DeepCopyable;
import util.Utils;

import java.io.Serializable;

public abstract class Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    protected final String id;
    protected Role role;
    protected boolean firstTurn;

    /* PRIVATE METHODS */

    private static boolean isValidId(final String id) throws NullPointerException
    {
        return !id.isEmpty();
    }

    /* CONSTRUCTION METHODS */

    protected Player ()
    {
        this.id = Utils.autoID();
        this.role = null;
        this.firstTurn = true;
    }

    protected Player(final String id) throws IllegalArgumentException
    {
        this.id = id;
        this.role = null;
        this.firstTurn = true;
    }

    protected Player(final Player player) throws IllegalArgumentException, NullPointerException
    {
        this.id = player.getId();
        this.role = player.getRole();
        this.firstTurn = player.firstTurn;
    }

    /* INSTANCE CONTROL METHODS */

    public final void restart()
    {
        this.role = null;
        this.firstTurn = true;
    }

    /* GET METHODS */

    public final String getId()
    {
        return id;
    }

    public final Role getRole()
    {
        return role;
    }

    public final boolean isFirstTurn()
    {
        return firstTurn;
    }

    /* SET METHODS */

    public void setRole(Role role) throws IllegalArgumentException
    {
        boolean b = role != null;
        if(!b) throw new IllegalArgumentException();

        this.role = role;
    }

    public final void passFirstTurn()
    {
        firstTurn = false;
    }

    /* VALIDATION METHODS */

    public boolean isValid()
    {
        return isValidId(id);
    }

    /* CLONING METHODS */

    public abstract Player deepCopy ();

    /* PLAYING METHODS */

    public abstract Action codeMake(final Difficulty difficulty) throws ReservedKeywordException;

    public abstract Action codeBreak(final Difficulty difficulty, final Turn lastTurn) throws ReservedKeywordException;

    public abstract Action codeCorrect(final Difficulty difficulty, final Code code, final Code solution) throws ReservedKeywordException;
}