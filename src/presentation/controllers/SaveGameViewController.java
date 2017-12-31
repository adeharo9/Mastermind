package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SaveGameViewController extends PopUpController {

    @FXML private TextField gameIdTextField;

    public SaveGameViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        this.popUpStage.close();
    }

    /* FXML */

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
