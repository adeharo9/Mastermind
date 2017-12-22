package presentation.runnables;

import presentation.controllers.PresentationController;

public class ShowMessageRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final String message;

    public ShowMessageRunnable(PresentationController presentationController, final String message)
    {
        this.presentationController = presentationController;
        this.message = message;
    }

    @Override
    public void run()
    {
        presentationController.showMessage(message);
    }
}
