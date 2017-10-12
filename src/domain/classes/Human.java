package domain.classes;

import util.*;
import java.util.*;

public class Human extends Player implements DeepCopyable
{
    /* ATTRIBUTES */

    private String name;
    private String password;

    /* CONSTRUCTION METHODS */

    public Human()
    {
        super();
    }

    public Human(Human human)
    {
        super(human);

        name = human.name;
        password = human.password;
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public Human deepCopy()
    {
        return new Human(this);
    }
}