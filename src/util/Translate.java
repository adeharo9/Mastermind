package util;

import domain.classes.Code;
import exceptions.RollbackException;

public abstract class Translate
{
    /* DIFFICULTY METHODS */

    public static Difficulty int2Difficulty(int diff) throws IllegalArgumentException, RollbackException
    {
        Difficulty difficulty;

        switch(diff)
        {
            case 0:
                throw new RollbackException();
            case 1:
                difficulty = Difficulty.easy;
                break;
            case 2:
                difficulty = Difficulty.medium;
                break;
            case 3:
                difficulty = Difficulty.hard;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return difficulty;
    }

    /* ROLE METHODS */

    @Deprecated
    public static int difficulty2Int(Difficulty difficulty) throws IllegalArgumentException
    {
        int diff;

        switch(difficulty)
        {
            case easy:
                diff = 1;
                break;
            case medium:
                diff = 2;
                break;
            case hard:
                diff = 3;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return diff;
    }

    public static Role int2Role(int rol) throws IllegalArgumentException, RollbackException
    {
        Role role;

        switch(rol)
        {
            case 0:
                throw new RollbackException();
            case 1:
                role = Role.CODE_MAKER;
                break;
            case 2:
                role = Role.CODE_BREAKER;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return role;
    }

    @Deprecated
    public static int role2Int(Role role) throws IllegalArgumentException
    {
        int rol;

        switch(role)
        {
            case CODE_MAKER:
                rol = 1;
                break;
            case CODE_BREAKER:
                rol = 2;
                break;
            case watcher:
                rol = 3;
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
                state = State.END_PROGRAM;
                break;
            case 1:
                state = State.REGISTER_USER_MENU;
                break;
            case 2:
                state = State.LOG_IN_USER_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateMainGameMenu(int stat) throws IllegalArgumentException
    {
        State state;

        switch(stat)
        {
            case 0:
                state = State.INIT_SESSION_MENU;
                break;
            case 1:
                state = State.GAME_MODE_SELECTION_MENU;
                break;
            case 2:
                state = State.LOAD_GAME_MENU;
                break;
            case 3:
                state = State.CHECK_RANKING;
                break;
            case 4:
                state = State.CHECK_INFO;
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
                state = State.GAME_PAUSE_MENU;
                break;
            case 1:
                state = State.PLAY_TURN;
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
                state = State.CONTINUE_GAME;
                break;
            case 1:
                state = State.SAVE_GAME_AND_CONTINUE;
                break;
            case 2:
                state = State.SAVE_GAME_AND_EXIT;
                break;
            case 3:
                state = State.EXIT_GAME_WITHOUT_SAVING;
                break;
            case 4:
                state = State.ASK_FOR_CLUE;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static String code2String(Code code)
    {
        return "";
    }
}
