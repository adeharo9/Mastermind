package presentation.controllers;

import domain.controllers.DomainController;
import enums.*;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public abstract class PresentationController
{
    /* ATTRIBUTES */

    private String currentViewFile;

    protected static Stage mainStage;
    protected static Stage popUpStage;

    protected static Stage currentStage;
    protected final static Stack<Stage> NON_FOCUS_STAGE_STACK = new Stack<>();
    protected final static Stack<Stage> FOCUS_STAGE_STACK = new Stack<>();

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
    protected static volatile String currentPassword;

    protected static Difficulty boardDifficulty;
    protected static List<Color> solution = new ArrayList<>();
    protected static List<List<Color>> codes = new ArrayList<>();
    protected static List<List<Color>> corrections = new ArrayList<>();
    protected static List<Color> currentTurn = new ArrayList<>();

    private static volatile PlayingAction playingAction;

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
        FOCUS_STAGE_STACK.push(currentStage);
        currentStage = new Stage();
        currentStage.setOnCloseRequest((WindowEvent event) ->
                currentStage = FOCUS_STAGE_STACK.pop()
        );
    }

    protected void registerToDomainController()
    {
        DOMAIN_CONTROLLER.setPresentationController(this);
    }

    /* GUI ELEMENT METHODS */

    protected Circle getNewPin(final Color color)
    {
        Circle pin = new Circle();

        pin.setRadius(25);
        pin.getStyleClass().add(color.getCssStyleClass());

        return pin;
    }

    /* PRIVATE METHODS */

    private void setCurrentViewFile(final String currentViewFile)
    {
        this.currentViewFile = currentViewFile;
    }

    private Parent loadView(final String viewFile) throws IOException
    {
        this.currentViewFile = viewFile;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.VIEWS_PATH + viewFile));

        Parent root = fxmlLoader.load();

        PresentationController presentationController = fxmlLoader.getController();

        presentationController.setCurrentViewFile(this.currentViewFile);

        return root;
    }

    /* SET METHODS */

    public void setMainStage(final Stage mainStage)
    {
        PresentationController.mainStage = mainStage;
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

    public static String getCurrentPassword()
    {
        return PresentationController.currentPassword;
    }

    public static String getGameId()
    {
        return PresentationController.gameId;
    }

    public static List<Color> getCurrentTurn()
    {
        return PresentationController.currentTurn;
    }

    public DomainController getDomainController()
    {
        return PresentationController.DOMAIN_CONTROLLER;
    }

    public static boolean isColorSelector(final PlayingAction playingAction)
    {
        return PresentationController.playingAction == playingAction;
    }

    public static boolean threadHasFinished()
    {
        return PresentationController.threadFinished;
    }

    /* GUI INTERACTION */

    public void initialize()
    {

    }

    public void initView() throws IOException, NullPointerException
    {
        Parent root = loadView(View.LOADING_VIEW.getViewFile());

        mainStage.setTitle("Mastermind");
        mainStage.getIcons().add(new Image(getClass().getResourceAsStream(Constants.RESOURCES_PATH + Constants.ICON_FILE)));

        mainStage.setScene(new Scene(root));
        mainStage.setOnCloseRequest((WindowEvent event) ->
            {
                if(popUpStage != null)
                {
                    popUpStage.close();
                }
            }
        );

        mainStage.show();
    }

    public void updateView(final String viewFile) throws IOException
    {
        if(currentViewFile == null || !currentViewFile.equals(viewFile))
        {
            Parent root = loadView(viewFile);

            mainStage.getScene().setRoot(root);
        }
        else
        {
            initialize();
        }
    }

    private void newPopUpStage(final String viewFile, final String title, final String iconPath, final Modality modality, final EventHandler<WindowEvent> closingEventHandler) throws IOException
    {
        popUpStage = new Stage();

        Parent root = loadView(viewFile);

        popUpStage.setTitle(title);
        popUpStage.getIcons().add(new Image(getClass().getResourceAsStream(iconPath)));

        popUpStage.initModality(modality);
        popUpStage.setResizable(false);

        popUpStage.setOnCloseRequest(closingEventHandler);

        popUpStage.setScene(new Scene(root, 250, 125));

        popUpStage.show();
    }

    public void popUpWindow(final PopUpWindowStyle popUpWindowStyle, final String viewFile) throws IOException
    {
        String title = popUpWindowStyle.getTitle();
        String iconPath = Constants.RESOURCES_PATH + popUpWindowStyle.getIconFile();
        Modality modality = popUpWindowStyle.getModality();
        EventHandler<WindowEvent> closingEventHandler;

        switch (popUpWindowStyle)
        {
            case INFO:
                closingEventHandler = (WindowEvent event) -> {};
                break;
            case INTERACTION:
            case WARNING:
                closingEventHandler = (WindowEvent event) ->
                    {
                        try
                        {
                            pressButtonAction(0);
                        }
                        catch (IOException ioe)
                        {
                            throw new RuntimeException(ioe.getMessage());
                        }
                    };
                break;
            default:
                throw new IllegalArgumentException();
        }

        newPopUpStage(viewFile, title, iconPath, modality, closingEventHandler);
    }

    /* TEMPLATE PATTERN */

    public void processInfo(final Object object)
    {

    }

    public void showLoadedGames(final Set<String> savedGames)
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

    public void renderBoard(final Difficulty difficulty)
    {
        throw new RuntimeException("You should not be executing this method from here");
    }

    public void renderLastTurn(final boolean renderFinishTurnButton)
    {
        throw new RuntimeException("You should not be executing this method from here");
    }

    public void updateToCodeBreakerBoard()
    {
        throw new RuntimeException("You should not be executing this method from here");
    }

    public void updateToCodeCorrecterBoard()
    {
        throw new RuntimeException("You should not be executing this method from here");
    }

    public void updateToCodeMakerBoard()
    {
        throw new RuntimeException("You should not be executing this method from here");
    }
}
