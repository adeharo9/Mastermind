package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Alex Sánchez
 */

public class SaveGameViewController extends PopUpController {

    @FXML private TextField gameIdTextField;

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de guardado de partida.
     */
    public SaveGameViewController()
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
    }

    /**
     * Método de gestión de botón Save.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Save.
     */
    @FXML
    public void saveButtonAction()
    {
        PresentationController.gameId = gameIdTextField.getText();
        super.yesButtonAction();
    }

    /**
     * Método de gestión de botón Back.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Back.
     */
    @FXML
    public void backButtonAction()
    {
        super.noButtonAction();
    }
}
