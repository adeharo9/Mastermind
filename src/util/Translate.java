package util;

public abstract class Translate
{
    public static Difficulty int2Difficulty(int diff)
    {
        Difficulty difficulty;

        switch(diff)
        {
            case 0:
                difficulty = Difficulty.easy;
                break;
            case 1:
                difficulty = Difficulty.medium;
                break;
            case 2:
                difficulty = Difficulty.hard;
                break;
            default:
                difficulty = null;
                break;
        }

        return difficulty;
    }

    public static Role int2Role(int rol)
    {
        Role role;

        switch(rol)
        {
            case 0:
                role = Role.codeMaker;
                break;
            case 1:
                role = Role.codeBreaker;
                break;
            case 2:
                role = Role.watcher;
                break;
            default:
                role = null;
                break;
        }

        return role;
    }
}
