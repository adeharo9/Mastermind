package presentation.controllers;

import enums.Color;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameInProgressViewController extends PresentationController
{
    /* ATTRIBUTES */

    @FXML private VBox boardVBox;
    @FXML private GridPane turnsGridPane;
    @FXML private GridPane userChoiceGridPane;
    @FXML private GridPane colorSelectionGridPane;

    private Circle getNewPin(final Color color)
    {
        Circle pin = new Circle();

        pin.getStyleClass().add(color.getCssStyleClass());

        return pin;
    }

    private GridPane getNewCorrectionGridPane(final List<Color> correction)
    {
        GridPane correctionGridPane = new GridPane();

        return correctionGridPane;
    }

    /* CONSTRUCTORS */

    public GameInProgressViewController()
    {

    }

    public void printAllTurns()
    {
        int row = 0;

        for(final List<Color> code : codes)
        {
            int column = 0;

            for(final Color color : code)
            {
                Circle pin = getNewPin(color);

                turnsGridPane.add(pin, column, row);
                ++column;
            }

            GridPane correctionGridPane = getNewCorrectionGridPane(corrections.get(row));

            turnsGridPane.add(correctionGridPane, column, row);

            ++row;
        }
    }

    public void printUserChoiceContainer()
    {
        int numPins = Constants.getNumPinsByDifficulty(boardDifficulty);

        for(int column = 0; column < numPins; ++column)
        {
            Circle pin = getNewPin(Color.NONE);

            userChoiceGridPane.add(pin, column, 0);
        }
    }

    public void printColorSelectors()
    {
        int column = 0;
        List<Color> colorList = new ArrayList<>(Color.getValues(boardDifficulty));

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
