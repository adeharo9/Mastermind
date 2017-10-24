package util;

public abstract class Translate
{
    /* DIFFICULTY METHODS */

    public static Difficulty int2Difficulty(int diff) throws IllegalArgumentException
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
                throw new IllegalArgumentException();
        }

        return difficulty;
    }

    /* ROLE METHODS */

    public static int difficulty2Int(Difficulty difficulty) throws IllegalArgumentException
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
                throw new IllegalArgumentException();
        }

        return diff;
    }

    public static Role int2Role(int rol) throws IllegalArgumentException
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
                throw new IllegalArgumentException();
        }

        return role;
    }

    public static int role2Int(Role role) throws IllegalArgumentException
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
                throw new IllegalArgumentException();
        }

        return rol;
    }

    public static State int2StateInitSession(int stat) throws IllegalArgumentException
    {
        State state;

        switch(stat)
        {
            case 0:
                state = State.endProgram;
                break;
            case 1:
                state = State.registerUser;
                break;
            case 2:
                state = State.logInUser;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateGameSelection(int stat) throws IllegalArgumentException
    {
        State state;

        switch(stat)
        {
            case 0:
                state = State.initSession;
                break;
            case 1:
                state = State.newGame;
                break;
            case 2:
                state = State.loadGame;
                break;
            case 3:
                state = State.checkRanking;
                break;
            case 4:
                state = State.checkInfo;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StatePlaySelection(int stat) throws IllegalArgumentException
    {
        State state;

        switch(stat)
        {
            case 0:
                state = State.playTurn;
                break;
            case 1:
                state = State.gamePause;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateGamePause(int stat) throws IllegalArgumentException
    {
        State state;

        switch(stat)
        {
            case 0:
                state = State.playSelection;
                break;
            case 1:
                state = State.saveGameAndContinue;
                break;
            case 2:
                state = State.saveGameAndExit;
                break;
            case 3:
                state = State.exitGameWithoutSaving;
                break;
            case 4:
                state = State.askForClue;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }
}
