package domain.classes;

import util.*;

public class Human extends Player implements DeepCopyable
{
    /* ATTRIBUTES */

    private String username;
    private String password;

    /* CONSTRUCTION METHODS */

    public Human()
    {
        super();
    }

    public Human(String username, String password)
    {
        setUsername(username);
        setPassword(password);
    }

    public Human(Human human)
    {
        super(human);

        username = human.username;
        password = human.password;
    }

    /* SET METHODS */

    public boolean setUsername(String username)
    {
        this.username = username;

        return true;
    }

    public boolean setPassword(String password)
    {
        this.password = password;

        return true;
    }

    /* GET METHODS */

    /* TESTING METHODS */

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    /* CLONING METHODS */

    public Human deepCopy()
    {
        return new Human(this);
    }
}