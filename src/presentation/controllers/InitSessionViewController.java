package presentation.controllers;

import enums.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Constants;

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
        pressButtonAction(View.LOADING_VIEW, 0);
    }

    @FXML
    public void registerButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(View.LOADING_VIEW, 1);
    }

    @FXML
    public void logInButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(View.LOADING_VIEW, 2);
    }
}
