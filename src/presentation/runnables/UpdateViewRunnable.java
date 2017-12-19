package presentation.runnables;

import presentation.controllers.PresentationController;

import java.io.IOException;

public class UpdateViewRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final String viewFile;

    public UpdateViewRunnable(PresentationController presentationController, String viewFile)
    {
        this.presentationController = presentationController;
        this.viewFile = viewFile;
    }

    @Override
    public void run()
    {
        try
        {
            presentationController.updateView(viewFile);
        }
        catch (IOException e)
        {

        }
    }
}
