package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInViewController extends PresentationController
{
    /* ATTRIBUTES */

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;

    /* CONSTRUCTORS */

    public LogInViewController()
    {

    }

    /* FXML */

    @FXML
    public void backButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void logInButtonAction(ActionEvent actionEvent) throws IOException
    {
        PresentationController.username = usernameTextField.getText();
        PresentationController.password = passwordTextField.getText();

        pressButtonAction(1);
    }
}