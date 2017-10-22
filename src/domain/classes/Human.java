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

        username = null;
        password = null;
    }

    public Human(String username, String password)
    {
        boolean b;

        try {
            b = setUsername(username);
            if(!b) throw new Exception("");

            b = setPassword(password);
            if(!b) throw new Exception("");
        }
        catch(Exception e)
        {
            ioUtils.printErrLn(e.getMessage());
        }
    }

    public Human(Human human)
    {
        super(human);

        boolean b;

        try
        {
            b = setUsername(human.getUsername());
            if(!b) throw new Exception("");

            b = setPassword(human.getPassword());
            if(!b) throw new Exception("");
        }
        catch(Exception e)
        {
            ioUtils.printErrLn(e.getMessage());
        }
    }

    /* SET METHODS */

    public boolean setUsername(String username)
    {
        boolean b = username != null && !username.isEmpty();

        if(b)
        {
            this.username = username;
        }

        return b;
    }

    public boolean setPassword(String password)
    {
        boolean b = password != null && !password.isEmpty();

        if(b)
        {
            this.password = password;
        }

        return b;
    }

    /* GET METHODS */

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    /* TESTING METHODS */

    public boolean checkPassword(String password)
    {
        return this.password != null && this.password.equals(password);
    }

    /* CLONING METHODS */

    public Human deepCopy()
    {
        return new Human(this);
    }
}