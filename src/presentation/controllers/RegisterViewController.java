package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.Constants;

/**
 * Controlador de vista de menú de registro.
 *
 * Clase encargada de gestionar los distintos elementos de la vista de menú de registro.
 *
 * @author Alex Sánchez
 */

public class RegisterViewController extends RegisteringPresentationController
{
    /* ATTRIBUTES */

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;
    @FXML private PasswordField confirmPasswordPasswordField;
    @FXML private Label errorLabel;

    /* CONSTRUCTORS */

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de menú de registro.
     */
    public RegisterViewController()
    {

    }

    /* GUI INTERACTION */

    /**
     * Método de procesado de información proveniente del controlador de dominio.
     *
     * Método encargado de recibir información genérica proveniente del controlador
     * de dominio, concretamente de recibir mensajes de error y mostrarlos adecuadamente.
     *
     * @param info Información recibida desde el controlador de dominio.
     */
    @Override
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
