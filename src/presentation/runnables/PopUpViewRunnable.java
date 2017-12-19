package presentation.runnables;

import presentation.controllers.PresentationController;

import java.io.IOException;

public class PopUpViewRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final String viewFile;

    public PopUpViewRunnable(PresentationController presentationController, String viewFile)
    {
        this.presentationController = presentationController;
        this.viewFile = viewFile;
    }

    @Override
    public void run()
    {
        try
        {
            presentationController.popUpWindow(viewFile);
        }
        catch (IOException e)
        {

        }
    }
}
