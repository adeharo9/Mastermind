package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * @author Alejandro de Haro
 */

public class LoadingViewController extends RegisteringPresentationController
{
    @FXML private Label errorLabel;

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de carga.
     */
    public LoadingViewController()
    {

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

        errorLabel.setVisible(true);
        errorLabel.setText(message);
    }

    /* FXML */

    /**
     * Método de captura de inicialización de JavaFX.
     *
     * Método a ejecutar cuando todos los campos fxml han sido construidos
     * e inicializados, implementado con la finalidad de no bloquear el controlador
     * de dominio.
     */
    @FXML
    public void initialize()
    {
        errorLabel.setVisible(false);
    }
}
