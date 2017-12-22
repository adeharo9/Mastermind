package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoadGameViewController extends PresentationController
{
    @FXML private Label errorLabel;
    @FXML private ScrollPane contentScrollPane;

    public LoadGameViewController()
    {

    }

    public void showMessage(String message)
    {
        errorLabel.setVisible(true);
        errorLabel.setText(message);
    }

    /* FXML */

    @FXML
    public void initialize()
    {
        contentScrollPane.setFitToHeight(true);
        contentScrollPane.setFitToWidth(true);
    }

    @FXML
    public void backButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void editButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(1);
    }
}
