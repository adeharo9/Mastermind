package presentation.controllers;

import enums.StyleClass;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import util.Pair;
import util.UncheckedCast;

import java.util.List;

/**
 * Controlador de vista de ranking.
 *
 * Clase encargada de gestionar los distintos elementos de la vista de ranking.
 *
 * @author Alex Sánchez
 */

public class RankingViewController extends RegisteringPresentationController
{
    /* ATTRIBUTES */

    @FXML private GridPane rankingGridPane;

    /**
     * Método de generación de unidad de ranking.
     *
     * Método encargado de generar la estructura de visualización que contiene
     * todos los elementos (etiquetas, etc.) necesarios para la
     * correcta visualización de una unidad de ranking (una fila con el nombre de usuario
     * y los puntos conseguidos).
     *
     * @param username Nombre de usuario del usuario a mostrar en la entrada.
     * @param points Puntos del usuario a mostrar en la entrada.
     * @return Entrada de ranking correctamente configurada.
     */
    private List<Node> getNewRankingEntry(final String username, final Integer points)
    {
        //HBox hBox = new HBox();

        Label usernameLabel = new Label(username);
        usernameLabel.getStyleClass().add(StyleClass.TEXT.toString());
        usernameLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        GridPane.setHgrow(usernameLabel, Priority.SOMETIMES);

        Label pointsLabel = new Label(points.toString());
        pointsLabel.getStyleClass().add(StyleClass.TEXT.toString());
        pointsLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

        GridPane gridPane = new GridPane();
        GridPane.setHalignment(pointsLabel, HPos.RIGHT);

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(pointsLabel, 1, 0);

        return gridPane.getChildren();
    }

    /* CONSTRUCTORS */

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de ranking.
     */
    public RankingViewController()
    {

    }

    /**
     * Método de procesado de información proveniente del controlador de dominio.
     *
     * Método encargado de recibir información genérica proveniente del controlador
     * de dominio, concretamente de recibir el ranking de jugadores y mostrarlo adecuadamente.
     *
     * @param info Información recibida desde el controlador de dominio.
     */
    @Override
    public void processInfo(final Object info)
    {
        List<Pair<String, Integer>> topTen = UncheckedCast.cast(info);

        int row = 0;

        for(Pair<String, Integer> entry : topTen)
        {
            int column = 0;
            List<Node> gridPaneChildren = getNewRankingEntry(entry.first, entry.second);

            while(!gridPaneChildren.isEmpty())
            {
                rankingGridPane.add(gridPaneChildren.get(0), column, row);
                ++column;
            }

            ++row;
        }
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
}
