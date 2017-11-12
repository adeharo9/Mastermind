package domain.classes;

import util.*;

public class CodeBreak extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CodeBreak(final CodeBreak codeBreak) throws IllegalArgumentException, NullPointerException
    {
        super(codeBreak);
    }

    public CodeBreak(final Code code) {
        super(code);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* OTHER METHODS */

    @Override
    public void addSelfToBoard(final Board board)
    {
        board.addCode(getCode());
    }

    /* CLONING METHODS */

    public CodeBreak deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CodeBreak(this);
    }
}