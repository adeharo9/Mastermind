package domain.classes;

import util.DeepCopyable;
import util.Utils;

import java.io.Serializable;

public abstract class Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    protected final String id;

    /* PRIVATE METHODS */

    private static boolean isValidId(final String id) throws NullPointerException
    {
        return !id.isEmpty();
    }

    /* CONSTRUCTION METHODS */
    protected Player ()
    {
        this.id = Utils.autoID();
    }

    protected Player(final String id) throws IllegalArgumentException
    {
        this.id = id;
    }

    protected Player(final Player player) throws IllegalArgumentException, NullPointerException
    {
        this.id = player.getId();
    }

    /* GET METHODS */

    public final String getId()
    {
        return id;
    }

    /* VALIDATION METHODS */

    public boolean isValid()
    {
        return isValidId(id);
    }

    /* CLONING METHODS */

    public abstract Player deepCopy ();
}