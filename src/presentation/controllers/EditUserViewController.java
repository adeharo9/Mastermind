package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class EditUserViewController extends PresentationController
{
    /* ATTRIBUTES */

    @FXML private Label usernameLabel;
    @FXML private PasswordField currentPasswordPasswordField;
    @FXML private PasswordField newPasswordPasswordField;
    @FXML private PasswordField confirmNewPasswordPasswordField;

    /* CONSTRUCTORS */

    public EditUserViewController()
    {

    }

    @Override
    public void showMessage(final String message)
    {
        usernameLabel.setText(message);
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
    public void updatePasswordButtonAction() throws IOException
    {
        pressButtonAction(2);
    }
}
