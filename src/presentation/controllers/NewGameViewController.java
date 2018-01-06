package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class NewGameViewController extends RegisteringPresentationController
{
    /* ATTRIBUTES */

    private boolean disabled = false;

    @FXML private VBox roleModeVBox;

    /* CONSTRUCTORS */

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

    @FXML
    public void playerVsCPURadioButtonAction()
    {
        disabled = false;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 2;
    }

    @FXML
    public void playerVsPlayerRadioButtonAction()
    {
        disabled = true;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 1;
    }

    @FXML
    public void cpuVsCPURadioButtonAction()
    {
        disabled = true;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 3;
    }

    @FXML
    public void playerVsCPURadioButtonOnMouseEntered()
    {
        if(disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    @FXML
    public void playerVsCPURadioButtonOnMouseExited()
    {
        if(disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    @FXML
    public void playerVsPlayerRadioButtonOnMouseEntered()
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    @FXML
    public void playerVsPlayerRadioButtonOnMouseExited()
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    @FXML
    public void cpuVsCPURadioButtonOnMouseEntered()
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    @FXML
    public void cpuVsCPURadioButtonOnMouseExited()
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    @FXML
    public void easyRadioButtonAction()
    {
        PresentationController.difficulty = 1;
    }

    @FXML
    public void mediumRadioButtonAction()
    {
        PresentationController.difficulty = 2;
    }

    @FXML
    public void hardRadioButtonAction()
    {
        PresentationController.difficulty = 3;
    }


    @FXML
    public void codeMakerRadioButtonAction()
    {
        PresentationController.role = 1;
    }

    @FXML
    public void codeBreakerRadioButtonAction()
    {
        PresentationController.role = 2;
    }
}
