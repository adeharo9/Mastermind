package presentation.runnables;

import presentation.controllers.PresentationController;

import java.io.IOException;

public class ErrorMessageRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final String message;

    public ErrorMessageRunnable(PresentationController presentationController, final String message)
    {
        this.presentationController = presentationController;
        this.message = message;
    }

    @Override
    public void run()
    {
        presentationController.errorMessage(message);
    }
}
