package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoadingViewController extends RegisteringPresentationController
{
    @FXML private Label errorLabel;

    public LoadingViewController()
    {

    }

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
