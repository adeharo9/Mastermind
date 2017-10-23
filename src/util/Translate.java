package util;

public abstract class Translate
{
    /* DIFFICULTY METHODS */

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

    /* ROLE METHODS */

    public static int difficulty2Int(Difficulty difficulty)
    {
        int diff;

        switch(difficulty)
        {
            case easy:
                diff = 0;
                break;
            case medium:
                diff = 1;
                break;
            case hard:
                diff = 2;
                break;
            default:
                diff = -1;
                break;
        }

        return diff;
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

    public static int role2Int(Role role)
    {
        int rol;

        switch(role)
        {
            case codeMaker:
                rol = 0;
                break;
            case codeBreaker:
                rol = 1;
                break;
            case watcher:
                rol = 2;
                break;
            default:
                rol = -1;
                break;
        }

        return rol;
    }
}
