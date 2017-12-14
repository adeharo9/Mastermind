package presentation.controllers;

import enums.View;
import javafx.event.ActionEvent;
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
    public void closeButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void registerButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void logInButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(2);
    }
}
