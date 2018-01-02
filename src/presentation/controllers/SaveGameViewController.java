package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SaveGameViewController extends PopUpController {

    @FXML private TextField gameIdTextField;
    @FXML private Label errorLabel;

    public SaveGameViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        popUpStage.close();
    }

    public void processInfo(final Object info)
    {
        String message = (String) info;

        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    /* FXML */

    @FXML
    @Override
    public void initialize()
    {
        errorLabel.setVisible(false);
    }

    @FXML
    public void saveButtonAction() throws IOException
    {
        PresentationController.gameId = gameIdTextField.getText();
        super.yesButtonAction();
    }

    @FXML
    public void backButtonAction() throws IOException
    {
        super.noButtonAction();
    }
}
