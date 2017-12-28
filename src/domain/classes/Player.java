package domain.classes;

import enums.Difficulty;
import enums.Role;
import exceptions.ReservedKeywordException;
import util.DeepCopyable;
import util.Utils;

import java.io.Serializable;

/**
 * Clase Player.
 *
 * Esta clase representa un jugador.
 *
 * @author Rafael
 */

public abstract class Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    protected String id;
    protected Role role;

    /* PRIVATE METHODS */

    private static boolean isValidId(final String id) throws NullPointerException
    {
        return id != null;
    }

    /* CONSTRUCTION METHODS */

    protected Player ()
    {
        this.id = Utils.autoID();
        this.role = null;
    }

    protected Player(final String id) throws IllegalArgumentException
    {
        this.id = id;
        this.role = null;
    }

    protected Player(final Player player) throws IllegalArgumentException, NullPointerException
    {
        this.id = player.getId();
        this.role = player.getRole();
    }

    /* INSTANCE CONTROL METHODS */

    public final void restart()
    {
        this.role = null;
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

    /* SET METHODS */

    public void setId(String id)
    {
        this.id = id;
    }

    public void setRole(Role role) throws IllegalArgumentException
    {
        boolean b = role != null;
        if(!b) throw new IllegalArgumentException();

        this.role = role;
    }

    /* VALIDATION METHODS */

    public boolean isValid()
    {
        return isValidId(id);
    }

    public boolean checkPassword(final String password)
    {
        return true;
    }

    /* CLONING METHODS */

    public abstract Player deepCopy ();

    /* PLAYING METHODS */

    public abstract Action codeMake(final Difficulty difficulty) throws ReservedKeywordException;

    public abstract Action codeBreak(final Difficulty difficulty, final Turn lastTurn, final boolean isFirstTurn) throws ReservedKeywordException;

    public abstract Action codeCorrect(final Difficulty difficulty, final Code code, final Code solution) throws ReservedKeywordException;
}