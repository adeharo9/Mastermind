package util;

public enum Difficulty
{
    easy,
    medium,
    hard;

    public static boolean isValid(Difficulty difficulty)
    {
        return difficulty != null;
    }
}
