package util;

import java.io.Serializable;

public enum Difficulty implements Serializable
{
    easy,
    medium,
    hard;

    @Deprecated
    public static boolean isValid(Difficulty difficulty)
    {
        return difficulty != null;
    }
}
