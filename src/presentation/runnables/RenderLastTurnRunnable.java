package presentation.runnables;

import presentation.controllers.PresentationController;

public class RenderLastTurnRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final boolean renderFinishTurnButton;

    public RenderLastTurnRunnable(final PresentationController presentationController, final boolean renderFinishTurnButton)
    {
        this.presentationController = presentationController;
        this.renderFinishTurnButton = renderFinishTurnButton;
    }

    @Override
    public void run()
    {
        presentationController.renderLastTurn(renderFinishTurnButton);
    }
}
