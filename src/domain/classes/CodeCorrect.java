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

    public CodeCorrect(CodeCorrect codeCorrect)
    {
        super(codeCorrect);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public CodeCorrect deepCopy()
    {
        return new CodeCorrect(this);
    }
}