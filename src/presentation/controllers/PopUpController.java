package presentation.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public abstract class PopUpController extends PresentationController
{
    public void yesButtonAction() throws IOException
    {
        pressButtonAction(1);
    }

    public void noButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

}
