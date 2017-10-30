package domain.classes;

import util.*;

import java.io.Serializable;
import java.util.*;

public abstract class Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    protected String id;
    protected int points;

    /* PRIVATE METHODS */

    private static boolean isValidId(String id)
    {
        return !id.isEmpty();
    }

    /* CONSTRUCTION METHODS */
    public Player ()
    {
        setId(Utils.autoID());
        setPoints(0);
    }

    public Player(String id) throws IllegalArgumentException
    {
        setId(id);
        setPoints(0);
    }

    public Player (Player player) throws IllegalArgumentException, NullPointerException
    {
        setId(player.getId());
        setPoints(player.getPoints());
    }

    /* SET METHODS */

    public void setId(String id) throws IllegalArgumentException
    {
        boolean b = isValidId(id);
        if(!b) throw new IllegalArgumentException();

        this.id = id;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    /* GET METHODS */

    public String getId()
    {
        return id;
    }

    public int getPoints()
    {
        return points;
    }

    /* TESTING METHODS */

    public boolean isValid()
    {
        return isValidId(id);
    }

    /* CLONING METHODS */

    public abstract Player deepCopy ();
}