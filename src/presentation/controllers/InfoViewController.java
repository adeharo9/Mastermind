package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class InfoViewController extends PresentationController
{
    @FXML private Label infoLabel;
    @FXML private ScrollPane contentScrollPane;

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

        contentScrollPane.setFitToWidth(true);
        contentScrollPane.setFitToHeight(true);
    }

    @FXML
    public void backButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }
}
