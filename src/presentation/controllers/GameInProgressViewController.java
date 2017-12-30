package presentation.controllers;

import enums.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import presentation.handlers.PinCircleOnMouseEnteredHandler;
import presentation.handlers.PinCircleOnMouseExitedHandler;
import util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameInProgressViewController extends RegisteringPresentationController
{
    /* ATTRIBUTES */

    @FXML private VBox boardVBox;
    @FXML private Button showCodeButton;

    private GridPane turnsGridPane;
    private GridPane userChoiceGridPane;
    private GridPane colorSelectionGridPane;

    private static final DataFormat STYLE_CLASS = new DataFormat("StyleClass");

    /* PRIVATE METHODS */

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

    /* RENDERING METHODS */

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

    private void printUserChoiceContainer()
    {
        int numPins = Constants.getNumPinsByDifficulty(boardDifficulty);
        List<Color> emptyCorrection = new ArrayList<>();

        for(int column = 0; column < numPins; ++column)
        {
            Circle pin = getNewPin(Color.NONE);
            pin.getStyleClass().add(StyleClass.COLOR_BLACK.toString());

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

            pin.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    pin.getStyleClass().setAll(StyleClass.COLOR_BLACK.toString());
                }
            });

            userChoiceGridPane.add(pin, column, 0);

            emptyCorrection.add(Color.HIDDEN);
        }

        GridPane correctionGridPane = getNewCorrectionGridPane(emptyCorrection);

        userChoiceGridPane.add(correctionGridPane, numPins, 0);
    }

    private void printColorSelectors(final  List<Color> colorList)
    {
        int column = 0;

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

    /* DRAG AND DROP METHODS */

    private void dragDetected(final MouseEvent event, final Circle pin)
    {
        Dragboard dragboard = pin.startDragAndDrop(TransferMode.COPY_OR_MOVE);

        ClipboardContent content = new ClipboardContent();

        ArrayList<String> styles = new ArrayList<>(pin.getStyleClass());

        content.put(STYLE_CLASS, styles);

        dragboard.setContent(content);
        event.consume();
    }

    private void dragOver(final DragEvent event, final Circle pin)
    {
        Dragboard dragboard = event.getDragboard();

        if(event.getGestureSource() != pin && dragboard.hasContent(STYLE_CLASS))
        {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
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

    /* GUI INTERACTION METHODS */

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

    public void printCorrectionColorSelectors()
    {
        List<Color> colorList = new ArrayList<>(Color.getCorrectionValues());

        printColorSelectors(colorList);
    }

    public void printCodeColorSelectors()
    {
        List<Color> colorList = new ArrayList<>(Color.getValues(boardDifficulty));

        printColorSelectors(colorList);
    }

    public void printBoard(final Difficulty difficulty)
    {
        boardDifficulty = difficulty;

        printAllTurns();
        printUserChoiceContainer();
        printCodeColorSelectors();
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
    public void showCodeButtonAction() throws IOException
    {

    }

    @FXML
    public void finishTurnButtonAction() throws IOException
    {
        int numPins = Constants.getNumPinsByDifficulty(boardDifficulty);
        currentTurn.clear();
        List<Node> nodeList = userChoiceGridPane.getChildren();

        for(int i = 0; i < numPins; ++i)
        {
            Node node = nodeList.get(i);
            Color color = Color.getColorByStyle(node.getStyleClass().get(0));
            currentTurn.add(color);
            node.getStyleClass().setAll(StyleClass.COLOR_BLACK.toString());
        }

        pressButtonAction(2);
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
