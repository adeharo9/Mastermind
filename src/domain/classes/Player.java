package domain.classes;

import util.DeepCopyable;
import util.Utils;

import java.io.Serializable;

public abstract class Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    protected String id;


    /* PRIVATE METHODS */

    private static boolean isValidId(String id) throws NullPointerException
    {
        return !id.isEmpty();
    }

    /* CONSTRUCTION METHODS */
    public Player ()
    {
        setId(Utils.autoID());
    }

    public Player(String id) throws IllegalArgumentException
    {
        setId(id);
    }

    public Player (Player player) throws IllegalArgumentException, NullPointerException
    {
        setId(player.getId());
    }

    /* SET METHODS */

    public void setId(String id) throws IllegalArgumentException
    {
        boolean b = isValidId(id);
        if(!b) throw new IllegalArgumentException();

        this.id = id;
    }

    /* GET METHODS */

    public String getId()
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