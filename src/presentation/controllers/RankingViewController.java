package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class RankingViewController extends PresentationController
{
    /* ATTRIBUTES */

    @FXML private TableView rankingTableView;

    /* CONSTRUCTORS */

    public RankingViewController()
    {

    }

    public void setRanking()
    {

    }

    /* FXML */

    public void backButtonAction(ActionEvent actionEvent) throws IOException
    {
        pressButtonAction(0);
    }
}
