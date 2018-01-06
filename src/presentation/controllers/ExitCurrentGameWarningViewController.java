package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class ExitCurrentGameWarningViewController extends PopUpController
{
    public ExitCurrentGameWarningViewController()
    {

    }

    /**
     * Método plantilla de pulsado de botón.
     *
     * Método plantilla que ejecuta su código cuando cualquier botón de la vista
     * del controlador se pulsa.
     */
    protected void pressButtonTemplateAction()
    {
        popUpStage.close();
    }

    /* FXML */

    @FXML
    public void exitGameButtonAction()
    {
        super.yesButtonAction();
    }

    @FXML
    public void cancelButtonAction()
    {
        super.noButtonAction();
    }
}
