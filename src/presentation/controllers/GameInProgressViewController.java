package presentation.controllers;

import enums.Color;
import enums.Difficulty;
import enums.StyleClass;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import presentation.handlers.PinCircleOnMouseEnteredHandler;
import presentation.handlers.PinCircleOnMouseExitedHandler;
import util.Constants;
import util.ioUtils;

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

    private static final DataFormat STYLE_CLASS = new DataFormat("StyleClass");

    private Circle getNewPin(final Color color)
    {
        Circle pin = new Circle();

        pin.setRadius(25);
        pin.getStyleClass().add(StyleClass.CIRCLE.toString());
        pin.getStyleClass().add(color.getCssStyleClass());

        return pin;
    }

    private GridPane getNewCorrectionGridPane(final List<Color> correction)
    {
        int column = 0;
        GridPane correctionGridPane = new GridPane();
        correctionGridPane.setHgap(5);
        correctionGridPane.setAlignment(Pos.CENTER);

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

    private void dragDetected(final MouseEvent event, final Circle pin)
    {
        Dragboard dragboard = pin.startDragAndDrop(TransferMode.COPY_OR_MOVE);

        ClipboardContent content = new ClipboardContent();

        ArrayList<String> styles = new ArrayList<>(pin.getStyleClass());

        content.put(STYLE_CLASS, styles);

        dragboard.setContent(content);
        event.consume();
        ioUtils.printOutLn("Drag Detected");
    }

    private void dragOver(final DragEvent event, final Circle pin)
    {
        Dragboard dragboard = event.getDragboard();

        if(event.getGestureSource() != pin && dragboard.hasContent(STYLE_CLASS))
        {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
        ioUtils.printOutLn("Drag Over");
    }

    @SuppressWarnings("unchecked")
    private void dragDropped(final DragEvent event, final Circle pin)
    {
        boolean dragCompleted = false;

        Dragboard dragboard = event.getDragboard();

        if(dragboard.hasContent(STYLE_CLASS))
        {
            List<String> styles = (ArrayList<String>) dragboard.getContent(STYLE_CLASS);
            pin.getStyleClass().setAll(styles);
            dragCompleted = true;
        }

        event.setDropCompleted(dragCompleted);
        event.consume();
        ioUtils.printOutLn("Drag Dropped");
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
            pin.getStyleClass().add(StyleClass.EMPTY_CIRCLE.toString());

            pin.setOnDragOver(new EventHandler<DragEvent>()
            {
                @Override
                public void handle(DragEvent event)
                {
                    dragOver(event, pin);
                }
            });

            pin.setOnDragDropped(new EventHandler<DragEvent>()
            {
                @Override
                public void handle(DragEvent event)
                {
                    dragDropped(event, pin);
                }
            });

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
            pin.setOnMouseEntered(new PinCircleOnMouseEnteredHandler(this));
            pin.setOnMouseExited(new PinCircleOnMouseExitedHandler(this));

            pin.setOnDragDetected(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    dragDetected(event, pin);
                }
            });

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

    @FXML
    public void pinCircleOnMouseEntered()
    {
        turnsGridPane.setDisable(true);
    }

    @FXML
    public void pinCircleOnMouseExited()
    {
        turnsGridPane.setDisable(false);
    }
}
