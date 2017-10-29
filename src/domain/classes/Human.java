package domain.classes;

import util.*;

import java.io.Serializable;

public class Human extends Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private String password;

    /* CONSTRUCTION METHODS */

    public Human()
    {
        super();
    }

    public Human(String id) throws IllegalArgumentException
    {
        super(id);
    }

    public Human(String username, String password) throws IllegalArgumentException, NullPointerException
    {
        setId(username);
        setPassword(password);
    }

    public Human(Human human) throws IllegalArgumentException, NullPointerException
    {
        super(human);

        setId(human.getId());
        setPassword(human.getPassword());
    }

    /* SET METHODS */

    public void setPassword(String password) throws IllegalArgumentException, NullPointerException
    {
        boolean b = !password.isEmpty();
        if(!b) throw new IllegalArgumentException();

        this.password = password;
    }

    /* GET METHODS */

    @Deprecated
    public String getUsername()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    /* TESTING METHODS */

    public boolean checkPassword(String password) throws NullPointerException
    {
        return this.password.equals(password);
    }

    /* CLONING METHODS */

    public Human deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Human(this);
    }
}