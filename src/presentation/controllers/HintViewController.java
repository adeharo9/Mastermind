package presentation.controllers;

import enums.StyleClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import util.Constants;

import java.io.IOException;

public class HintViewController extends PopUpController
{
    @FXML private Label hintLabel;

    public HintViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        popUpStage.close();
    }

    public void processInfo(final Object info)
    {
        String message = (String) info;

        switch (message)
        {
            case Constants.GAME_NOT_STARTED_ERROR:
                hintLabel.getStyleClass().setAll(StyleClass.ERROR_LABEL.toString());
                break;
        }

        hintLabel.setText(message);
    }

    /* FXML */

    @Override
    @FXML
    public void initialize()
    {
        endAction();
    }

    @FXML
    public void okButtonAction() throws IOException
    {
        super.noButtonAction();
    }

}
