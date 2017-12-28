package presentation.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import presentation.controllers.GameInProgressViewController;
import presentation.controllers.PresentationController;

public class PinCircleOnMouseEnteredHandler implements EventHandler<MouseEvent>
{
    private GameInProgressViewController gameInProgressViewController;

    public PinCircleOnMouseEnteredHandler(final GameInProgressViewController gameInProgressViewController)
    {
        this.gameInProgressViewController = gameInProgressViewController;
    }

    public void handle(final MouseEvent mouseEvent)
    {
        gameInProgressViewController.pinCircleOnMouseEntered();
    }
}
