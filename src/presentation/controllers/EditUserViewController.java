package presentation.controllers;

import javafx.event.ActionEvent;
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

    /* FXML */

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

    @FXML
    public void updatePasswordButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(2);
    }
}