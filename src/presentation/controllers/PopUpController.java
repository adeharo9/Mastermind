package presentation.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public abstract class PopUpController extends PresentationController
{
    public void yesButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(1);
    }

    public void noButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }

}
