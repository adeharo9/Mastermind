package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuViewController extends RegisteringPresentationController
{
    /* CONSTRUCTORS */
    public MainMenuViewController()
    {

    }

    /* FXML */
    @FXML
    public void logOutButtonAction()
    {
        pressButtonAction(0);
    }

    @FXML
    public void newGameButtonAction()
    {
        pressButtonAction(1);
    }

    @FXML
    public void loadGameButtonAction()
    {
        pressButtonAction(2);
    }

    @FXML
    public void rankingButtonAction()
    {
        pressButtonAction(3);
    }

    @FXML
    public void infoButtonAction()
    {
        pressButtonAction(4);
    }

    @FXML
    public void editUserButtonAction()
    {
        pressButtonAction(5);
    }
}
