package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameOverViewController extends PopUpController
{
    /* ATTRIBUTES */

    @FXML private Label pointsLabel;

    /* CONSTRUCTORS */

    public GameOverViewController()
    {

    }

    protected void pressButtonTemplateAction()
    {
        popUpStage.close();
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
    public void mainMenuButtonAction()
    {
        pressButtonAction(0);
    }

    @FXML
    public void resetGameButtonAction()
    {
        pressButtonAction(1);
    }

    @FXML
    public void newGameButtonAction()
    {
        pressButtonAction(2);
    }
}
