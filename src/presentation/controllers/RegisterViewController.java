package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.Constants;

public class RegisterViewController extends RegisteringPresentationController
{
    /* ATTRIBUTES */

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;
    @FXML private PasswordField confirmPasswordPasswordField;
    @FXML private Label errorLabel;

    /* CONSTRUCTORS */

    public RegisterViewController()
    {

    }

    /* GUI INTERACTION */

    public void processInfo(final Object info)
    {
        String message = (String) info;
        
        switch (message)
        {
            case Constants.USERNAME_ALREADY_EXISTS:
                usernameTextField.clear();

            case Constants.PASSWORDS_MUST_MATCH:
            case Constants.EMPTY_PASSWORD_ERROR:
                passwordPasswordField.clear();
                confirmPasswordPasswordField.clear();
                break;
        }

        errorLabel.setVisible(true);
        errorLabel.setText(message);
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
     * Método de gestión de botón Register.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Register.
     */
    @FXML
    public void registerButtonAction()
    {
        errorLabel.setVisible(false);

        PresentationController.username = usernameTextField.getText();
        PresentationController.password = passwordPasswordField.getText();
        PresentationController.confirmPassword = confirmPasswordPasswordField.getText();

        pressButtonAction(1);
    }
}
