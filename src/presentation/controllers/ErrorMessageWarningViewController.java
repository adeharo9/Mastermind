package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Controlador de vista de aviso de mensaje de error.
 *
 * Clase encargada de gestionar los distintos elementos de la vista de mensaje de error.
 *
 * @author Alex Sánchez
 */

public class ErrorMessageWarningViewController extends PopUpController
{
    /**
     * Etiqueta de mensaje de error.
     */
    @FXML private Label errorMessageLabel;

    /**
     * Constructora por defecto.
     *
     * Constructora de instancia de controlador de vista de mensaje de error.
     */
    public ErrorMessageWarningViewController()
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
        errorMessageLabel.setText(message);
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
    @Override
    public void initialize()
    {
        endAction();
    }

    /**
     * Método de gestión de botón Accept.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Accept.
     */
    @FXML
    public void acceptButtonAction()
    {
        super.noButtonAction();
    }
}
