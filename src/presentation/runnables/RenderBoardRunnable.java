package presentation.runnables;

import enums.Difficulty;
import presentation.controllers.PresentationController;

public class RenderBoardRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final Difficulty difficulty;

    public RenderBoardRunnable(final PresentationController presentationController, final Difficulty difficulty)
    {
        this.presentationController = presentationController;
        this.difficulty = difficulty;
    }

    @Override
    public void run()
    {
        presentationController.renderBoard(difficulty);
    }
}
