package enums;

/**
 * enum View.
 *
 * Diferentes vistas del programa.
 *
 * @author Alejandro de Haro
 */

public enum View
{
    CLOSE_PROGRAM_WARNING_VIEW ("CloseProgramWarningView.fxml"),
    EDIT_USER_VIEW ("EditUserView.fxml"),
    ERROR_MESSAGE_WARNING_VIEW ("ErrorMessageWarningView.fxml"),
    EXIT_CURRENT_GAME_WARNING_VIEW ("ExitCurrentGameWarningView.fxml"),
    GAME_IN_PROGRESS_VIEW ("GameInProgressView.fxml"),
    GAME_OVER_VIEW ("GameOverView.fxml"),
    INIT_SESSION_VIEW ("InitSessionView.fxml"),
    LOAD_GAME_VIEW ("LoadGameView.fxml"),
    LOADING_VIEW ("LoadingView.fxml"),
    LOG_IN_VIEW ("LogInView.fxml"),
    LOG_OUT_WARNING_VIEW ("LogOutWarningView.fxml"),
    MAIN_MENU_VIEW ("MainMenuView.fxml"),
    NEW_GAME_VIEW ("NewGameView.fxml"),
    RANKING_VIEW ("RankingView.fxml"),
    REGISTER_VIEW ("RegisterView.fxml"),
    SAVE_GAME_VIEW ("SaveGameView.fxml"),
    SAVE_GAME_OVERWRITE_VIEW ("SaveGameOverwriteView.fxml"),
    SHOW_CODE_VIEW ("ShowCodeView.fxml"),
    HINT_VIEW ("HintView.fxml"),
    INFO_VIEW ("InfoView.fxml"),
    PAUSE_VIEW ("PauseView.fxml");


    private final String viewFile;

    /**
     * Creadora.
     *
     * Crea una vista con el valor
     * del parametro de entrada.
     *
     * @param viewFile Archivo .fxml de la vista.
     */

    View(final String viewFile)
    {
        this.viewFile = viewFile;
    }

    /**
     * Getter viewFile.
     *
     * Devuelve el nombre del archivo
     * que contiene la vista.
     *
     * @return nombre del archivo de la vista.
     */

    public String getViewFile()
    {
        return this.viewFile;
    }
}
