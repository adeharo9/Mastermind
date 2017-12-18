package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainGameViewController extends PresentationController
{
    /* CONSTRUCTORS */
    public MainGameViewController()
    {

    }

    /* FXML */
    @FXML
    public void backButtonAction(ActionEvent actionEvent) throws IOException
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