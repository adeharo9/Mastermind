package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class InfoViewController extends PresentationController
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

    public void showMessage(final String message)
    {
        infoLabel.setText(message);
    }

    @FXML
    public void backButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }
}
