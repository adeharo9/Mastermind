package util;

import domain.classes.Code;

public abstract class Translate
{
    /* PRIVATE METHODS */

    private static State int2StateGameDifficultySelectionMenu(int stat, State state0) throws IllegalArgumentException
    {
        State state;

        switch (stat)
        {
            case 0:
                state = state0;
                break;
            case 1:
            case 2:
            case 3:
                state = State.NEW_GAME;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    /* PUBLIC METHODS */

    public static Mode int2Mode(int mod) throws IllegalArgumentException
    {
        Mode mode;

        switch(mod)
        {
            case 0:
                mode = null;
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

    public static Role int2Role(int rol) throws IllegalArgumentException
    {
        Role role;

        switch(rol)
        {
            case 0:
                role = null;
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

    public static Difficulty int2Difficulty(int diff) throws IllegalArgumentException
    {
        Difficulty difficulty;

        switch(diff)
        {
            case 0:
                difficulty = null;
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

    public static State int2StateGameModeSelectionMenu(int stat) throws IllegalArgumentException
    {
        State state;

        switch (stat)
        {
            case 0:
                state = State.MAIN_GAME_MENU;
                break;
            case 1:
            case 2:
                state = State.GAME_ROLE_SELECTION_MENU;
                break;
            case 3:
                state = State.GAME_DIFFICULTY_SELECTION_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateGameRoleSelectionMenu(int stat) throws IllegalArgumentException
    {
        State state;

        switch (stat)
        {
            case 0:
                state = State.GAME_MODE_SELECTION_MENU;
                break;
            case 1:
            case 2:
                state = State.GAME_DIFFICULTY_SELECTION_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateGameDifficultySelectionMenuHuman(int state) throws IllegalArgumentException
    {
        return int2StateGameDifficultySelectionMenu(state, State.GAME_ROLE_SELECTION_MENU);
    }

    public static State int2StateGameDifficultySelectionMenuCPU(int state) throws IllegalArgumentException
    {
        return int2StateGameDifficultySelectionMenu(state, State.GAME_MODE_SELECTION_MENU);
    }

    public static String code2String(Code code)
    {
        return "";
    }
}
