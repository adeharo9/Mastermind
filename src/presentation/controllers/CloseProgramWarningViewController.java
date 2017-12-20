package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CloseProgramWarningViewController extends PopUpController
{
    public CloseProgramWarningViewController()
    {

    }

    protected void pressButtonSpecificAction()
    {
        this.popUpStage.close();
    }

    /* FXML */

    @FXML
    public void exitButtonAction(ActionEvent actionEvent) throws IOException
    {
        super.yesButtonAction(actionEvent);
    }

    @FXML
    public void cancelButtonAction(ActionEvent actionEvent) throws IOException
    {
        super.noButtonAction(actionEvent);
    }
}
