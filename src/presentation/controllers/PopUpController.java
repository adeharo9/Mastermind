package presentation.controllers;

import java.io.IOException;

public abstract class PopUpController extends RegisteringPresentationController
{
    public void yesButtonAction()
    {
        pressButtonAction(1);
    }

    public void noButtonAction()
    {
        pressButtonAction(0);
    }

}
