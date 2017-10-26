package util;

import java.io.Serializable;

public enum Difficulty implements Serializable
{
    easy,
    medium,
    hard;

    public static boolean isValid(Difficulty difficulty)
    {
        return difficulty != null;
    }
}
