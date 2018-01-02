package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class SaveGameOverwriteViewController extends PopUpController
{
    public SaveGameOverwriteViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        popUpStage.close();
    }

    /* FXML */

    @FXML
    public void overwriteButtonAction() throws IOException
    {
        super.yesButtonAction();
    }

    @FXML
    public void cancelButtonAction() throws IOException
    {
        super.noButtonAction();
    }
}
