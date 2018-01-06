package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    @FXML
    public void initialize()
    {
        PresentationController.mode = 2;
        PresentationController.difficulty = 1;
        PresentationController.role = 2;
    }

    @FXML
    public void backButtonAction()
    {
        pressButtonAction(0);
    }

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
