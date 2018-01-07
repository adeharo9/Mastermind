package presentation.controllers;

import javafx.fxml.FXML;

/**
 * @author Rafael Ramírez
 */

public class PauseViewController extends RegisteringPresentationController
{
    /* CONSTRUCTORS */

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de menú de pausa.
     */
    public PauseViewController()
    {

    }

    /* FXML */

    /**
     * Método de gestión de botón Continue.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Continue.
     */
    @FXML
    public void continueButtonAction()
    {
        pressButtonAction(0);
    }

    /**
     * Método de gestión de botón Save.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Save.
     */
    @FXML
    public void saveButtonAction()
    {
        pressButtonAction(1);
    }

    /**
     * Método de gestión de botón Main Menu.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Main Menu.
     */
    @FXML
    public void mainMenuButtonAction()
    {
        pressButtonAction(2);
    }

}
