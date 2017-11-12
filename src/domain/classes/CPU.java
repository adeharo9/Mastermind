package domain.classes;

import util.DeepCopyable;

import java.io.Serializable;

public class CPU extends Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CPU()
    {
        super();
    }

    public CPU(final String id) throws IllegalArgumentException
    {
        super(id);
    }

    public CPU(final CPU cpu) throws IllegalArgumentException, NullPointerException
    {
        super(cpu);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public CPU deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CPU(this);
    }
}