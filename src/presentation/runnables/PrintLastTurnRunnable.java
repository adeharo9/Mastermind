package presentation.runnables;

import presentation.controllers.PresentationController;

public class PrintLastTurnRunnable implements Runnable
{

    private final PresentationController presentationController;

    public PrintLastTurnRunnable(PresentationController presentationController)
    {
        this.presentationController = presentationController;
    }

    @Override
    public void run()
    {
        presentationController.renderLastTurn();
    }
}
