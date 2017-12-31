package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class SaveGameViewController extends PopUpController {

    public SaveGameViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        this.popUpStage.close();
    }

    /* FXML */

    @FXML
    public void saveGameButtonAction() throws IOException
    {
        super.yesButtonAction();
    }

    @FXML
    public void backButtonAction() throws IOException
    {
        super.noButtonAction();
    }
}
