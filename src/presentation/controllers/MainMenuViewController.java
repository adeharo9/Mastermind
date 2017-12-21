package presentation.controllers;

import javafx.event.ActionEvent;
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
    public void logOutButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void newGameButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void loadGameButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(2);
    }

    @FXML
    public void rankingButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(3);
    }

    @FXML
    public void infoButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(4);
    }

    @FXML
    public void editUserButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(5);
    }
}
