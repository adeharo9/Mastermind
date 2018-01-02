package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import util.Constants;

import java.io.IOException;

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

    public EditUserViewController()
    {

    }

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
                newPasswordPasswordField.clear();
                confirmNewPasswordPasswordField.clear();
                currentPasswordPasswordField.clear();
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

    @FXML
    public void initialize()
    {
        endAction();
    }

    @FXML
    public void backButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void editButtonAction() throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void doneButtonAction() throws IOException
    {
        PresentationController.username = newUsernameTextField.getText();
        newUsernameTextField.setVisible(false);
        doneButton.setVisible(false);
        editButton.setVisible(true);
        endAction();
    }

    @FXML
    public void updatePasswordButtonAction() throws IOException
    {
        PresentationController.newPassword = newPasswordPasswordField.getText();
        PresentationController.confirmPassword = confirmNewPasswordPasswordField.getText();
        PresentationController.currentPassword = currentPasswordPasswordField.getText();
        pressButtonAction(2);
    }
}
