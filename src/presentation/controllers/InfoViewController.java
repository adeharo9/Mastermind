package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlador de vista de información.
 *
 * Clase encargada de gestionar los distintos elementos de la vista de información.
 *
 * @author Rafael Ramírez
 */

public class InfoViewController extends RegisteringPresentationController
{
    @FXML private Label infoLabel;

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de información.
     */
    public InfoViewController()
    {

    }

    /**
     * Método de captura de inicialización de JavaFX.
     *
     * Método a ejecutar cuando todos los campos fxml han sido construidos
     * e inicializados, implementado con la finalidad de inicializar los distintos
     * campos y no bloquear el controlador de dominio.
     */
    @FXML
    public void initialize()
    {
        endAction();
    }

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

        infoLabel.setText(message);
    }

    /**
     * Método de gestión de botón Back.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Back.
     */
    @FXML
    public void backButtonAction()
    {
        pressButtonAction(0);
    }
}
