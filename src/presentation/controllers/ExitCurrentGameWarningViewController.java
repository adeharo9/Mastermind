package presentation.controllers;

import javafx.event.ActionEvent;
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
    public void exitGameButtonAction(ActionEvent actionEvent) throws IOException
    {
        super.yesButtonAction(actionEvent);
    }

    @FXML
    public void cancelButtonAction(ActionEvent actionEvent) throws IOException
    {
        super.noButtonAction(actionEvent);
    }
}
