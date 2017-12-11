import domain.controllers.DomainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.ioUtils;

public class Mastermind extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        DomainController domainController = new DomainController();
        domainController.exe();
    }

    public static void main(String args[]) throws Exception
    {
        launch(args);
    }
}
