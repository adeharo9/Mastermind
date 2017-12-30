package presentation.controllers;

import java.io.IOException;

public abstract class PopUpController extends RegisteringPresentationController
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
