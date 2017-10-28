package util;

import java.io.Serializable;

public enum Role implements Serializable
{
    CODE_MAKER,
    CODE_BREAKER,
    watcher;

    @Deprecated
    public static boolean isValid(Role role)
    {
        return role != null;
    }
}
