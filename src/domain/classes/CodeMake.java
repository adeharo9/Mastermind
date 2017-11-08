package domain.classes;

import util.*;

public class CodeMake extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CodeMake()
    {
        code = new Code();
    }

    public CodeMake(Code code) {
        super(code);
    }

    public CodeMake(CodeMake codeMake) throws IllegalArgumentException, NullPointerException
    {
        super(codeMake);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public CodeMake deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CodeMake(this);
    }
}