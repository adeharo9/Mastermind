package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ErrorMessageWarningViewController extends PopUpController
{
    @FXML private Label errorMessageLabel;

    public ErrorMessageWarningViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        popUpStage.close();
    }

    @Override
    public void processInfo(final Object info)
    {
        String message = (String) info;
        errorMessageLabel.setText(message);
    }

    /* FXML */

    @FXML
    @Override
    public void initialize()
    {
        endAction();
    }

    @FXML
    public void acceptButtonAction() throws IOException
    {
        super.noButtonAction();
    }
}
