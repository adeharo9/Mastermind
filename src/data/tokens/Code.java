package data.tokens;

public class Code extends Combination
{
    /* CONSTRUCTION FUNCTIONS */

    public Code()
    {

    }

    public Code(Code code)
    {
        setBPins(code.getBPins());
    }

    /* CLONING FUNCTIONS */

    public Code cClone()
    {
        return new Code(this);
    }
}
