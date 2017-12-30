package presentation.runnables;

import presentation.controllers.PresentationController;

public class ProcessInfoRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final Object info;

    public ProcessInfoRunnable(PresentationController presentationController, final Object info)
    {
        this.presentationController = presentationController;
        this.info = info;
    }

    @Override
    public void run()
    {
        presentationController.processInfo(info);
    }
}
