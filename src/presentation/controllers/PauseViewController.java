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
    public void continueButtonAction()
    {
        pressButtonAction(0);
    }

    @FXML
    public void saveButtonAction()
    {
        pressButtonAction(1);
    }

    @FXML
    public void mainMenuButtonAction()
    {
        pressButtonAction(2);
    }

}
