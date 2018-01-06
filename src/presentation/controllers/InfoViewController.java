package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoViewController extends RegisteringPresentationController
{
    @FXML private Label infoLabel;

    public InfoViewController()
    {

    }

    @FXML
    public void initialize()
    {
        endAction();
    }

    @Override
    public void processInfo(final Object info)
    {
        String message = (String) info;

        infoLabel.setText(message);
    }

    @FXML
    public void backButtonAction()
    {
        pressButtonAction(0);
    }
}
