package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class ExitCurrentGameWarningViewController extends PopUpController
{
    public ExitCurrentGameWarningViewController()
    {

    }

    protected void pressButtonSpecificAction()
    {
        this.popUpStage.close();
    }

    /* FXML */

    @FXML
    public void exitGameButtonAction() throws IOException
    {
        super.yesButtonAction();
    }

    @FXML
    public void cancelButtonAction() throws IOException
    {
        super.noButtonAction();
    }
}
