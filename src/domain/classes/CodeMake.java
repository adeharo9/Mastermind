package domain.classes;

import util.*;
import java.util.*;

public class CodeMake extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CodeMake()
    {
        super();
    }

    public CodeMake(CodeMake codeMake) throws Exception
    {
        super(codeMake);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public CodeMake deepCopy() throws Exception
    {
        return new CodeMake(this);
    }
}