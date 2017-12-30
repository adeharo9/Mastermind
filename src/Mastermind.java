import domain.controllers.DomainController;
import javafx.application.Application;
import javafx.stage.Stage;
import presentation.controllers.PresentationController;
import presentation.controllers.RegisteringPresentationController;
import presentation.runnables.LogicThreadRunnable;

import java.io.IOException;

public class Mastermind extends Application
{
    private PresentationController presentationController;
    private DomainController domainController;

    @Override
    public void init() throws InterruptedException
    {
        presentationController = new RegisteringPresentationController();
        domainController = presentationController.getDomainController();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        presentationController.setMainStage(stage);
        presentationController.initView();

        Thread logicThread = new Thread(new LogicThreadRunnable(domainController));
        logicThread.start();
    }

    @Override
    public void stop()
    {
        System.exit(0);
    }

    public static void main(String args[])
    {
        launch(args);
    }
}
