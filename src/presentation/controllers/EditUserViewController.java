package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import util.Constants;

/**
 * @author Alex Sánchez
 */

public class EditUserViewController extends RegisteringPresentationController
{
    /* ATTRIBUTES */

    @FXML private Label usernameLabel;
    @FXML private TextField newUsernameTextField;
    @FXML private PasswordField currentPasswordPasswordField;
    @FXML private PasswordField newPasswordPasswordField;
    @FXML private PasswordField confirmNewPasswordPasswordField;
    @FXML private Button editButton;
    @FXML private Button doneButton;
    @FXML private Label exceptionsLabel;

    /* CONSTRUCTORS */

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de edición de usuario.
     */
    public EditUserViewController()
    {

    }

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
            case Constants.EDIT_USERNAME:
                newUsernameTextField.setVisible(true);
                editButton.setVisible(false);
                doneButton.setVisible(true);
                exceptionsLabel.setVisible(false);
                break;
            case Constants.USERNAME_ALREADY_EXISTS:
                exceptionsLabel.setVisible(true);
                exceptionsLabel.setTextFill(Color.web("red"));
                exceptionsLabel.setText(Constants.USERNAME_ALREADY_EXISTS);
                break;
            case Constants.WRONG_PASSWORD:
                exceptionsLabel.setVisible(true);
                exceptionsLabel.setTextFill(Color.web("red"));
                exceptionsLabel.setText(Constants.WRONG_PASSWORD);
                break;
            case Constants.PASSWORDS_MUST_MATCH:
                exceptionsLabel.setVisible(true);
                exceptionsLabel.setTextFill(Color.web("red"));
                exceptionsLabel.setText(Constants.PASSWORDS_MUST_MATCH);
                break;
            case Constants.NEW_PASSWORD_SAVED:
                exceptionsLabel.setVisible(true);
                exceptionsLabel.setTextFill(Color.web("green"));
                exceptionsLabel.setText(Constants.NEW_PASSWORD_SAVED);
                break;
            default:
                newPasswordPasswordField.clear();
                confirmNewPasswordPasswordField.clear();
                currentPasswordPasswordField.clear();
                usernameLabel.setText(message);
                break;
        }
    }

    /* FXML */

    /**
     * Método de captura de inicialización de JavaFX.
     *
     * Método a ejecutar cuando todos los campos fxml han sido construidos
     * e inicializados, implementado con la finalidad de no bloquear el controlador
     * de dominio.
     */
    @FXML
    public void initialize()
    {
        endAction();
    }

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
     * Método de gestión de botón Edit.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Edit.
     */
    @FXML
    public void editButtonAction()
    {
        pressButtonAction(1);
    }

    /**
     * Método de gestión de botón Done.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Done.
     */
    @FXML
    public void doneButtonAction()
    {
        PresentationController.username = newUsernameTextField.getText();
        newUsernameTextField.setVisible(false);
        doneButton.setVisible(false);
        editButton.setVisible(true);
        endAction();
    }

    /**
     * Método de gestión de botón Update Password.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Update Password.
     */
    @FXML
    public void updatePasswordButtonAction()
    {
        PresentationController.newPassword = newPasswordPasswordField.getText();
        PresentationController.confirmPassword = confirmNewPasswordPasswordField.getText();
        PresentationController.currentPassword = currentPasswordPasswordField.getText();
        pressButtonAction(2);
    }
}
