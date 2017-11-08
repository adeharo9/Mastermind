package domain.classes;

import util.*;

public class CodeBreak extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CodeBreak()
    {
        code = new Turn();
    }

    public CodeBreak(CodeBreak codeBreak) throws IllegalArgumentException, NullPointerException
    {
        super(codeBreak);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public CodeBreak deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CodeBreak(this);
    }
}