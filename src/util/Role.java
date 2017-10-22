package util;

public enum Role
{
    codeMaker,
    codeBreaker,
    watcher;

    public static boolean isValid(Role role)
    {
        return role != null;
    }
}
