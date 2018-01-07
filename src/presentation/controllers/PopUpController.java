package presentation.controllers;

/**
 * @author Alejandro de Haro
 */

public abstract class PopUpController extends RegisteringPresentationController
{
    /**
     * Método de gestión de botón Yes.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Yes.
     */
    public void yesButtonAction()
    {
        pressButtonAction(1);
    }

    /**
     * Método de gestión de botón No.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón No.
     */
    public void noButtonAction()
    {
        pressButtonAction(0);
    }

}
