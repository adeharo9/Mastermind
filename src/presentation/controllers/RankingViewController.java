package presentation.controllers;

import enums.StyleClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.Pair;

import java.io.IOException;
import java.util.List;

public class RankingViewController extends PresentationController
{
    /* ATTRIBUTES */

    @FXML private VBox rankingVBox;

    private HBox getNewRankingEntry(final String username, final Integer points)
    {
        HBox hBox = new HBox();

        Label usernameLabel = new Label(username);
        usernameLabel.getStyleClass().add(StyleClass.TEXT.toString());
        usernameLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

        Label pointsLabel = new Label(points.toString());
        pointsLabel.getStyleClass().add(StyleClass.TEXT.toString());
        pointsLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

        hBox.getChildren().add(usernameLabel);
        hBox.getChildren().add(pointsLabel);
        hBox.setAlignment(Pos.CENTER);

        hBox.setSpacing(50);

        return hBox;
    }

    /* CONSTRUCTORS */

    public RankingViewController()
    {

    }

    public void showRanking(List<Pair<String, Integer>> topTen)
    {
        for(Pair<String, Integer> entry : topTen)
        {
            HBox hBox = getNewRankingEntry(entry.first, entry.second);
            rankingVBox.getChildren().add(hBox);
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
