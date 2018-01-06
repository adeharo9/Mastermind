package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class CloseProgramWarningViewController extends PopUpController
{
    public CloseProgramWarningViewController()
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

    /**
     *
     * @throws IOException
     */
    @FXML
    public void exitButtonAction()
    {
        super.yesButtonAction();
    }

    @FXML
    public void cancelButtonAction()
    {
        super.noButtonAction();
    }
}
