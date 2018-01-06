package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.Constants;

public class LogInViewController extends RegisteringPresentationController
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

    /**
     * Método de gestión de botón Back.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Back.
     */
    @FXML
    public void backButtonAction()
    {
        pressButtonAction(0);
    }

    /**
     * Método de gestión de botón Log In.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Log In.
     */
    @FXML
    public void logInButtonAction()
    {
        errorLabel.setVisible(false);

        PresentationController.username = usernameTextField.getText();
        PresentationController.password = passwordPasswordField.getText();

        pressButtonAction(1);

    }

    @Override
    public void processInfo(final Object info)
    {
        String message = (String) info;

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
