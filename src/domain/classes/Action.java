package domain.classes;

import util.*;

public abstract class Action implements DeepCopyable
{
    /* ATTRIBUTES */

    protected Code code;

    /* CONSTRUCTION METHODS */

    public Action()
    {

    }

    public Action(Code code) {
        this.code = code;
    }

    public Action(Action action) throws IllegalArgumentException, NullPointerException
    {
        setCode(action.getCode());
    }

    /* SET METHODS */

    public void setCode(Code code) throws IllegalArgumentException, NullPointerException
    {
        boolean b = code.isValid();
        if(!b) throw new IllegalArgumentException();

        this.code = code.deepCopy();
    }

    /* GET METHODS */

    public Code getCode()
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