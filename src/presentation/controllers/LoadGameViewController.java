package presentation.controllers;

import enums.StyleClass;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.util.List;

public class LoadGameViewController extends PresentationController
{
    @FXML private Label errorLabel;
    @FXML private GridPane savedGamesGridPane;

    private List<Node> getNewGridPaneChildren(final String gameId)
    {
        Label gameIdLabel = new Label(gameId);
        gameIdLabel.getStyleClass().add(StyleClass.TEXT.toString());
        GridPane.setHgrow(gameIdLabel, Priority.SOMETIMES);

        Button editButton = new Button("Edit");

        GridPane gridPane = new GridPane();
        GridPane.setHalignment(editButton, HPos.RIGHT);

        gridPane.add(gameIdLabel, 0, 0);
        gridPane.add(editButton, 1, 0);

        return gridPane.getChildren();
    }

    public LoadGameViewController()
    {

    }

    public void showMessage(String message)
    {
        errorLabel.setVisible(true);
        errorLabel.setText(message);
    }

    public void showLoadedGames(final List<String> savedGames)
    {
        int row = 0;

        savedGamesGridPane.setVisible(false);

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

    @FXML
    public void initialize()
    {

        endAction();
    }

    @FXML
    public void backButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void editButtonAction() throws IOException
    {
        pressButtonAction(1);
    }
}
