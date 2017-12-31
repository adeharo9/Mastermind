package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HintViewController extends PopUpController
{
    @FXML private Label hintLabel;
    @FXML private Button okButton;

    public HintViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        this.popUpStage.close();
    }

    /* FXML */

    @FXML
    public void okButtonAction() throws IOException
    {
        super.noButtonAction();
    }

}
