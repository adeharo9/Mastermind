package domain.classes;

import util.*;
import java.util.*;

public class Action implements DeepCopyable
{
    /* ATTRIBUTES */

    private Combination combination;

    /* CONSTRUCTION METHODS */

    public Action()
    {

    }

    public Action(Action action) throws Exception
    {
        boolean b = setCombination(action.getCombination());
        if(!b) throw new IllegalArgumentException();
    }

    /* SET METHODS */

    public boolean setCombination(Combination combination) throws Exception
    {
        boolean b = combination != null && combination.isValid();

        if(b)
        {
            this.combination = combination.deepCopy();
        }

        return b;
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

    public Action deepCopy() throws Exception
    {
        return new Action(this);
    }
}