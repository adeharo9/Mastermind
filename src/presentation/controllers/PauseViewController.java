package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class PauseViewController extends RegisteringPresentationController
{
    /* CONSTRUCTORS */
    public PauseViewController()
    {

    }

    /* FXML */
    @FXML
    public void continueButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void saveButtonAction() throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void hintButtonAction() throws IOException
    {
        pressButtonAction(2);
    }

    @FXML
    public void mainMenuButtonAction() throws IOException
    {
        pressButtonAction(3);
    }

}
