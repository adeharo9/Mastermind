package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LogOutWarningViewController extends PopUpController
{
    public LogOutWarningViewController()
    {

    }

    protected void pressButtonSpecificAction()
    {
        this.popUpStage.close();
    }

    /* FXML */

    @FXML
    public void logOutButtonAction(ActionEvent actionEvent) throws IOException
    {
        super.yesButtonAction(actionEvent);
    }

    @FXML
    public void cancelButtonAction(ActionEvent actionEvent) throws IOException
    {
        super.noButtonAction(actionEvent);
    }
}
