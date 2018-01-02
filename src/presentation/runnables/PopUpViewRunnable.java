package presentation.runnables;

import enums.PopUpWindowStyle;
import presentation.controllers.PresentationController;

import java.io.IOException;

public class PopUpViewRunnable implements Runnable
{
    private final PresentationController presentationController;
    private final PopUpWindowStyle popUpWindowStyle;
    private final String viewFile;

    public PopUpViewRunnable(final PresentationController presentationController, final PopUpWindowStyle popUpWindowStyle, final String viewFile)
    {
        this.presentationController = presentationController;
        this.popUpWindowStyle = popUpWindowStyle;
        this.viewFile = viewFile;
    }

    @Override
    public void run()
    {
        try
        {
            presentationController.popUpWindow(popUpWindowStyle, viewFile);
        }
        catch (IOException e)
        {
            throw new RuntimeException();
        }
    }
}
