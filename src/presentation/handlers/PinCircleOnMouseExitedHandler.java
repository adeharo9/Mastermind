package presentation.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import presentation.controllers.GameInProgressViewController;

public class PinCircleOnMouseExitedHandler implements EventHandler<MouseEvent>
{
    private GameInProgressViewController gameInProgressViewController;

    public PinCircleOnMouseExitedHandler(final GameInProgressViewController gameInProgressViewController)
    {
        this.gameInProgressViewController = gameInProgressViewController;
    }

    public void handle(final MouseEvent mouseEvent)
    {
        gameInProgressViewController.pinCircleOnMouseExited();
    }
}
