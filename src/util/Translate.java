package util;

import enums.Difficulty;
import enums.Mode;
import enums.Role;
import enums.State;

/**
 * Clase abstracta de métodos de traducción.
 *
 * Contiene los métodos de traducción necesarios para interpretar de forma
 * correcta el input del usuario en formato texto e interpretarlo en el formato
 * interno del programa.
 *
 * @author Alejandro de Haro
 */
public abstract class Translate
{
    /* PRIVATE METHODS */

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
                state = State.NEW_GAME_MENU;
                break;
            case 2:
                state = State.LOAD_GAME_MENU;
                break;
            case 3:
                state = State.RANKING_MENU;
                break;
            case 4:
                state = State.INFO_MENU;
                break;
            case 5:
                state = State.EDIT_USER_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateInfoMenu(final int stat) throws IllegalArgumentException
    {
        State state;

        switch (stat)
        {
            case 0:
                state = State.MAIN_MENU;
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
                state = State.SAVE_GAME_MENU;
                break;
            case 2:
                state = State.EXIT_CURRENT_GAME_WARNING;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateGameSettingsMenu(int stat) throws IllegalArgumentException
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

    public static State int2StateLoadGameMenu(int stat)
    {
        State state;

        switch (stat)
        {
            case 0:
                state = State.MAIN_MENU;
                break;
            case 1:
                state = State.LOAD_GAME;
                break;
            case 2:
                state = State.DELETE_GAME;
                break;
            default:
                throw new IllegalArgumentException();
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
                state = State.NEW_GAME_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State int2StateExitCurrentGameWarning(int state) throws IllegalArgumentException
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

        if(!hasStarted)
        {
            state = State.PLAY_CODE_MAKER;
        }
        else if(hasCodeToCorrect)
        {
            state = State.PLAY_CODE_CORRECTER;
        }
        else
        {
            state = State.PLAY_CODE_BREAKER;
        }

        return state;
    }

    public static State intToStateRankingMenu(int returnState) throws IllegalArgumentException
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

    public static State intToStatePlay(int returnState) throws IllegalArgumentException
    {
        State state;

        switch (returnState)
        {
            case 0:
                state = State.GAME_PAUSE_MENU;
                break;
            case 1:
                state = State.HINT_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State intToStateHintMenu(int returnState) throws IllegalArgumentException
    {
        State state;

        switch (returnState)
        {
            case 0:
                state = State.RENDER_BOARD;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State intToStateSaveGameMenu(int returnState) throws IllegalArgumentException
    {
        State state;

        switch (returnState)
        {
            case 0:
                state = State.GAME_PAUSE_MENU;
                break;
            case 1:
                state = State.SAVE_GAME;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State intToStateSaveGameOverwrite(int returnState) throws IllegalArgumentException
    {
        State state;

        switch (returnState)
        {
            case 0:
                state = State.GAME_PAUSE_MENU;
                break;
            case 1:
                state = State.SAVE_GAME;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }

    public static State intToStateErrorMessageWarning(int returnState) throws IllegalArgumentException
    {
        State state;

        switch (returnState)
        {
            case 0:
                state = State.GAME_PAUSE_MENU;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return state;
    }
}
