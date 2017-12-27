package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.Constants;

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
    public void backButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void logInButtonAction() throws IOException
    {
        errorLabel.setVisible(false);

        PresentationController.username = usernameTextField.getText();
        PresentationController.password = passwordPasswordField.getText();

        pressButtonAction(1);

    }

    public void showMessage(final String message)
    {
        switch (message)
        {
            case Constants.WRONG_USERNAME_OR_PASSWORD:
                usernameTextField.clear();
                passwordPasswordField.clear();
                break;
        }

        errorLabel.setVisible(true);
        errorLabel.setText(message);
    }
}
