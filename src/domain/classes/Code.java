package domain.classes;

import util.*;

public class Code extends Combination implements DeepCopyable
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