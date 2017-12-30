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

    /*public void showClue(int type, String number, String color) throws IllegalArgumentException
    {
        switch(type)
        {
            case 1:
                hintLabel.setText("Token in position " + number + " is " + color + ".");
                break;
            case 2:
                hintLabel.setText("There is/are " + number + " " + color + " tokens" + ".");
                break;
            default:
                throw new IllegalArgumentException();
        }
    }*/

    protected void pressButtonTemplateAction()
    {
        this.popUpStage.close();
    }

    /* FXML */

    @FXML
    public void okButtonAction() throws IOException
    {

    }

}
