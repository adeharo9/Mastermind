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

    public CodeMake(final Code code) {
        super(code);
    }

    public CodeMake(final CodeMake codeMake) throws IllegalArgumentException, NullPointerException
    {
        super(codeMake);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* OTHER METHODS */

    @Override
    public void addSelfToBoard(final Board board)
    {
        board.setSolution(getCode());
    }

    /* CLONING METHODS */

    public CodeMake deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CodeMake(this);
    }
}