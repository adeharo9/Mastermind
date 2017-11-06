package domain.classes;

import enums.Difficulty;
import util.*;

import java.io.Serializable;

public class Code extends Combination implements DeepCopyable, Serializable
{
    /* CONSTRUCTION METHODS */

    @Deprecated
    public Code()
    {
        super();
    }

    @Deprecated
    public Code(int n) throws IllegalArgumentException
    {
        super(n);
    }

    public Code(Difficulty difficulty)
    {
        super(difficulty);
    }

    public Code(Code code) throws IllegalArgumentException, NullPointerException
    {
        super(code);
    }

    /* OTHER METHODS */

    public Code getRandomCode(int n)
    {

    }

    /* CLONING METHODS */

    public Code deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Code(this);
    }
}