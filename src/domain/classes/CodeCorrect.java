package domain.classes;

import util.*;
import java.util.*;

public class CodeCorrect extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CodeCorrect()
    {
        combination = new Turn();
    }

    public CodeCorrect(CodeCorrect codeCorrect) throws IllegalArgumentException, NullPointerException
    {
        super(codeCorrect);
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
