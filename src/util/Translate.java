package util;

import enums.Difficulty;
import enums.Mode;
import enums.Role;
import enums.State;

import java.util.List;

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

    private static State int2StateYesNo(int stat, State stateYes, State stateNo) throws IllegalArgumentException
    {
        State state;

        switch (stat)
        {
            case 0:
                state = stateNo;
                break;
            case 1:
                state = stateYes;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    /* PUBLIC METHODS */

    /* ENUM TRANSLATE METHODS */

    public static Mode int2Mode(int mod) throws IllegalArgumentException
    {
        Mode mode;

        switch(mod)
        {
            case 0:
                mode = null;
                break;
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
                break;
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
                break;
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

    /* STATE TRANSLATION METHODS */

    public static State int2StateInitSessionMenu(int stat) throws IllegalArgumentException
    {
        State state;

        switch(stat)
        {
            case 0:
                state = State.CLOSE_PROGRAM_WARNING;
                break;
            case 1:
                state = State.REGISTER_GET_USERNAME_MENU;
                break;
            case 2:
                state = State.LOG_IN_GET_USERNAME_MENU;
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
                state = State.LOG_OUT_WARNING;
                break;
            case 1:
                state = State.GAME_MODE_SELECTION_MENU;
                break;
            case 2:
                state = State.LOAD_SAVED_GAMES_LIST;
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

    public static State int2StateInGameMenu(int stat) throws IllegalArgumentException
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

    public static State int2StateGamePauseMenu(int stat) throws IllegalArgumentException
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
                state = State.EXIT_GAME_WITHOUT_SAVING_WARNING;
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

    public static String int2SavedGameId(List<String> savedGames, int index) throws IllegalArgumentException
    {
        String savedGameId;

        if(index == 0)
        {
            savedGameId = null;
        }
        else if(index <= savedGames.size())
        {
            savedGameId = savedGames.get(index - 1);
        }
        else
        {
            throw new IllegalArgumentException();
        }

        return savedGameId;
    }

    public static State int2StateLoadGameMenu(int stat)
    {
        State state;

        switch (stat)
        {
            case 0:
                state = State.MAIN_GAME_MENU;
                break;
            default:
                state = State.LOAD_GAME;
                break;
        }

        return state;
    }

    public static State int2StateGameOverMenu(int stat) throws IllegalArgumentException
    {
        State state;

        switch (stat)
        {
            case 0:
                state = State.MAIN_GAME_MENU;
                break;
            case 1:
                state = State.RESTART_GAME;
                break;
            case 2:
                state = State.GAME_MODE_SELECTION_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateExitGameWarning(int state) throws IllegalArgumentException
    {
        return int2StateYesNo(state, State.EXIT_GAME_WITHOUT_SAVING, State.GAME_PAUSE_MENU);
    }

    public static State int2StateCloseProgramWarning(int state) throws IllegalArgumentException
    {
        return int2StateYesNo(state, State.CLOSE_PROGRAM, State.INIT_SESSION_MENU);
    }

    public static State int2StateLogOutWarning(int state) throws IllegalArgumentException
    {
        return int2StateYesNo(state, State.INIT_SESSION_MENU, State.MAIN_GAME_MENU);
    }

    public static State booleanModeToStateCheckTurnNumber(boolean hasFinished, Mode mode) throws IllegalArgumentException
    {
        State state;

        if(hasFinished)
        {
            state = State.GAME_OVER_MENU;
        }
        else
        {
            switch (mode)
            {
                case HUMAN_VS_HUMAN:
                case HUMAN_VS_CPU:
                    state = State.IN_GAME_MENU;
                    break;
                case CPU_VS_CPU:
                    state = State.PLAY_TURN;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        return state;
    }

    public static State booleanToStatePlayTurn(boolean hasStarted)
    {
        State state;

        if(hasStarted)
        {
            state = State.PLAY_CODEBREAKER;
        }
        else
        {
            state = State.PLAY_CODEMAKER;
        }

        return state;
    }
}
