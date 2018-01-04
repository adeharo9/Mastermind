package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class CloseProgramWarningViewController extends PopUpController
{
    public CloseProgramWarningViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        popUpStage.close();
    }

    /* FXML */

    @FXML
    public void exitButtonAction() throws IOException
    {
        super.yesButtonAction();
    }

    @FXML
    public void cancelButtonAction() throws IOException
    {
        super.noButtonAction();
    }
}
