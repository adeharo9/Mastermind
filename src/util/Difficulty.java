package util;

import java.io.Serializable;

public enum Difficulty implements Serializable
{
    EASY,
    MEDIUM,
    HARD;

    @Deprecated
    public static boolean isValid(Difficulty difficulty)
    {
        return difficulty != null;
    }
}
