package domain.classes;

import enums.Color;
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

    public void setRandomCode()
    {
        Color color;
        int n = this.size();

        for(int i = 0; i < n; ++i)
        {
            color = Color.getRandomColor(n + 2);
            addBPin(color);
        }
    }

    /* CLONING METHODS */

    public Code deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Code(this);
    }
}