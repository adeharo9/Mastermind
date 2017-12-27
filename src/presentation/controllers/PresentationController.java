package presentation.controllers;

import domain.controllers.DomainController;
import enums.Color;
import enums.Difficulty;
import enums.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.handlers.CloseWindowHandler;
import util.Constants;
import util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PresentationController
{
    /* ATTRIBUTES */

    private String currentViewFile;

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
    protected static volatile String confirmPassword;
    protected static volatile String newPassword;

    protected static Difficulty boardDifficulty;
    protected static List<Color> solution = new ArrayList<>();
    protected static List<List<Color>> codes = new ArrayList<>();
    protected static List<List<Color>> corrections = new ArrayList<>();

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
        this.currentViewFile = viewFile;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.VIEWS_PATH + viewFile));

        Parent root = fxmlLoader.load();

        PresentationController presentationController = fxmlLoader.getController();
        presentationController.setMainStage(this.mainStage);
        presentationController.setPopUpStage(this.popUpStage);
        presentationController.setCurrentViewFile(this.currentViewFile);

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

    public void setCurrentViewFile(final String currentViewFile)
    {
        this.currentViewFile = currentViewFile;
    }

    public static void clearThreadHasFinished()
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

    public static String getConfirmPassword()
    {
        return PresentationController.confirmPassword;
    }

    public static String getNewPassword()
    {
        return PresentationController.newPassword;
    }

    public DomainController getDomainController()
    {
        return PresentationController.DOMAIN_CONTROLLER;
    }

    public static boolean threadHasFinished()
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
        if(currentViewFile == null || !currentViewFile.equals(viewFile))
        {
            Parent root = loadView(viewFile);

            this.mainStage.getScene().setRoot(root);
        }
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

    public void showMessage(final String message)
    {

    }

    public void showLoadedGames(final List<String> savedGames)
    {

    }

    public void showRanking(final List<Pair<String, Integer>> topTen)
    {

    }

    public void setSolution(List<Color> solution)
    {
        this.solution = solution;
    }

    public void setCodes(List<List<Color>> codes)
    {
        this.codes = codes;
    }

    public void setCorrections(List<List<Color>> corrections)
    {
        this.corrections = corrections;
    }

    public void addCode(List<Color> code)
    {
        this.codes.add(code);
    }

    public void addCorrection(List<Color> correction)
    {
        this.corrections.add(correction);
    }

    public void clear()
    {
        solution.clear();
        codes.clear();
        corrections.clear();
    }
}
