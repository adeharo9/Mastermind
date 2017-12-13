import domain.controllers.DomainController;
import enums.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.controllers.PresentationController;
import runnables.LogicThreadRunnable;
import util.Constants;

import java.io.IOException;

public class Mastermind extends Application
{
    private PresentationController presentationController;
    private DomainController domainController;

    @Override
    public void init() throws InterruptedException
    {
        presentationController = new PresentationController();
        domainController = new DomainController(presentationController);
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        presentationController.setStage(stage);
        presentationController.initView();

        Thread logicThread = new Thread(new LogicThreadRunnable(domainController));
        logicThread.start();
    }

    public static void main(String args[])
    {
        launch(args);
    }
}
