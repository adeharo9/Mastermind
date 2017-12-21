package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.ArrayList;

public class GameInProgressViewController extends PresentationController
{
    /* ATTRIBUTES */

    @FXML private Circle bigPinTemplate;
    @FXML private Circle smallPinTemplate;
    @FXML private HBox rowTemplate;
    @FXML private VBox board;
    @FXML private Button pauseButton;
    @FXML private Button helpButton;

    private ArrayList<Circle> colorSelectors;

    /* CONSTRUCTORS */

    public GameInProgressViewController()
    {
        colorSelectors = new ArrayList<>();
    }

    public void printColorSelectors()
    {

    }

    public void printBoard()
    {

    }

    /* FXML */

    @FXML
    public void initialize()
    {
        colorSelectors.add(bigPinTemplate);
    }
}
