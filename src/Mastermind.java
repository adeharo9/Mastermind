import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Mastermind extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("presentation/views/InitSessionView.fxml"));
        primaryStage.setTitle("Mastermind");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String args[]) throws Exception
    {
        launch(args);
        /*DomainController domainController = new DomainController();
        domainController.exe();*/
    }
}
