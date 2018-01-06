package presentation.controllers;

import enums.StyleClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.Constants;

public class HintViewController extends PopUpController
{
    @FXML private Label hintLabel;

    public HintViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        popUpStage.close();
    }

    /**
     * Método de procesado de información proveniente del controlador de dominio.
     *
     * Método encargado de recibir información genérica proveniente del controlador
     * de dominio, concretamente de recibir el mensaje de pista y mostrarlo adecuadamente.
     *
     * @param info Información recibida desde el controlador de dominio.
     */
    @Override
    public void processInfo(final Object info)
    {
        String message = (String) info;

        switch (message)
        {
            case Constants.GAME_NOT_STARTED_ERROR:
                hintLabel.getStyleClass().setAll(StyleClass.ERROR_LABEL.toString());
                break;
        }

        hintLabel.setText(message);
    }

    /* FXML */

    /**
     * Método de captura de inicialización de JavaFX.
     *
     * Método a ejecutar cuando todos los campos fxml han sido construidos
     * e inicializados, implementado con la finalidad de no bloquear el controlador
     * de dominio.
     */
    @Override
    @FXML
    public void initialize()
    {
        endAction();
    }

    /**
     * Método de gestión de botón OK.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón OK.
     */
    @FXML
    public void okButtonAction()
    {
        super.noButtonAction();
    }

}
