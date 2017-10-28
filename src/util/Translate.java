package util;

import domain.classes.Code;
import exceptions.RollbackException;

public abstract class Translate
{
    /* DIFFICULTY METHODS */

    public static Mode int2Mode(int mod) throws IllegalArgumentException, RollbackException
    {
        Mode mode;

        switch(mod)
        {
            case 0:
                throw new RollbackException();
            case 1:
                mode = Mode.HUMAN_VS_HUMAN;
                break;
            case 2:
                mode = Mode.HUMAN_VS_CPU;
                break;
            case 3:
                mode = Mode.CPU_VS_CPU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return mode;
    }

    public static Difficulty int2Difficulty(int diff) throws IllegalArgumentException, RollbackException
    {
        Difficulty difficulty;

        switch(diff)
        {
            case 0:
                throw new RollbackException();
            case 1:
                difficulty = Difficulty.EASY;
                break;
            case 2:
                difficulty = Difficulty.MEDIUM;
                break;
            case 3:
                difficulty = Difficulty.HARD;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return difficulty;
    }

    /* ROLE METHODS */

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
