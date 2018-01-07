package presentation.controllers;

import javafx.fxml.FXML;

/**
 * Controlador de vista de menú principal.
 *
 * Clase encargada de gestionar los distintos elementos de la vista de menú principal.
 *
 * @author Alejandro de Haro
 */

public class MainMenuViewController extends RegisteringPresentationController
{
    /* CONSTRUCTORS */

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de menú principal.
     */
    public MainMenuViewController()
    {

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
        pressButtonAction(0);
    }

    /**
     * Método de gestión de botón New Game.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón New Game.
     */
    @FXML
    public void newGameButtonAction()
    {
        pressButtonAction(1);
    }

    /**
     * Método de gestión de botón Load Game.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Load Game.
     */
    @FXML
    public void loadGameButtonAction()
    {
        pressButtonAction(2);
    }

    /**
     * Método de gestión de botón Ranking.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Ranking.
     */
    @FXML
    public void rankingButtonAction()
    {
        pressButtonAction(3);
    }

    /**
     * Método de gestión de botón Info.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Info.
     */
    @FXML
    public void infoButtonAction()
    {
        pressButtonAction(4);
    }

    /**
     * Método de gestión de botón Edit User.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Edit User.
     */
    @FXML
    public void editUserButtonAction()
    {
        pressButtonAction(5);
    }
}
