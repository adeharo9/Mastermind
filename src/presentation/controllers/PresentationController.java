package presentation.controllers;

import enums.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Constants;

import java.io.IOException;

public class PresentationController
{
    /* ATTRIBUTES */
    private final Stage stage;

    public PresentationController(Stage stage) throws IOException
    {
        this.stage = stage;
        initView();
    }

    public void initView() throws IOException, NullPointerException
    {
        Parent root = FXMLLoader.load(getClass().getResource(Constants.VIEWS_PATH + View.LOADING_VIEW.getViewFile()));

        this.stage.setTitle("Mastermind");
        this.stage.setScene(new Scene(root));
        this.stage.show();
    }

    public void updateView(final String viewFile) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource(Constants.VIEWS_PATH + viewFile));

        stage.getScene().setRoot(root);
    }
}
