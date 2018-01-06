package presentation.controllers;

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
