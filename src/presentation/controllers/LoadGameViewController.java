package presentation.controllers;

import enums.StyleClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class LoadGameViewController extends PresentationController
{
    @FXML private Label errorLabel;
    @FXML private ScrollPane contentScrollPane;
    @FXML private VBox savedGamesVBox;

    private GridPane getNewGridPane(final String gameId)
    {
        Label gameIdLabel = new Label(gameId);
        gameIdLabel.getStyleClass().add(StyleClass.TEXT.toString());
        GridPane.setHgrow(gameIdLabel, Priority.SOMETIMES);

        Button editButton = new Button("Edit");

        GridPane gridPane = new GridPane();
        GridPane.setHalignment(editButton, HPos.RIGHT);

        gridPane.add(gameIdLabel, 0, 0);
        gridPane.add(editButton, 1, 0);

        return gridPane;
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
        savedGamesVBox.setVisible(false);

        for(final String savedGame : savedGames)
        {
            GridPane gridPane = getNewGridPane(savedGame);
            savedGamesVBox.getChildren().add(gridPane);
        }

        contentScrollPane.setFitToHeight(true);
        contentScrollPane.setFitToWidth(true);

        savedGamesVBox.setVisible(true);
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

    @FXML
    public void editButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(1);
    }
}
