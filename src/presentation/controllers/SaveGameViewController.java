package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SaveGameViewController extends PopUpController {

    @FXML private TextField gameIdTextField;

    public SaveGameViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        popUpStage.close();
    }

    /* FXML */

    @FXML
    @Override
    public void initialize()
    {
    }

    @FXML
    public void saveButtonAction()
    {
        PresentationController.gameId = gameIdTextField.getText();
        super.yesButtonAction();
    }

    @FXML
    public void backButtonAction()
    {
        super.noButtonAction();
    }
}
