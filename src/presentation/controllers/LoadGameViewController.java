package presentation.controllers;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.List;
import java.util.Set;

public class LoadGameViewController extends RegisteringPresentationController
{
    @FXML private Label errorLabel;
    @FXML private GridPane savedGamesGridPane;
    @FXML private Button loadGameButton;

    private List<Node> getNewGridPaneChildren(final String gameId)
    {
        Button gameIdButton = new Button(gameId);
        gameIdButton.setAlignment(Pos.CENTER_LEFT);
        gameIdButton.setMinSize(60, 25);
        gameIdButton.setPadding(new Insets(0, 15, 0, 15));
        gameIdButton.setOnAction((actionEvent) ->
                {
                    PresentationController.gameId = gameId;
                    loadGameButton.setVisible(true);
                }
        );
        GridPane.setHgrow(gameIdButton, Priority.ALWAYS);

        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("button");
        deleteButton.setOnAction((actionEvent) ->
            {
                PresentationController.gameId = gameId;
                pressButtonAction(2);
            }
        );

        GridPane gridPane = new GridPane();
        GridPane.setHalignment(deleteButton, HPos.RIGHT);

        gridPane.add(gameIdButton, 0, 0);
        gridPane.add(deleteButton, 1, 0);

        return gridPane.getChildren();
    }

    public LoadGameViewController()
    {

    }

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

        errorLabel.setVisible(true);
        errorLabel.setText(message);
    }

    /**
     * Método de procesado de lista de partidas guardadas proveniente del controlador de dominio.
     *
     * Método encargado de recibir el listado de partidas guardadas proveniente del controlador
     * de dominio y mostrarlas adecuadamente.
     *
     * @param savedGames Listado de partidas guardadas recibido desde el controlador de dominio.
     */
    @Override
    public void showLoadedGames(final Set<String> savedGames)
    {
        int row = 0;

        savedGamesGridPane.setVisible(false);
        savedGamesGridPane.getChildren().clear();

        for(final String savedGame : savedGames)
        {
            int column = 0;
            List<Node> gridPaneChildren = getNewGridPaneChildren(savedGame);

            while(!gridPaneChildren.isEmpty())
            {
                savedGamesGridPane.add(gridPaneChildren.get(0), column, row);
                ++column;
            }
            ++row;
        }

        savedGamesGridPane.setVisible(true);
    }

    /* FXML */

    /**
     * Método de captura de inicialización de JavaFX.
     *
     * Método a ejecutar cuando todos los campos fxml han sido construidos
     * e inicializados, implementado con la finalidad de inicializar los distintos
     * campos y no bloquear el controlador de dominio.
     */
    @FXML
    public void initialize()
    {
        loadGameButton.setVisible(false);
        endAction();
    }

    /**
     * Método de gestión de botón Back.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Back.
     */
    @FXML
    public void backButtonAction()
    {
        pressButtonAction(0);
    }

    /**
     * Método de gestión de botón Load Game.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Load Game.
     */
    @FXML
    public void loadGameButtonAction()
    {
        pressButtonAction(1);
    }

    /**
     * Método de gestión de botón Delete.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar el botón Delete.
     */
    @FXML
    public void deleteButtonAction()
    {
        pressButtonAction(2);
    }
}
