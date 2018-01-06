package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;


public class InitSessionViewController extends RegisteringPresentationController
{
    /* CONSTRUCTORS */
    public InitSessionViewController()
    {

    }

    /* FXML */
    @FXML
    public void exitButtonAction()
    {
        pressButtonAction(0);
    }

    @FXML
    public void registerButtonAction()
    {
        pressButtonAction(1);
    }

    @FXML
    public void logInButtonAction()
    {
        pressButtonAction(2);
    }
}
