package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoadingViewController extends RegisteringPresentationController
{
    @FXML private Label errorLabel;

    public LoadingViewController()
    {

    }

    public void processInfo(final Object info)
    {
        String message = (String) info;

        errorLabel.setVisible(true);
        errorLabel.setText(message);
    }

    /* FXML */

    @FXML
    public void initialize()
    {
        errorLabel.setVisible(false);
    }
}
