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

public class RankingViewController extends RegisteringPresentationController
{
    /* ATTRIBUTES */

    @FXML private GridPane rankingGridPane;

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

    public RankingViewController()
    {

    }

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
