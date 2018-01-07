package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controlador de vista de guardado de partida.
 *
 * Clase encargada de gestionar los distintos elementos de la vista de guardado de partida.
 *
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
