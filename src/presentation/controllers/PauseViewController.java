package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PauseViewController extends PresentationController
{
    /* CONSTRUCTORS */
    public PauseViewController()
    {

    }

    /* FXML */
    @FXML
    public void continueButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void saveButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void hintButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(2);
    }

    @FXML
    public void mainMenuButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(3);
    }

}
