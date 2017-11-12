package domain.classes;

import util.*;

import java.io.Serializable;

public class Human extends Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private String password;

    /* PRIVATE METHODS */

    private static boolean isValidPassword(final String password)
    {
        return !password.isEmpty();
    }

    /* CONSTRUCTION METHODS */

    public Human()
    {
        super();
        password = "";
    }

    public Human(final String username) throws IllegalArgumentException
    {
        super(username);
    }

    public Human(final String username, final String password) throws IllegalArgumentException, NullPointerException
    {
        super(username);

        setPassword(password);
    }

    public Human(final Human human) throws IllegalArgumentException, NullPointerException
    {
        super(human);

        setPassword(human.getPassword());
    }

    /* SET METHODS */

    public void setPassword(final String password) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPassword(password);
        if(!b) throw new IllegalArgumentException();

        this.password = password;
    }

    /* GET METHODS */

    @Deprecated
    public final String getUsername()
    {
        return super.getId();
    }

    public final String getPassword()
    {
        return password;
    }

    /* TESTING METHODS */

    public final boolean checkPassword(final String password) throws NullPointerException
    {
        return this.password.equals(password);
    }

    /* CLONING METHODS */

    public Human deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Human(this);
    }
}