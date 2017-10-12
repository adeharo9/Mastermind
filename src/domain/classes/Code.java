package domain.classes;

import util.*;

public class Code extends Combination implements DeepCopyable
{
    /* CONSTRUCTION METHODS */

    public Code ()
    {
        super ();
    }

    public Code (int n)
    {
        super (n);
    }

    public Code (Code code)
    {
        super (code);
    }

    /* CLONING METHODS */

    public Code deepCopy ()
    {
        return new Code (this);
    }
}