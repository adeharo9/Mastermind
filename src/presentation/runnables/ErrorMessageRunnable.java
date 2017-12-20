package presentation.runnables;

import presentation.controllers.PresentationController;

import java.io.IOException;

public class ErrorMessageRunnable implements Runnable
{

    private final PresentationController presentationController;

    public ErrorMessageRunnable(PresentationController presentationController)
    {
        this.presentationController = presentationController;
    }

    @Override
    public void run()
    {
        presentationController.errorMessage();
    }
}
