package presentation.controllers;

import domain.controllers.DomainController;
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
    private Stage stage;

    public PresentationController()
    {

    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public void initView() throws IOException, NullPointerException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.VIEWS_PATH + View.LOADING_VIEW.getViewFile()));

        Parent root = fxmlLoader.load();

        PresentationController presentationController = fxmlLoader.getController();
        presentationController.setStage(this.stage);

        this.stage.setTitle("Mastermind");
        this.stage.setScene(new Scene(root));
        this.stage.show();
    }

    public void updateView(final String viewFile) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.VIEWS_PATH + viewFile));

        Parent root = fxmlLoader.load();

        PresentationController presentationController = fxmlLoader.getController();
        presentationController.setStage(this.stage);

        stage.getScene().setRoot(root);
    }
}
