package domain.classes;

import util.*;
import java.util.*;

public class Player implements DeepCopyable
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

    public Player (Player player)
    {
        boolean b;

        try
        {
            b = setId(player.getId());
            if (!b) throw new Exception("");

            b = setPoints(player.getPoints());
            if (!b) throw new Exception("");
        }
        catch(Exception e)
        {
            ioUtils.printErrLn(e.getMessage());
        }
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

    public Player deepCopy ()
    {
        return new Player (this);
    }
}