package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Constants;


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
        Parent root = FXMLLoader.load(getClass().getResource(Constants.VIEWS_PATH + "InitSessionView.fxml"));
        stage.setTitle("Mastermind");
        stage.setScene(new Scene(root, 300, 275));
        stage.showAndWait();
    }

    @FXML
    public void pressButton(ActionEvent actionEvent)
    {
        System.out.println("Button pressed");
    }
}
