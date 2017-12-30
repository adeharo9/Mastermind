package presentation.controllers;

import domain.controllers.DomainController;
import enums.Color;
import enums.Difficulty;
import enums.View;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.Constants;
import util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class PresentationController
{
    /* ATTRIBUTES */

    private String currentViewFile;

    protected Stage mainStage;
    protected Stage popUpStage;

    protected static Stage currentStage;
    protected final static Stack<Stage> STAGE_STACK = new Stack<>();

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
    protected static List<Color> currentTurn = new ArrayList<>();

    /* PROTECTED METHODS */

    protected void endAction()
    {
        PresentationController.threadFinished = true;

        synchronized(DOMAIN_CONTROLLER)
        {
            DOMAIN_CONTROLLER.notify();
        }
    }

    protected void pressButtonTemplateAction()
    {

    }

    protected void pressButtonAction(final int value) throws IOException
    {
        PresentationController.returnState = value;
        pressButtonTemplateAction();
        endAction();
    }

    private void newStage()
    {
        STAGE_STACK.push(currentStage);
        currentStage = new Stage();
        currentStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent event)
            {
                currentStage = STAGE_STACK.pop();
            }
        });
    }

    protected void registerToDomainController()
    {
        DOMAIN_CONTROLLER.setPresentationController(this);
    }

    private void setPopUpStage(final Stage popUpStage)
    {
        this.popUpStage = popUpStage;
    }

    private void setCurrentViewFile(final String currentViewFile)
    {
        this.currentViewFile = currentViewFile;
    }

    /* PRIVATE METHODS */

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

    /* SET METHODS */

    public void setMainStage(final Stage mainStage)
    {
        this.mainStage = mainStage;
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

    public static List<Color> getCurrentTurn()
    {
        return PresentationController.currentTurn;
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

    public void newWindow(final String viewFile, final String windowTitle, final String iconPath, final Modality modality, final EventHandler<WindowEvent> closingEventHandler)
    {

    }

    public void popUpWindow(final String viewFile) throws IOException
    {
        popUpStage = new Stage();

        Parent root = loadView(viewFile);

        popUpStage.setTitle("Warning");
        popUpStage.getIcons().add(new Image(getClass().getResourceAsStream(Constants.RESOURCES_PATH + Constants.WARNING_ICON_FILE)));

        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setResizable(false);

        popUpStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent event)
            {
                try
                {
                    pressButtonAction(0);
                }
                catch (IOException e)
                {

                }
            }
        });

        popUpStage.setScene(new Scene(root, 250, 100));

        popUpStage.show();
    }

    /* TEMPLATE PATTERN */

    public void processInfo(final Object object)
    {

    }

    public void showLoadedGames(final List<String> savedGames)
    {

    }

    /* BOARD METHODS */

    public void setSolution(List<Color> solution)
    {
        PresentationController.solution = solution;
    }

    public void setCodes(List<List<Color>> codes)
    {
        PresentationController.codes = codes;
    }

    public void setCorrections(List<List<Color>> corrections)
    {
        PresentationController.corrections = corrections;
    }

    public void addCode(List<Color> code)
    {
        PresentationController.codes.add(code);
    }

    public void addCorrection(List<Color> correction)
    {
        PresentationController.corrections.add(correction);
    }

    public void clear()
    {
        PresentationController.solution.clear();
        PresentationController.codes.clear();
        PresentationController.corrections.clear();
    }

    public void printBoard(final Difficulty difficulty)
    {

    }

    public void printLastTurn()
    {

    }

    /* GUI ELEMENT METHODS */

    protected Circle getNewPin(final Color color)
    {
        Circle pin = new Circle();

        pin.setRadius(25);
        pin.getStyleClass().add(color.getCssStyleClass());

        return pin;
    }
}
