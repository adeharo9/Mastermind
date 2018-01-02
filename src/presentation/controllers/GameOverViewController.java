package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameOverViewController extends RegisteringPresentationController
{
    /* ATTRIBUTES */

    @FXML private Label pointsLabel;

    /* CONSTRUCTORS */

    public GameOverViewController()
    {

    }

    /* GUI INTERACTION */

    @Override
    public void processInfo(final Object info)
    {
        String message = (String) info;

        pointsLabel.setText(message);
    }

    /* FXML */

    @FXML
    @Override
    public void initialize()
    {
        endAction();
    }

    @FXML
    public void mainMenuButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void resetGameButtonAction() throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void newGameButtonAction() throws IOException
    {
        pressButtonAction(2);
    }
}
