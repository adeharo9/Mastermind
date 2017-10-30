package domain.classes;

import util.*;

import java.io.Serializable;

public class Human extends Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private String password;

    /* PRIVATE METHODS */

    private static boolean isValidPassword(String password)
    {
        return !password.isEmpty();
    }

    /* CONSTRUCTION METHODS */

    public Human()
    {
        super();
        password = "";
    }

    public Human(String username) throws IllegalArgumentException
    {
        super(username);
    }

    public Human(String username, String password) throws IllegalArgumentException, NullPointerException
    {
        setId(username);
        setPassword(password);
    }

    public Human(Human human) throws IllegalArgumentException, NullPointerException
    {
        super(human);

        setPassword(human.getPassword());
    }

    /* SET METHODS */

    public void setPassword(String password) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPassword(password);
        if(!b) throw new IllegalArgumentException();

        this.password = password;
    }

    /* GET METHODS */

    @Deprecated
    public String getUsername()
    {
        return super.getId();
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