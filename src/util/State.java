package util;

public enum State
{
    ASK_FOR_CLUE,

    CHECK_INFO,
    CHECK_RANKING,
    CONTINUE_GAME,

    END_PROGRAM,
    EXIT_GAME_WITHOUT_SAVING,

    GAME_DIFFICULTY_SELECTION_MENU,
    GAME_MODE_SELECTION_MENU,
    GAME_ROLE_SELECTION_MENU,
    GAME_PAUSE_MENU,

    INIT_PROGRAM,
    INIT_SESSION_MENU,

    LOAD_GAME_MENU,
    LOG_IN_USER,
    LOG_IN_USER_MENU,

    MAIN_GAME_MENU,

    NEW_GAME,

    IN_GAME_MENU,
    PLAY_TURN,

    REGISTER_USER,
    REGISTER_USER_MENU,

    SAVE_GAME_AND_CONTINUE,
    SAVE_GAME_AND_EXIT;

    @Deprecated
    public static boolean isValid(State state)
    {
        return state != null;
    }
}