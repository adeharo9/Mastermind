package presentation.controllers;

import enums.StyleClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import util.Pair;

import java.io.IOException;
import java.util.List;

public class RankingViewController extends PresentationController
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

    public void showRanking(List<Pair<String, Integer>> topTen)
    {
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

    @FXML
    public void initialize()
    {
        endAction();
    }

    @FXML
    public void backButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }
}
