package presentation.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuViewController extends PresentationController
{
    /* CONSTRUCTORS */
    public MainMenuViewController()
    {

    }

    /* FXML */
    @FXML
    public void logOutButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void newGameButtonAction() throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void loadGameButtonAction() throws IOException
    {
        pressButtonAction(2);
    }

    @FXML
    public void rankingButtonAction() throws IOException
    {
        pressButtonAction(3);
    }

    @FXML
    public void infoButtonAction() throws IOException
    {
        pressButtonAction(4);
    }

    @FXML
    public void editUserButtonAction() throws IOException
    {
        pressButtonAction(5);
    }
}
