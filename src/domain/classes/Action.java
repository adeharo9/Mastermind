package domain.classes;

import util.*;

public abstract class Action implements DeepCopyable
{
    /* ATTRIBUTES */

    protected Code code;

    /* CONSTRUCTION METHODS */

    protected Action()
    {

    }

    protected Action(final Code code) {
        this.code = code;
    }

    protected Action(final Action action) throws IllegalArgumentException, NullPointerException
    {
        setCode(action.getCode());
    }

    /* SET METHODS */

    public final void setCode(final Code code) throws IllegalArgumentException, NullPointerException
    {
        boolean b = code.isValid();
        if(!b) throw new IllegalArgumentException();

        this.code = code.deepCopy();
    }

    /* GET METHODS */

    public final Code getCode()
    {
        return code;
    }

    /* OTHER METHODS */

    public abstract void addSelfToBoard(Board board);

    /* VALIDATION METHODS */

    public boolean isValid()
    {
        return code != null;
    }

    /* CLONING METHODS */

    public abstract Action deepCopy();
}