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

    protected static final DomainController domainController = new DomainController();

    protected static volatile boolean threadFinished = false;

    protected static volatile int returnState;
    protected static volatile String gameId;
    protected static volatile String username;
    protected static volatile String password;

    /* CONSTRUCTORS */

    public PresentationController()
    {
        domainController.setPresentationController(this);
    }

    /* SET METHODS */

    public void setStage(final Stage stage)
    {
        this.stage = stage;
    }

    public void clearThreadHasFinished()
    {
        PresentationController.threadFinished = false;
    }

    /* GET METHODS */

    public static int getReturnState()
    {
        return PresentationController.returnState;
    }

    public DomainController getDomainController()
    {
        return PresentationController.domainController;
    }

    public boolean threadHasFinished()
    {
        return PresentationController.threadFinished;
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
