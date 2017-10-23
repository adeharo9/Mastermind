package domain.classes;

import util.*;
import java.util.*;

public class CodeBreak extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CodeBreak()
    {
        super();
    }

    public CodeBreak(CodeBreak codeBreak) throws Exception
    {
        super(codeBreak);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public CodeBreak deepCopy() throws Exception
    {
        return new CodeBreak(this);
    }
}