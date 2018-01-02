package presentation.controllers;

import enums.Color;
import enums.Difficulty;
import enums.StyleClass;
import enums.View;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import util.Constants;
import util.UncheckedCast;

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

    private List<Color> getEmptyCorrection(int nElements)
    {
        List<Color> colorList = new ArrayList<>(nElements);

        for(int i = 0; i < nElements; ++i)
        {
            colorList.add(Color.NONE);
        }

        return colorList;
    }

    /* RENDERING METHODS */

    private void renderTurn(final int row)
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
            int numPins = Constants.getNumPinsByDifficulty(boardDifficulty);

            List<Color> emptyCorrection = getEmptyCorrection(numPins);
            GridPane correctionGridPane = getNewCorrectionGridPane(emptyCorrection);

            turnsGridPane.add(correctionGridPane, column, row);
        }
    }

    private void renderUserChoiceContainer()
    {
        int numPins = Constants.getNumPinsByDifficulty(boardDifficulty);

        for(int column = 0; column < numPins; ++column)
        {
            Circle pin = getNewPin(Color.NONE);

            pin.setOnDragOver((DragEvent event) ->
                    dragOver(event, pin)
            );

            pin.setOnDragDropped( (DragEvent event) ->
                    dragDropped(event, pin)
            );

            pin.setOnMouseClicked((MouseEvent event) ->
                pin.getStyleClass().setAll(StyleClass.COLOR_NONE.toString())
            );

            userChoiceGridPane.add(pin, column, 0);
        }

        List<Color> emptyCorrection = getEmptyCorrection(numPins);
        GridPane correctionGridPane = getNewCorrectionGridPane(emptyCorrection);

        userChoiceGridPane.add(correctionGridPane, numPins, 0);
    }

    private void renderColorSelectors(final List<Color> colorList)
    {
        int column = 0;

        colorSelectionGridPane.getChildren().clear();

        for(final Color color : colorList)
        {
            Circle pin = getNewPin(color);

            pin.setOnDragDetected((MouseEvent event) ->
                    dragDetected(event, pin)
            );

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

    private void dragDropped(final DragEvent event, final Circle pin)
    {
        boolean dragCompleted = false;

        Dragboard dragboard = event.getDragboard();

        if(dragboard.hasContent(STYLE_CLASS))
        {
            List<String> styles = UncheckedCast.cast(dragboard.getContent(STYLE_CLASS));
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

    private void renderAllTurns()
    {
        for(int index = 0; index < codes.size(); ++index)
        {
            renderTurn(index);
        }
    }

    public  void renderLastTurn()
    {
        if(!codes.isEmpty())
        {
            renderTurn(codes.size() - 1);
        }
    }

    private void renderCorrectionColorSelectors()
    {
        List<Color> colorList = new ArrayList<>(Color.getCorrectionValues());
        renderColorSelectors(colorList);
    }

    private void renderCodeColorSelectors()
    {
        List<Color> colorList = new ArrayList<>(Color.getValues(boardDifficulty));
        renderColorSelectors(colorList);
    }

    public void renderBoard(final Difficulty difficulty)
    {
        boardDifficulty = difficulty;

        renderAllTurns();
        renderUserChoiceContainer();
    }

    public void updateToCodeBreakerBoard()
    {
        renderCodeColorSelectors();

        showCodeButton.setVisible(false);
    }

    public void updateToCodeCorrecterBoard()
    {
        renderCorrectionColorSelectors();

        showCodeButton.setVisible(true);
    }

    public void updateToCodeMakerBoard()
    {
        renderCodeColorSelectors();

        showCodeButton.setVisible(false);
    }

    /* FXML */

    @FXML
    public void initialize()
    {
        boardVBox.getChildren().add(turnsGridPane);
        boardVBox.getChildren().add(userChoiceGridPane);
        boardVBox.getChildren().add(colorSelectionGridPane);

        showCodeButton.setVisible(false);

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
        popUpInfoWindow(View.SHOW_CODE_VIEW.getViewFile());
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
            node.getStyleClass().setAll(StyleClass.COLOR_NONE.toString());
        }

        pressButtonAction(2);
    }
}
