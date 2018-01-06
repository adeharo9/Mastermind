package presentation.controllers;

import javafx.fxml.FXML;

/**
 * Controlador de vista de aviso de finalización de partida.
 *
 * Clase encargada de gestionar los distintos elementos de la vista de aviso de finalización de partida.
 *
 * @author Alejandro de Haro
 */

public class ExitCurrentGameWarningViewController extends PopUpController
{
    /**
     * Constructora por defecto.
     *
     * Constructora de instancia de controlador de vista de aviso de finalización de partida.
     */
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

    /**
     * Método de gestión de botón Exit.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Exit.
     */
    @FXML
    public void exitGameButtonAction()
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
