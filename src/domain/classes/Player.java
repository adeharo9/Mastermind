package domain.classes;

import util.*;
import java.util.*;

public abstract class Player implements DeepCopyable
{
    /* ATTRIBUTES */

    private int id;
    private int points;

    /* PRIVATE METHODS */

    private static boolean isValidId(int id)
    {
        return id >= 0;
    }

    /* CONSTRUCTION METHODS */

    public Player ()
    {
        id = -1;
        points = 0;
    }

    public Player (Player player) throws IllegalArgumentException, NullPointerException
    {
        setId(player.getId());
        setPoints(player.getPoints());
    }

    /* SET METHODS */

    public void setId(int id) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidId(id);
        if(!b) throw new IllegalArgumentException();

        this.id = id;
    }

    public void setPoints(int points) throws NullPointerException
    {
        this.points = points;
    }

    /* GET METHODS */

    public int getId()
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