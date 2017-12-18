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
    public void startGameButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void playerVsPlayerRadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        roleModeVBox.setDisable(false);
        PresentationController.mode = 1;
    }

    @FXML
    public void playerVsCPURadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        roleModeVBox.setDisable(false);
        PresentationController.mode = 2;
    }

    @FXML
    public void cpuVsCPURadioButtonAction(ActionEvent actionEvent) throws IOException
    {
        roleModeVBox.setDisable(true);
        PresentationController.mode = 3;
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
