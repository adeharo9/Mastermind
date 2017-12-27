package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;


public class InitSessionViewController extends PresentationController
{
    /* CONSTRUCTORS */
    public InitSessionViewController()
    {

    }

    /* FXML */
    @FXML
    public void exitButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void registerButtonAction() throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void logInButtonAction() throws IOException
    {
        pressButtonAction(2);
    }
}
