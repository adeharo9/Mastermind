package presentation.controllers;

import javafx.fxml.FXML;

/**
 * Controlador de vista de aviso de cerrado de sesión.
 *
 * Clase encargada de gestionar los distintos elementos de la vista de aviso de cerrado de sesión.
 *
 * @author Alejandro de Haro
 */

public class LogOutWarningViewController extends PopUpController
{
    /**
     * Constructora por defecto.
     *
     * Constructora de instancia de controlador de vista de aviso de cerrado de sesión.
     */
    public LogOutWarningViewController()
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
     * Método de gestión de botón Log Out.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Log Out.
     */
    @FXML
    public void logOutButtonAction()
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
