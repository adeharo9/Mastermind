package presentation.controllers;

import domain.controllers.DomainController;
import enums.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.handlers.CloseWindowHandler;
import util.Constants;

import java.io.IOException;

public class PresentationController
{
    /* ATTRIBUTES */

    protected Stage mainStage;
    protected Stage popUpStage;

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

    protected void pressButtonSpecificAction()
    {

    }

    public void pressButtonAction(final int value) throws IOException
    {
        PresentationController.returnState = value;
        pressButtonSpecificAction();
        endAction();
    }

    private Parent loadView(final String viewFile) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.VIEWS_PATH + viewFile));

        Parent root = fxmlLoader.load();

        PresentationController presentationController = fxmlLoader.getController();
        presentationController.setMainStage(this.mainStage);
        presentationController.setPopUpStage(this.popUpStage);

        return root;
    }

    /* CONSTRUCTORS */

    public PresentationController()
    {
        DOMAIN_CONTROLLER.setPresentationController(this);
    }

    /* SET METHODS */

    public void setMainStage(final Stage mainStage)
    {
        this.mainStage = mainStage;
    }

    public void setPopUpStage(final Stage popUpStage)
    {
        this.popUpStage = popUpStage;
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
        Parent root = loadView(View.LOADING_VIEW.getViewFile());

        this.mainStage.setTitle("Mastermind");
        this.mainStage.getIcons().add(new Image(getClass().getResourceAsStream(Constants.RESOURCES_PATH + Constants.ICON_FILE)));

        this.mainStage.setScene(new Scene(root));
        //this.mainStage.setResizable(false);

        this.mainStage.show();
    }

    public void updateView(final String viewFile) throws IOException
    {
        Parent root = loadView(viewFile);

        this.mainStage.getScene().setRoot(root);
    }

    public void popUpWindow(final String viewFile) throws IOException
    {
        popUpStage = new Stage();

        Parent root = loadView(viewFile);

        popUpStage.setTitle("Warning");
        popUpStage.getIcons().add(new Image(getClass().getResourceAsStream(Constants.RESOURCES_PATH + Constants.WARNING_ICON_FILE)));

        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setResizable(false);
        popUpStage.setOnCloseRequest(new CloseWindowHandler(this));

        popUpStage.setScene(new Scene(root, 250, 100));

        popUpStage.show();
    }

    public void errorMessage(){}
}
