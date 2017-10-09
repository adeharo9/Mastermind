package data.tokens;

public class Code extends Combination
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

    public Code cClone()
    {
        return new Code(this);
    }
}
