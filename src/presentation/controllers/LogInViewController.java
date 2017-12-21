package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInViewController extends PresentationController
{
    /* ATTRIBUTES */

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;
    @FXML private Label errorLabel;

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
        errorLabel.setVisible(false);

        PresentationController.username = usernameTextField.getText();
        PresentationController.password = passwordPasswordField.getText();

        pressButtonAction(1);

    }

    public void errorMessage(final String message)
    {
        errorLabel.setVisible(true);
        errorLabel.setText(message);

        usernameTextField.clear();
        passwordPasswordField.clear();
    }
}
