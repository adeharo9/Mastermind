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


public class InitSessionViewController extends AbstractViewController
{
    public InitSessionViewController()
    {

    }

    @Override
    @FXML
    protected void initialize()
    {

    }

    @FXML
    public void pressButton(ActionEvent actionEvent) throws IOException
    {
        updateView(View.LOADING_VIEW.getViewFile());
        returnState = 1;

        synchronized (domainController)
        {
            domainController.notify();
        }
    }
}
