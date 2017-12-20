package enums;

public enum View
{
    EDIT_USER_VIEW("EditUserView.fxml"),
    CLOSE_PROGRAM_WARNING_VIEW("CloseProgramWarningView.fxml"),
    GAME_IN_PROGRESS_VIEW("GameInProgressView.fxml"),
    GAME_OVER_VIEW("GameOverView.fxml"),
    GAME_SELECTION_VIEW("GameSelectionView.fxml"),
    INIT_SESSION_VIEW("InitSessionView.fxml"),
    LOAD_GAME_VIEW("LoadGameView.fxml"),
    LOADING_VIEW("LoadingView.fxml"),
    LOG_IN_VIEW("LogInView.fxml"),
    LOG_OUT_WARNING_VIEW("LogOutWarningView.fxml"),
    MAIN_GAME_VIEW("MainGameView.fxml"),
    RANKING_VIEW("RankingView.fxml"),
    REGISTER_VIEW("RegisterView.fxml"),
    SAVE_GAME_VIEW("SaveGameView.fxml"),
    SHOW_HINT_VIEW("ShowClueView.fxml"),
    SHOW_INFO_VIEW("ShowInfoView.fxml"),
    PAUSE_VIEW("PauseView.fxml");


    private final String viewFile;

    View(final String viewFile)
    {
        this.viewFile = viewFile;
    }

    public String getViewFile()
    {
        return this.viewFile;
    }
}
