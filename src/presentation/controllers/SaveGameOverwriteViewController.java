package presentation.controllers;

import javafx.fxml.FXML;

public class SaveGameOverwriteViewController extends PopUpController
{
    public SaveGameOverwriteViewController()
    {

    }

    /**
     * Método plantilla de pulsado de botón.
     *
     * Método plantilla que ejecuta su código cuando cualquier botón de la vista
     * del controlador se pulsa.
     */
    @Override
    protected void pressButtonTemplateAction()
    {
        popUpStage.close();
    }

    /* FXML */

    /**
     * Método de gestión de botón Overwrite.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Overwrite.
     */
    @FXML
    public void overwriteButtonAction()
    {
        super.yesButtonAction();
    }

    /**
     * Método de gestión de botón Cancel.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Cancel.
     */
    @FXML
    public void cancelButtonAction()
    {
        super.noButtonAction();
    }
}
