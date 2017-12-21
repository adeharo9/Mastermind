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
                state = State.REGISTER_MENU;
                break;
            case 2:
                state = State.LOG_IN_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateEditUserMenu(final int stat) throws IllegalArgumentException
    {
        State state;

        switch (stat)
        {
            case 0:
                state = State.MAIN_MENU;
                break;
            case 1:
                state = State.EDIT_USERNAME;
                break;
            case 2:
                state = State.EDIT_PASSWORD;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateMainMenu(int stat) throws IllegalArgumentException
    {
        State state;

        switch(stat)
        {
            case 0:
                state = State.LOG_OUT_WARNING;
                break;
            case 1:
                state = State.GAME_SETTINGS_MENU;
                break;
            case 2:
                state = State.LOAD_SAVED_GAMES_LIST;
                break;
            case 3:
                state = State.SHOW_RANKING;
                break;
            case 4:
                state = State.CHECK_INFO;
                break;
            case 5:
                state = State.EDIT_USER_MENU;
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
                state = State.SAVE_GAME;
                break;
            /*case 2:
                state = State.SAVE_GAME_AND_EXIT;
                break;*/
            case 2:
                state = State.HINT_MENU;
                break;
            case 3:
                state = State.EXIT_CURRENT_GAME_WARNING;
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
                state = State.MAIN_MENU;
                break;
            case 1:
                state = State.NEW_GAME;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    /*public static State int2StateGameRoleSelectionMenu(int stat) throws IllegalArgumentException
    {
        State state;

        switch (stat)
        {
            case 0:
                state = State.GAME_SETTINGS_MENU;
                break;
            case 1:
            case 2:
                state = State.GAME_DIFFICULTY_SELECTION_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }*/

    /*public static State int2StateGameDifficultySelectionMenuHuman(int state) throws IllegalArgumentException
    {
        return int2StateGameDifficultySelectionMenu(state, State.GAME_ROLE_SELECTION_MENU);
    }*/

    public static State int2StateGameDifficultySelectionMenuCPU(int state) throws IllegalArgumentException
    {
        return int2StateGameDifficultySelectionMenu(state, State.GAME_SETTINGS_MENU);
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
                state = State.MAIN_MENU;
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
                state = State.MAIN_MENU;
                break;
            case 1:
                state = State.RESTART_GAME;
                break;
            case 2:
                state = State.GAME_SETTINGS_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateExitGameWarning(int state) throws IllegalArgumentException
    {
        return int2StateYesNo(state, State.EXIT_CURRENT_GAME, State.GAME_PAUSE_MENU);
    }

    public static State int2StateCloseProgramWarning(int state) throws IllegalArgumentException
    {
        return int2StateYesNo(state, State.LAST_ACTIONS, State.INIT_SESSION_MENU);
    }

    public static State int2StateLogOutWarning(int state) throws IllegalArgumentException
    {
        return int2StateYesNo(state, State.INIT_SESSION_MENU, State.MAIN_MENU);
    }

    public static State booleanModeToStateCheckGameHasFinished(boolean hasFinished) throws IllegalArgumentException
    {
        State state;

        if(hasFinished)
        {
            state = State.GAME_OVER_MENU;
        }
        else
        {
            state = State.PLAY_TURN;
        }

        return state;
    }

    public static State booleanToStatePlayTurn(boolean hasStarted, boolean hasCodeToCorrect)
    {
        State state;

        if(!hasStarted || hasCodeToCorrect)
        {
            state = State.PLAY_CODE_MAKER;
        }
        else
        {
            state = State.PLAY_CODE_BREAKER;
        }

        return state;
    }

    public static State intToStateShowRanking(int returnState) throws IllegalArgumentException
    {
        State state;

        switch (returnState)
        {
            case 0:
                state = State.MAIN_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State intToStateLogInMenu(int returnState) throws IllegalArgumentException
    {
        State state;

        switch (returnState)
        {
            case 0:
                state = State.INIT_SESSION_MENU;
                break;
            case 1:
                state = State.LOG_IN_USER;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State intToStateRegisterMenu(int returnState) throws IllegalArgumentException
    {
        State state;

        switch (returnState)
        {
            case 0:
                state = State.INIT_SESSION_MENU;
                break;
            case 1:
                state = State.REGISTER_USER;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }
}
