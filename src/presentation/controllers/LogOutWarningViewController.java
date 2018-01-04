package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class LogOutWarningViewController extends PopUpController
{
    public LogOutWarningViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        popUpStage.close();
    }

    /* FXML */

    @FXML
    public void logOutButtonAction() throws IOException
    {
        super.yesButtonAction();
    }

    @FXML
    public void cancelButtonAction() throws IOException
    {
        super.noButtonAction();
    }
}
