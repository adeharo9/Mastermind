package util;

public enum State
{
    ASK_FOR_CLUE,

    CHECK_INFO,
    CHECK_RANKING,
    CHECK_TURN_NUMBER,
    CLOSE_PROGRAM,
    CLOSE_PROGRAM_WARNING,
    CONTINUE_GAME,

    EXIT_GAME_WITHOUT_SAVING,
    EXIT_GAME_WITHOUT_SAVING_WARNING,

    GAME_DIFFICULTY_SELECTION_MENU,
    GAME_MODE_SELECTION_MENU,
    GAME_OVER_MENU,
    GAME_PAUSE_MENU,
    GAME_ROLE_SELECTION_MENU,

    IN_GAME_MENU,
    INIT_PROGRAM,
    INIT_SESSION_MENU,

    LOAD_GAME,
    LOAD_GAME_MENU,
    LOAD_SAVED_GAMES_LIST,
    LOG_IN_USER,
    LOG_IN_USER_MENU,
    LOG_OUT_WARNING,

    MAIN_GAME_MENU,

    NEW_GAME,

    PLAY_TURN,

    REGISTER_USER,
    REGISTER_USER_MENU,
    RESTART_GAME,

    SAVE_GAME_AND_CONTINUE,
    SAVE_GAME_AND_EXIT;
}