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

    public Player (Player player) throws Exception
    {
        boolean b = setId(player.getId());
        if (!b) throw new IllegalArgumentException();

        b = setPoints(player.getPoints());
        if (!b) throw new IllegalArgumentException();
    }

    /* SET METHODS */

    public boolean setId(int id)
    {
        boolean b = isValidId(id);

        if(b)
        {
            this.id = id;
        }

        return b;
    }

    public boolean setPoints(int points)
    {
        this.points = points;

        return true;
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
        boolean b;

        b = isValidId(id);

        return b;
    }

    /* CLONING METHODS */

    public abstract Player deepCopy ();
}