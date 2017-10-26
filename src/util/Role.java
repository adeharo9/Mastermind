package util;

import java.io.Serializable;

public enum Role implements Serializable
{
    codeMaker,
    codeBreaker,
    watcher;

    public static boolean isValid(Role role)
    {
        return role != null;
    }
}
