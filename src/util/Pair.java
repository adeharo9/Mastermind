package util;

import java.io.Serializable;

public class Pair<T1 extends Serializable, T2 extends Serializable> implements Serializable
{
    public T1 first;
    public T2 second;

    public Pair()
    {
        this.first = null;
        this.second = null;
    }

    public Pair(T1 first, T2 second)
    {
        this.first = first;
        this.second = second;
    }

    public boolean hasNull()
    {
        return first == null || second == null;
    }

    public static <T1 extends Serializable, T2 extends Serializable> boolean isValid(Pair<T1, T2> pair)
    {
        return pair != null && !pair.hasNull();
    }
}
