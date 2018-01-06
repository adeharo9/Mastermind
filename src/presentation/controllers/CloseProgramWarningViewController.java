package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Controlador de vista de aviso de cerrar programa.
 *
 * Clase encargada de gestionar los distintos elementos de la vista de aviso de cerrar programa.
 *
 * @author Rafael Ramírez
 */

public class CloseProgramWarningViewController extends PopUpController
{
    /**
     * Constructora por defecto
     *
     * Constructora de instancia de controlador de vista de aviso de cerrar programa
     */
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
     * Método de gestión de botón Exit.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Exit.
     */
    @FXML
    public void exitButtonAction()
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
