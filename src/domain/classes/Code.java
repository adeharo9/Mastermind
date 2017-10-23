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

    public Code(Code code) throws Exception
    {
        super(code);
    }

    /* CLONING METHODS */

    public Code deepCopy() throws Exception
    {
        return new Code(this);
    }
}