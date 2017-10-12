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

    public CPU(CPU cpu)
    {
        super(cpu);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public CPU deepCopy()
    {
        return new CPU(this);
    }
}