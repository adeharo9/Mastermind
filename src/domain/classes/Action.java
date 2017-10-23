package domain.classes;

import util.*;
import java.util.*;

public abstract class Action implements DeepCopyable
{
    /* ATTRIBUTES */

    protected Combination combination;

    /* CONSTRUCTION METHODS */

    public Action()
    {

    }

    public Action(Action action) throws IllegalArgumentException, NullPointerException
    {
        setCombination(action.getCombination());
    }

    /* SET METHODS */

    public void setCombination(Combination combination) throws IllegalArgumentException, NullPointerException
    {
        boolean b = combination.isValid();
        if(!b) throw new IllegalArgumentException();

        this.combination = combination.deepCopy();
    }

    /* GET METHODS */

    public Combination getCombination()
    {
        return combination;
    }

    /* TESTING METHODS */

    public boolean isValid()
    {
        return combination != null;
    }

    /* CLONING METHODS */

    public abstract Action deepCopy();
}