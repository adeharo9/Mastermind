package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * Controlador de vista de partida nueva.
 *
 * Clase encargada de gestionar los distintos elementos de la vista de partida nueva.
 *
 * @author Rafael Ramírez
 */

public class NewGameViewController extends RegisteringPresentationController
{
    /* ATTRIBUTES */

    private boolean disabled = false;

    @FXML private VBox roleModeVBox;

    /* CONSTRUCTORS */

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de partida nueva.
     */
    public NewGameViewController()
    {

    }

    /* FXML */

    /**
     * Método de captura de inicialización de JavaFX.
     *
     * Método a ejecutar cuando todos los campos fxml han sido construidos
     * e inicializados, implementado con la finalidad de inicializar los distintos
     * campos.
     */
    @FXML
    public void initialize()
    {
        PresentationController.mode = 2;
        PresentationController.difficulty = 1;
        PresentationController.role = 2;
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

    /**
     * Método de gestión de botón Start Game.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Start Game.
     */
    @FXML
    public void startGameButtonAction()
    {
        pressButtonAction(1);
    }

    /**
     * Método de gestión de botón radial Player vs. CPU.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón radial Player vs. CPU.
     */
    @FXML
    public void playerVsCPURadioButtonAction()
    {
        disabled = false;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 2;
    }

    /**
     * Método de gestión de botón radial Player vs. Player.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón radial Player vs. Player.
     */
    @FXML
    public void playerVsPlayerRadioButtonAction()
    {
        disabled = true;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 1;
    }

    /**
     * Método de gestión de botón radial CPU vs. CPU.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón radial CPU vs. CPU.
     */
    @FXML
    public void cpuVsCPURadioButtonAction()
    {
        disabled = true;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 3;
    }

    /**
     * Método de gestión de botón radial Player vs. CPU.
     *
     * Método de gestión de las acciones a llevar a cabo al situar el mouse encima del botón radial Player vs. CPU.
     */
    @FXML
    public void playerVsCPURadioButtonOnMouseEntered()
    {
        if(disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    /**
     * Método de gestión de botón radial Player vs. CPU.
     *
     * Método de gestión de las acciones a llevar a cabo al sacar el mouse de encima del botón radial Player vs. CPU.
     */
    @FXML
    public void playerVsCPURadioButtonOnMouseExited()
    {
        if(disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    /**
     * Método de gestión de botón radial Player vs. Player.
     *
     * Método de gestión de las acciones a llevar a cabo al situar el mouse encima del botón radial Player vs. Player.
     */
    @FXML
    public void playerVsPlayerRadioButtonOnMouseEntered()
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    /**
     * Método de gestión de botón radial Player vs. Player.
     *
     * Método de gestión de las acciones a llevar a cabo al sacar el mouse de encima del botón radial Player vs. Player.
     */
    @FXML
    public void playerVsPlayerRadioButtonOnMouseExited()
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    /**
     * Método de gestión de botón radial CPU vs. CPU.
     *
     * Método de gestión de las acciones a llevar a cabo al situar el mouse encima del botón radial CPU vs. CPU.
     */
    @FXML
    public void cpuVsCPURadioButtonOnMouseEntered()
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    /**
     * Método de gestión de botón radial CPU vs. CPU.
     *
     * Método de gestión de las acciones a llevar a cabo al sacar el mouse de encima del botón radial CPU vs. CPU.
     */
    @FXML
    public void cpuVsCPURadioButtonOnMouseExited()
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    /**
     * Método de gestión de botón radial Easy.
     *
     * Método de gestión de las acciones a llevar a cabo al situar el mouse encima del botón radial Easy.
     */
    @FXML
    public void easyRadioButtonAction()
    {
        PresentationController.difficulty = 1;
    }

    /**
     * Método de gestión de botón radial Medium.
     *
     * Método de gestión de las acciones a llevar a cabo al situar el mouse encima del botón radial Medium.
     */
    @FXML
    public void mediumRadioButtonAction()
    {
        PresentationController.difficulty = 2;
    }

    /**
     * Método de gestión de botón radial Hard.
     *
     * Método de gestión de las acciones a llevar a cabo al situar el mouse encima del botón radial Hard.
     */
    @FXML
    public void hardRadioButtonAction()
    {
        PresentationController.difficulty = 3;
    }

    /**
     * Método de gestión de botón radial Code Maker.
     *
     * Método de gestión de las acciones a llevar a cabo al situar el mouse encima del botón radial Code Maker.
     */
    @FXML
    public void codeMakerRadioButtonAction()
    {
        PresentationController.role = 1;
    }

    /**
     * Método de gestión de botón radial Code Breaker.
     *
     * Método de gestión de las acciones a llevar a cabo al situar el mouse encima del botón radial Code Breaker.
     */
    @FXML
    public void codeBreakerRadioButtonAction()
    {
        PresentationController.role = 2;
    }
}
