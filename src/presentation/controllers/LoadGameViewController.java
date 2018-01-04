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

import java.io.IOException;
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
        deleteButton.setOnAction((actionEvent) ->
            {
                try
                {
                    PresentationController.gameId = gameId;
                    pressButtonAction(2);
                }
                catch (IOException ioe)
                {
                    throw new RuntimeException();
                }
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

    @Override
    public void processInfo(final Object info)
    {
        String message = (String) info;

        errorLabel.setVisible(true);
        errorLabel.setText(message);
    }

    @Override
    public void showLoadedGames(final Set<String> savedGames)
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
        loadGameButton.setVisible(false);
        endAction();
    }

    @FXML
    public void backButtonAction() throws IOException
    {
        pressButtonAction(0);
    }

    @FXML
    public void loadGameButtonAction() throws IOException
    {
        pressButtonAction(1);
    }

    @FXML
    public void deleteButtonAction() throws IOException
    {
        pressButtonAction(2);
    }
}
