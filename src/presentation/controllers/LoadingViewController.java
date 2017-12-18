package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoadingViewController extends PresentationController
{
    public LoadingViewController()
    {

    }

    /* FXML */
    @FXML
    public void closeButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }
}
