package presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InitSessionViewController extends AbstractViewController
{
    public InitSessionViewController()
    {

    }

    @Override
    @FXML
    protected void initialize()
    {
    }

    @Override
    public void start() throws Exception
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/InitSessionView.fxml"));
        stage.setTitle("Mastermind");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }
}
