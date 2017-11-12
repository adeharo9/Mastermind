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
    public Player ()
    {
        this.id = Utils.autoID();
    }

    public Player(final String id) throws IllegalArgumentException
    {
        this.id = id;
    }

    public Player(final Player player) throws IllegalArgumentException, NullPointerException
    {
        this.id = player.getId();
    }

    /* SET METHODS */

    /* GET METHODS */

    public final String getId()
    {
        return id;
    }

    /* TESTING METHODS */

    public boolean isValid()
    {
        return isValidId(id);
    }

    /* CLONING METHODS */

    public abstract Player deepCopy ();
}