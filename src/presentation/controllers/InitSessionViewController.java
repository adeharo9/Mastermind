package presentation.controllers;

import javafx.fxml.FXML;


public class InitSessionViewController extends RegisteringPresentationController
{
    /* CONSTRUCTORS */
    public InitSessionViewController()
    {

    }

    /**
     * Método de gestión de botón Exit.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Exit.
     */
    /* FXML */
    @FXML
    public void exitButtonAction()
    {
        pressButtonAction(0);
    }

    /**
     * Método de gestión de botón Register.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Register.
     */
    @FXML
    public void registerButtonAction()
    {
        pressButtonAction(1);
    }

    /**
     * Método de gestión de botón Log In.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Log In.
     */
    @FXML
    public void logInButtonAction()
    {
        pressButtonAction(2);
    }
}
