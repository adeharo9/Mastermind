package presentation.controllers;

import enums.Color;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ShowCodeViewController extends NonRegisteringPresentationController
{
    /* ATTRIBUTES */

    @FXML private HBox codeHBox;

    public ShowCodeViewController()
    {

    }

    /* FXML */

    @FXML
    public void initialize()
    {
        int column = 0;

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        for(final Color color : solution)
        {
            Node pin = getNewPin(color);

            gridPane.add(pin, column, 0);

            ++column;
        }

        codeHBox.getChildren().setAll(gridPane);
    }
}
