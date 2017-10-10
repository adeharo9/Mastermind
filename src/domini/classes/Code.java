package domini.classes;

import util.*;

public class Code extends Combination implements DeepCopyable
{
    /* CONSTRUCTION FUNCTIONS */

    public Code()
    {
        super();
    }

    public Code(int n)
    {
        super(n);
    }

    public Code(Code code)
    {
        super(code);
    }

    /* CLONING FUNCTIONS */

    public Code deepCopy()
    {
        return new Code(this);
    }
}