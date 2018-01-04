package presentation.controllers;

import enums.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML private VBox nonScrollableVBox;
    @FXML private Button helpButton;
    @FXML private Button showCodeButton;
    @FXML private Button finishTurnButton;
    @FXML private Label errorLabel;

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
            Circle pin = getNewPin(Color.HIDDEN);

            pin.setOnDragOver((DragEvent event) ->
                    dragOver(event, pin)
            );

            pin.setOnDragDropped( (DragEvent event) ->
                    dragDropped(event, pin)
            );

            pin.setOnMouseClicked((MouseEvent event) ->
                pin.getStyleClass().setAll(Color.HIDDEN.getCssStyleClass())
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

    public void processInfo(final Object info)
    {
        String message = (String) info;
        errorLabel.setText(message);
    }

    private void renderAllTurns()
    {
        for(int index = 0; index < codes.size(); ++index)
        {
            renderTurn(index);
        }
    }

    public  void renderLastTurn(final boolean renderFinishTurnButton)
    {
        if(!codes.isEmpty())
        {
            renderTurn(codes.size() - 1);
        }

        finishTurnButton.setVisible(renderFinishTurnButton);
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

    @Override
    public void renderBoard(final Difficulty difficulty)
    {
        boardDifficulty = difficulty;

        renderAllTurns();
        renderUserChoiceContainer();
    }

    @Override
    public void updateToCodeBreakerBoard()
    {
        renderCodeColorSelectors();

        helpButton.setVisible(true);
        showCodeButton.setVisible(false);
    }

    @Override
    public void updateToCodeCorrecterBoard()
    {
        renderCorrectionColorSelectors();

        helpButton.setVisible(false);
        showCodeButton.setVisible(true);
    }

    @Override
    public void updateToCodeMakerBoard()
    {
        renderCodeColorSelectors();

        helpButton.setVisible(false);
        showCodeButton.setVisible(false);
    }

    /* FXML */

    @Override
    @FXML
    public void initialize()
    {
        boardVBox.getChildren().add(turnsGridPane);

        nonScrollableVBox.getChildren().add(0, colorSelectionGridPane);
        nonScrollableVBox.getChildren().add(0, userChoiceGridPane);

        helpButton.setVisible(false);
        showCodeButton.setVisible(false);
        finishTurnButton.setVisible(true);

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
        if(getDifficulty() == 3) popUpWindow(PopUpWindowStyle.INFO_HARD, View.SHOW_CODE_VIEW.getViewFile());
        else popUpWindow(PopUpWindowStyle.INFO, View.SHOW_CODE_VIEW.getViewFile());
    }

    @FXML
    public void finishTurnButtonAction() throws IOException
    {
        errorLabel.setText("");

        int numPins = Constants.getNumPinsByDifficulty(boardDifficulty);
        currentTurn.clear();
        List<Node> nodeList = userChoiceGridPane.getChildren();

        for(int i = 0; i < numPins; ++i)
        {
            Node node = nodeList.get(i);

            if(node.getStyleClass().get(0).equals(StyleClass.COLOR_HIDDEN.toString()))
            {
                node.getStyleClass().setAll(StyleClass.COLOR_NONE.toString());
            }

            Color color = Color.getColorByStyle(node.getStyleClass().get(0));
            currentTurn.add(color);
            node.getStyleClass().setAll(StyleClass.COLOR_HIDDEN.toString());
        }

        pressButtonAction(2);
    }
}
