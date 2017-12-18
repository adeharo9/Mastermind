package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GameSelectionViewController extends PresentationController
{
    /* ATTRIBUTES */

    @FXML private VBox roleModeVBox;

    /* CONSTRUCTORS */
    public GameSelectionViewController()
    {

    }

    /* FXML */

    @FXML
    public void backButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void playerVsPlayerRadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        roleModeVBox.setDisable(false);
    }

    @FXML
    public void playerVsCPURadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        roleModeVBox.setDisable(false);
    }

    @FXML
    public void cpuVsCPURadioButtonAction(ActionEvent actionEvent) throws IOException
    {

    }


    @FXML
    public void easyRadioButtonAction(ActionEvent actionEvent) throws IOException
    {

    }

    @FXML
    public void mediumRadioButtonAction(ActionEvent actionEvent) throws IOException
    {

    }

    @FXML
    public void hardRadioButtonAction(ActionEvent actionEvent) throws IOException
    {

    }


    @FXML
    public void codeMakerRadioButtonAction(ActionEvent actionEvent) throws IOException
    {

    }

    @FXML
    public void codeBreakerRadioButtonAction(ActionEvent actionEvent) throws IOException
    {

    }
}
