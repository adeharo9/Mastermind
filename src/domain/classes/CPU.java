package domain.classes;

import util.*;
import java.util.*;

public class CPU extends Player implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CPU()
    {
        super();
    }

    public CPU(CPU cpu) throws IllegalArgumentException, NullPointerException
    {
        super(cpu);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public CPU deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CPU(this);
    }
}