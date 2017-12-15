package enums;

public enum View
{
    LOADING_VIEW("LoadingView.fxml"),
    INIT_SESSION_VIEW("InitSessionView.fxml"),
    LOG_IN_VIEW("LogInView.fxml"),
    REGISTER_VIEW("RegisterView.fxml"),
    MAIN_GAME_VIEW("MainGameView.fxml"),
    GAME_IN_PROGRESS_VIEW("GameInProgressView.fxml"),
    GAME_SELECTION_VIEW("GameSelectionView.fxml"),
    LOAD_GAME_VIEW("LoadGameView.fxml"),
    RANKING_VIEW("RankingView.fxml"),
    SHOW_INFO_VIEW("ShowInfoView.fxml"),
    EDIT_USER_VIEW("EditUserView.fxml"),
    PAUSE_VIEW("PauseView.fxml"),
    SAVE_GAME_VIEW("SaveGameView.fxml"),
    SHOW_CLUE_VIEW("ShowClueView.fxml"),
    GAME_OVER_VIEW("GameOverView.fxml");

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
