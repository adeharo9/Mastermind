package domain.classes;

import util.*;

public class CodeCorrect extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CodeCorrect()
    {
        code = new Turn();
    }

    public CodeCorrect(CodeCorrect codeCorrect) throws IllegalArgumentException, NullPointerException
    {
        super(codeCorrect);
    }

    public CodeCorrect(Code code) {
        super(code);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public CodeCorrect deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CodeCorrect(this);
    }
}
