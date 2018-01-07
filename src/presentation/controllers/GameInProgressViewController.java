package presentation.controllers;

import enums.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import util.Constants;
import util.UncheckedCast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de vista de tablero de juego.
 *
 * Clase encargada de gestionar los distintos elementos de la vista de tablero de juego.
 *
 * @author Alejandro de Haro
 */

public class GameInProgressViewController extends RegisteringPresentationController
{
    /* ATTRIBUTES */

    @FXML private VBox boardVBox;
    @FXML private VBox nonScrollableVBox;
    @FXML private Button helpButton;
    @FXML private Button showCodeButton;
    @FXML private Button finishTurnButton;
    @FXML private Label errorLabel;
    @FXML private ScrollPane turnsScrollPane;

    private GridPane turnsGridPane;
    private GridPane userChoiceGridPane;
    private GridPane colorSelectionGridPane;

    private static final DataFormat STYLE_CLASS = new DataFormat("StyleClass");

    /* PRIVATE METHODS */

    /**
     * Método de generación de unidad de tablero de corrección.
     *
     * Método encargado de generar un conjunto de pines contenedores de la solución
     * especificada por parámetro con las alineaciones y colores correctos.
     *
     * @param correction Corrección sobre la que construir la unidad de visualización de corrección.
     * @return Unidad de visualización correctamente inicializada.
     */
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

    /**
     * Método de generación de unidad corrección vacía.
     *
     * Método encargado de generar una unidad de visualización con nElements
     * pines vacíos.
     *
     * @param nElements Número de pines vacíos de la unidad.
     * @return Unidad de visualización correctamente inicializada.
     */
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

    /**
     * Método de renderizado de turno.
     *
     * Método encargado de generar y dar color a los pines correspondientes al turno
     * indicado por parámetro.
     *
     * @param row Número de turno a renderizar.
     */
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

    /**
     * Método de renderizado del contenedor de opción de usuario
     *
     * Método encargado de generar y visualizar correctamente los pines
     * que sirven de contenedor al usuario para indicar su jugada. Configura
     * las interacciones drag and drop.
     */
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

    /**
     * Renderizado de selectores de color.
     *
     * Genera y visualiza los selectores de color que el usuario utiliza para
     * realizar su jugada. Configura las interacciones drag and drop.
     *
     * @param colorList Lista de colores de los selectores.
     */
    private void renderColorSelectors(final List<Color> colorList)
    {
        int column = 0;

        colorSelectionGridPane.getChildren().clear();

        for(final Color color : colorList)
        {
            Circle pin = getNewPin(color);
            pin.setCursor(Cursor.OPEN_HAND);
            pin.setOnDragDetected((MouseEvent event) ->
                    dragDetected(event, pin)
            );

            colorSelectionGridPane.add(pin, column, 0);

            ++column;
        }
    }

    /* DRAG AND DROP METHODS */

    /**
     * Detección de drag.
     *
     * Inicia la acción de drag and drop y copia los datos de color del pin
     * indicado al dragboard de intercambio de información.
     *
     * @param event Evento de mouse que dispara el método.
     * @param pin Pin sobre el que se está efectuando drag.
     */
    private void dragDetected(final MouseEvent event, final Circle pin)
    {
        Dragboard dragboard = pin.startDragAndDrop(TransferMode.COPY_OR_MOVE);

        ClipboardContent content = new ClipboardContent();

        ArrayList<String> styles = new ArrayList<>(pin.getStyleClass());

        content.put(STYLE_CLASS, styles);

        dragboard.setContent(content);
        event.consume();
    }

    /**
     * Drag sobre objeto.
     *
     * Prepara el pin de destino para aceptar la información contenida en el dragboard
     * cuando el usuario haga drop.
     *
     * @param event Evento de mouse que dispara el método.
     * @param pin Pin sobre el que se efectuará drop.
     */
    private void dragOver(final DragEvent event, final Circle pin)
    {
        Dragboard dragboard = event.getDragboard();

        if(event.getGestureSource() != pin && dragboard.hasContent(STYLE_CLASS))
        {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    /**
     * Drop sobre objeto
     *
     * Copia la información necesaria del dragboard al pin de destino.
     *
     * @param event Evento de mouse que dispara el método.
     * @param pin Pin sobre el que se efectua drop.
     */
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

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de tablero de juego.
     */
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

    /**
     * Método de procesado de información proveniente del controlador de dominio.
     *
     * Método encargado de recibir información genérica proveniente del controlador
     * de dominio, concretamente de recibir mensajes de error y mostrarlos adecuadamente.
     *
     * @param info Información recibida desde el controlador de dominio.
     */
    @Override
    public void processInfo(final Object info)
    {
        String message = (String) info;
        errorLabel.setText(message);
    }

    /**
     * Renderizado de tablero
     *
     * Genera y visualiza un tablero con todos los turnos que haya
     * cargados en el controlador de presentación, previamente
     * introducidos por el controlador de dominio.
     */
    private void renderAllTurns()
    {
        for(int index = 0; index < codes.size(); ++index)
        {
            renderTurn(index);
        }
    }

    /**
     * Renderizado de último turno.
     *
     * Genera y visualiza el último turno introducido con la finalidad
     * de no tener que actualizar el tablero completo cada vez que se
     * juega un turno.
     *
     * @param renderFinishTurnButton Booleano de renderizado del botón de finalización
     *                               de turno.
     */
    public  void renderLastTurn(final boolean renderFinishTurnButton)
    {
        if(!codes.isEmpty())
        {
            renderTurn(codes.size() - 1);
        }

        finishTurnButton.setVisible(renderFinishTurnButton);
    }

    /**
     * Renderizado de selector de colores de corrección.
     *
     * Genera y visualiza los selectores de colores de corrección
     * de jugada.
     */
    private void renderCorrectionColorSelectors()
    {
        List<Color> colorList = new ArrayList<>(Color.getCorrectionValues());
        renderColorSelectors(colorList);
    }

    /**
     * Renderizado de selector de colores de jugada.
     *
     * Genera y visualiza los selectores de colores de jugada.
     */
    private void renderCodeColorSelectors()
    {
        List<Color> colorList = new ArrayList<>(Color.getValues(boardDifficulty));
        renderColorSelectors(colorList);
    }

    /**
     * Renderizado de tablero vacío.
     *
     * Genera y visualiza un tablero vacío sin los correspondientes
     * selectores de colores.
     *
     * @param difficulty Dificultad del tablero a visualizar.
     */
    @Override
    public void renderBoard(final Difficulty difficulty)
    {
        boardDifficulty = difficulty;

        renderAllTurns();
        renderUserChoiceContainer();
    }

    /**
     * Actualización de tablero a jugador code breaker.
     *
     * Adapta los selectores de color del tablero actual y los
     * botones de la vista a un jugador de tipo code breaker.
     */
    @Override
    public void updateToCodeBreakerBoard()
    {
        renderCodeColorSelectors();

        helpButton.setVisible(true);
        showCodeButton.setVisible(false);
    }

    /**
     * Actualización de tablero a jugador code maker en modo corrección.
     *
     * Adapta los selectores de color del tablero actual y los
     * botones de la vista a un jugador de tipo code maker en modo corrección
     * de jugada.
     */
    @Override
    public void updateToCodeCorrecterBoard()
    {
        renderCorrectionColorSelectors();

        helpButton.setVisible(false);
        showCodeButton.setVisible(true);
    }

    /**
     * Actualización de tablero a jugador code maker en modo generación.
     *
     * Adapta los selectores de color del tablero actual y los
     * botones de la vista a un jugador de tipo code maker en modo generación
     * de código de tablero.
     */
    @Override
    public void updateToCodeMakerBoard()
    {
        renderCodeColorSelectors();

        helpButton.setVisible(false);
        showCodeButton.setVisible(false);
    }

    /* FXML */

    /**
     * Método de captura de inicialización de JavaFX.
     *
     * Método a ejecutar cuando todos los campos fxml han sido construidos
     * e inicializados, implementado con la finalidad de inicializar los distintos
     * campos y no bloquear el controlador de dominio.
     */
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

        turnsScrollPane.vvalueProperty().bind(boardVBox.heightProperty());

        endAction();
    }

    /**
     * Método de gestión de botón Pause.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Pause.
     */
    @FXML
    public void pauseButtonAction()
    {
        pressButtonAction(0);
    }

    /**
     * Método de gestión de botón Help.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Help.
     */
    @FXML
    public void helpButtonAction()
    {
        pressButtonAction(1);
    }

    /**
     * Método de gestión de botón Show Code.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Show Code.
     * @throws IOException En caso que la vista definida en el estilo predefinido no pueda ser cargada
     * por algún motivo.
     */
    @FXML
    public void showCodeButtonAction() throws IOException
    {
        if(getDifficulty() == 3)
        {
            popUpWindow(PopUpWindowStyle.INFO_HARD, View.SHOW_CODE_VIEW.getViewFile());
        }
        else
        {
            popUpWindow(PopUpWindowStyle.INFO, View.SHOW_CODE_VIEW.getViewFile());
        }
    }

    /**
     * Método de gestión de botón Finish Turn.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Finish Turn.
     */
    @FXML
    public void finishTurnButtonAction()
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
