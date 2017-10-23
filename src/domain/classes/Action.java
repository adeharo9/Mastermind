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

    public Action(Action action)
    {
        combination = action.combination.deepCopy();
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    public boolean isValid()
    {
        return combination != null;
    }

    /* CLONING METHODS */

    public Action deepCopy()
    {
        return new Action(this);
    }
}