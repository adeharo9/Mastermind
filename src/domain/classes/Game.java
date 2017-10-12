package domain.classes;

import util.*;
import java.util.*;

public class Game implements DeepCopyable
{
    /* ATTRIBUTES */

    private int id;
    private int difficulty;
    private int time;

    /* CONSTRUCTION METHODS */

    public Game()
    {

    }

    public Game(Game game)
    {
        id = game.id;
        difficulty = game.difficulty;
        time = game.time;
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public Game deepCopy()
    {
        return new Game(this);
    }
}