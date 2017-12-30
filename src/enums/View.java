package enums;

public enum View
{
    CLOSE_PROGRAM_WARNING_VIEW("CloseProgramWarningView.fxml"),
    EDIT_USER_VIEW("EditUserView.fxml"),
    EXIT_CURRENT_GAME_WARNING_VIEW("ExitCurrentGameWarningView.fxml"),
    GAME_IN_PROGRESS_VIEW("GameInProgressView.fxml"),
    GAME_OVER_VIEW("GameOverView.fxml"),
    NEW_GAME_VIEW("NewGameView.fxml"),
    INIT_SESSION_VIEW("InitSessionView.fxml"),
    LOAD_GAME_VIEW("LoadGameView.fxml"),
    LOADING_VIEW("LoadingView.fxml"),
    LOG_IN_VIEW("LogInView.fxml"),
    LOG_OUT_WARNING_VIEW("LogOutWarningView.fxml"),
    MAIN_MENU_VIEW("MainMenuView.fxml"),
    RANKING_VIEW("RankingView.fxml"),
    REGISTER_VIEW("RegisterView.fxml"),
    SAVE_GAME_VIEW("SaveGameView.fxml"),
    SHOW_CODE_VIEW("ShowCodeView.fxml"),
    HINT_VIEW("HintView.fxml"),
    INFO_VIEW("InfoView.fxml"),
    PAUSE_VIEW("PauseView.fxml");


    private final String viewFile;

    private View(final String viewFile)
    {
        this.viewFile = viewFile;
    }

    public String getViewFile()
    {
        return this.viewFile;
    }
}
