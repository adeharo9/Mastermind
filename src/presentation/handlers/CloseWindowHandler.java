package presentation.handlers;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import presentation.controllers.PresentationController;

import java.io.IOException;

public class CloseWindowHandler implements EventHandler<WindowEvent>
{
    private PresentationController presentationController;

    public CloseWindowHandler(PresentationController presentationController)
    {
        this.presentationController = presentationController;
    }

    public void handle(WindowEvent windowEvent)
    {
        try
        {
            presentationController.pressButtonAction(0);
        }
        catch (IOException e)
        {

        }
    }
}
