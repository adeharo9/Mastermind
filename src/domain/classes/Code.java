package domain.classes;

import util.*;

import java.io.Serializable;

public class Code extends Combination implements DeepCopyable, Serializable
{
    /* CONSTRUCTION METHODS */

    public Code()
    {
        super();
    }

    public Code(int n) throws IllegalArgumentException
    {
        super(n);
    }

    public Code(Code code) throws IllegalArgumentException, NullPointerException
    {
        super(code);
    }

    /* CLONING METHODS */

    public Code deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Code(this);
    }
}