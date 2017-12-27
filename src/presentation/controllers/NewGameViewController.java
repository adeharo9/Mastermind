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
    public void backButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void startGameButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void playerVsCPURadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        disabled = false;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 2;
    }

    @FXML
    public void playerVsPlayerRadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        disabled = true;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 1;
    }

    @FXML
    public void cpuVsCPURadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        disabled = true;
        roleModeVBox.setDisable(disabled);
        PresentationController.mode = 3;
    }

    @FXML
    public void playerVsCPURadioButtonOnMouseEntered(MouseEvent actionEvent) throws IOException
    {
        if(disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    @FXML
    public void playerVsCPURadioButtonOnMouseExited(MouseEvent actionEvent) throws IOException
    {
        if(disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    @FXML
    public void playerVsPlayerRadioButtonOnMouseEntered(MouseEvent actionEvent) throws IOException
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    @FXML
    public void playerVsPlayerRadioButtonOnMouseExited(MouseEvent actionEvent) throws IOException
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    @FXML
    public void cpuVsCPURadioButtonOnMouseEntered(MouseEvent actionEvent) throws IOException
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(true);
        }
    }

    @FXML
    public void cpuVsCPURadioButtonOnMouseExited(MouseEvent actionEvent) throws IOException
    {
        if(!disabled)
        {
            roleModeVBox.setDisable(false);
        }
    }

    @FXML
    public void easyRadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        PresentationController.difficulty = 1;
    }

    @FXML
    public void mediumRadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        PresentationController.difficulty = 2;
    }

    @FXML
    public void hardRadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        PresentationController.difficulty = 3;
    }


    @FXML
    public void codeMakerRadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        PresentationController.role = 1;
    }

    @FXML
    public void codeBreakerRadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        PresentationController.role = 2;
    }
}
