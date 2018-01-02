package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class GameOverViewController extends RegisteringPresentationController
{
    public GameOverViewController()
    {

    }

    /* FXML */

    @FXML
    public void mainMenuButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void resetGameButtonAction() throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void newGameButtonAction() throws IOException
    {
        pressButtonAction(2);
    }
}
