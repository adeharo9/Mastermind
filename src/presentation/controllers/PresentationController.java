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

    private static final DomainController DOMAIN_CONTROLLER = new DomainController();

    private static volatile boolean threadFinished = false;

    private static volatile int returnState;
    protected static volatile int mode;
    protected static volatile int difficulty;
    protected static volatile int role;
    protected static volatile String gameId;
    protected static volatile String username;
    protected static volatile String password;

    /* PRIVATE METHODS */

    protected void endAction()
    {
        PresentationController.threadFinished = true;

        synchronized(DOMAIN_CONTROLLER)
        {
            DOMAIN_CONTROLLER.notify();
        }
    }

    protected void pressButtonAction(final int value) throws IOException
    {
        PresentationController.returnState = value;
        endAction();
    }

    /* CONSTRUCTORS */

    public PresentationController()
    {
        DOMAIN_CONTROLLER.setPresentationController(this);
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

    public static int getMode()
    {
        return PresentationController.mode;
    }

    public static int getDifficulty()
    {
        return PresentationController.difficulty;
    }

    public static int getRole()
    {
        return PresentationController.role;
    }

    public static String getUsername()
    {
        return PresentationController.username;
    }

    public static String getPassword()
    {
        return PresentationController.password;
    }

    public DomainController getDomainController()
    {
        return PresentationController.DOMAIN_CONTROLLER;
    }

    public boolean threadHasFinished()
    {
        return PresentationController.threadFinished;
    }

    /* GUI INTERACTION */

    public void initView() throws IOException, NullPointerException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.VIEWS_PATH + View.LOADING_VIEW.getViewFile()));

        Parent root = fxmlLoader.load();

        PresentationController presentationController = fxmlLoader.getController();
        presentationController.setStage(this.stage);

        this.stage.setTitle("Mastermind");
        this.stage.setScene(new Scene(root));
        //this.stage.setResizable(false);
        this.stage.show();
    }

    public void updateView(final String viewFile) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.VIEWS_PATH + viewFile));

        Parent root = fxmlLoader.load();

        PresentationController presentationController = fxmlLoader.getController();
        presentationController.setStage(this.stage);

        this.stage.getScene().setRoot(root);
    }
}
