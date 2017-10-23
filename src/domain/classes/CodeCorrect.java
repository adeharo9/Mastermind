package domain.classes;

import util.*;
import java.util.*;

public class CodeCorrect extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CodeCorrect()
    {
        super();
    }

    public CodeCorrect(CodeCorrect codeCorrect) throws Exception
    {
        super(codeCorrect);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public CodeCorrect deepCopy() throws Exception
    {
        return new CodeCorrect(this);
    }
}
