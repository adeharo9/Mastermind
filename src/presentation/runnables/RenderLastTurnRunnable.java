package presentation.runnables;

import presentation.controllers.PresentationController;

public class RenderLastTurnRunnable implements Runnable
{

    private final PresentationController presentationController;

    public RenderLastTurnRunnable(PresentationController presentationController)
    {
        this.presentationController = presentationController;
    }

    @Override
    public void run()
    {
        presentationController.renderLastTurn();
    }
}
