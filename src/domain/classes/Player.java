package domain.classes;

import util.*;
import java.util.*;

public class Player implements DeepCopyable
{
    /* ATTRIBUTES */

    private int id;
    private int points;
    private String role;

    /* CONSTRUCTION METHODS */

    public Player ()
    {

    }

    public Player (Player player)
    {
        id = player.id;
        points = player.points;
        role = player.role;
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public Player deepCopy ()
    {
        return new Player (this);
    }
}