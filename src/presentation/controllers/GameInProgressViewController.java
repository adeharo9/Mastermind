package presentation.controllers;

import enums.Color;
import enums.Difficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameInProgressViewController extends PresentationController
{
    /* ATTRIBUTES */

    @FXML private GridPane turnsGridPane;
    @FXML private GridPane colorSelectionGridPane;

    private Circle getNewPin(final Color color)
    {
        Circle pin = new Circle();

        pin.getStyleClass().add(color.getCssStyleClass());

        return pin;
    }

    /* CONSTRUCTORS */

    public GameInProgressViewController()
    {

    }

    public void printColorSelectors()
    {
        int column = 0;
        List<Color> colorList = new ArrayList<>(Color.getValues(Difficulty.EASY));

        for(final Color color : colorList)
        {
            Circle pin = getNewPin(color);

            colorSelectionGridPane.add(pin, column, 0);

            ++column;
        }
    }

    public void printBoard()
    {

    }

    /* FXML */

    @FXML
    public void initialize()
    {
        printColorSelectors();
    }

    @FXML
    public void pauseButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void helpButtonAction() throws IOException
    {
        pressButtonAction(1);
    }
}
