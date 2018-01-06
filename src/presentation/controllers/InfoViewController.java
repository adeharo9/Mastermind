package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoViewController extends RegisteringPresentationController
{
    @FXML private Label infoLabel;

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
