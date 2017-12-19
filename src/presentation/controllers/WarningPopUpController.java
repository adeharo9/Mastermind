package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WarningPopUpController extends PresentationController
{
    public WarningPopUpController()
    {

    }

    protected void pressButtonSpecificAction()
    {
        this.popUpStage.close();
    }

    /* FXML */

    @FXML
    public void yesButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void noButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }
}
