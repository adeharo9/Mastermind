package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverViewController extends PopUpController
{
    /* ATTRIBUTES */

    @FXML private Label pointsLabel;

    /* CONSTRUCTORS */

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de juego finalizado.
     */
    public GameOverViewController()
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

    /* GUI INTERACTION */

    /**
     * Método de procesado de información proveniente del controlador de dominio.
     *
     * Método encargado de recibir información genérica proveniente del controlador
     * de dominio, concretamente de recibir mensajes de error y mostrarlos adecuadamente.
     *
     * @param info Información recibida desde el controlador de dominio.
     */
    @Override
    public void processInfo(final Object info)
    {
        String message = (String) info;

        pointsLabel.setText(message);
    }

    /* FXML */

    /**
     * Método de captura de inicialización de JavaFX.
     *
     * Método a ejecutar cuando todos los campos fxml han sido construidos
     * e inicializados, implementado con la finalidad de inicializar los distintos
     * campos y no bloquear el controlador de dominio.
     */
    @FXML
    @Override
    public void initialize()
    {
        endAction();
    }

    /**
     * Método de gestión de botón Main Menu.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Main Menu.
     */
    @FXML
    public void mainMenuButtonAction()
    {
        pressButtonAction(0);
    }

    /**
     * Método de gestión de botón Reset Game.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Reset Game.
     */
    @FXML
    public void resetGameButtonAction()
    {
        pressButtonAction(1);
    }

    /**
     * Método de gestión de botón New Game.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón New Game.
     */
    @FXML
    public void newGameButtonAction()
    {
        pressButtonAction(2);
    }
}
