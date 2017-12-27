package presentation.runnables;

import enums.Difficulty;
import presentation.controllers.PresentationController;

public class PrintBoardRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final Difficulty difficulty;

    public PrintBoardRunnable(final PresentationController presentationController, final Difficulty difficulty)
    {
        this.presentationController = presentationController;
        this.difficulty = difficulty;
    }

    @Override
    public void run()
    {
        presentationController.printBoard(difficulty);
    }
}
