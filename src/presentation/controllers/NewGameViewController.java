package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NewGameViewController extends PresentationController
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
    public void backButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void startGameButtonAction() throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void playerVsCPURadioButtonAction() throws IOException
    {
        disabled = false;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 2;
    }

    @FXML
    public void playerVsPlayerRadioButtonAction() throws IOException
    {
        disabled = true;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 1;
    }

    @FXML
    public void cpuVsCPURadioButtonAction() throws IOException
    {
        disabled = true;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 3;
    }

    @FXML
    public void playerVsCPURadioButtonOnMouseEntered() throws IOException
    {
        if(disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    @FXML
    public void playerVsCPURadioButtonOnMouseExited() throws IOException
    {
        if(disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    @FXML
    public void playerVsPlayerRadioButtonOnMouseEntered() throws IOException
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    @FXML
    public void playerVsPlayerRadioButtonOnMouseExited() throws IOException
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    @FXML
    public void cpuVsCPURadioButtonOnMouseEntered() throws IOException
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    @FXML
    public void cpuVsCPURadioButtonOnMouseExited() throws IOException
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    @FXML
    public void easyRadioButtonAction() throws IOException
    {
        PresentationController.difficulty = 1;
    }

    @FXML
    public void mediumRadioButtonAction() throws IOException
    {
        PresentationController.difficulty = 2;
    }

    @FXML
    public void hardRadioButtonAction() throws IOException
    {
        PresentationController.difficulty = 3;
    }


    @FXML
    public void codeMakerRadioButtonAction() throws IOException
    {
        PresentationController.role = 1;
    }

    @FXML
    public void codeBreakerRadioButtonAction() throws IOException
    {
        PresentationController.role = 2;
    }
}
