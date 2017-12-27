package presentation.controllers;

import enums.Color;
import enums.Difficulty;
import enums.StyleClass;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
    private GridPane turnsGridPane;
    private GridPane userChoiceGridPane;
    private GridPane colorSelectionGridPane;

    private Circle getNewPin(final Color color)
    {
        Circle pin = new Circle();

        pin.setRadius(25);
        pin.getStyleClass().add(color.getCssStyleClass());

        return pin;
    }

    private GridPane getNewCorrectionGridPane(final List<Color> correction)
    {
        int column = 0;
        GridPane correctionGridPane = new GridPane();
        correctionGridPane.setHgap(2);

        for(final Color color : correction)
        {
            Circle pin = getNewPin(color);
            pin.setRadius(10);

            correctionGridPane.add(pin, column, 0);
            ++column;
        }

        return correctionGridPane;
    }

    private void printTurn(final int row)
    {
        int column = 0;

        for(final Color color : codes.get(row))
        {
            Circle pin = getNewPin(color);

            turnsGridPane.add(pin, column, row);
            ++column;
        }

        try
        {
            GridPane correctionGridPane = getNewCorrectionGridPane(corrections.get(row));

            turnsGridPane.add(correctionGridPane, column, row);
        }
        catch(IndexOutOfBoundsException iob)
        {

        }
    }

    /* CONSTRUCTORS */

    public GameInProgressViewController()
    {
        turnsGridPane = new GridPane();
        turnsGridPane.setHgap(10);
        turnsGridPane.setVgap(10);
        turnsGridPane.setAlignment(Pos.CENTER);

        userChoiceGridPane = new GridPane();
        userChoiceGridPane.setHgap(10);
        userChoiceGridPane.setAlignment(Pos.CENTER);

        colorSelectionGridPane = new GridPane();
        colorSelectionGridPane.setHgap(10);
        colorSelectionGridPane.setAlignment(Pos.CENTER);
    }

    public void printAllTurns()
    {
        for(int index = 0; index < codes.size(); ++index)
        {
            printTurn(index);
        }
    }

    public  void printLastTurn()
    {
        if(!codes.isEmpty())
        {
            printTurn(codes.size() - 1);
        }
    }

    public void printUserChoiceContainer()
    {
        int numPins = Constants.getNumPinsByDifficulty(boardDifficulty);
        List<Color> emptyCorrection = new ArrayList<>();

        for(int column = 0; column < numPins; ++column)
        {
            Circle pin = getNewPin(Color.NONE);
            pin.getStyleClass().add(StyleClass.CIRCLE.toString());

            userChoiceGridPane.add(pin, column, 0);

            emptyCorrection.add(Color.NONE);
        }

        GridPane correctionGridPane = getNewCorrectionGridPane(emptyCorrection);

        userChoiceGridPane.add(correctionGridPane, numPins, 0);
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

    public void printBoard(final Difficulty difficulty)
    {
        boardDifficulty = difficulty;

        printAllTurns();
        printUserChoiceContainer();
        printColorSelectors();
    }

    /* FXML */

    @FXML
    public void initialize()
    {
        boardVBox.getChildren().add(turnsGridPane);
        boardVBox.getChildren().add(userChoiceGridPane);
        boardVBox.getChildren().add(colorSelectionGridPane);

        endAction();
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
