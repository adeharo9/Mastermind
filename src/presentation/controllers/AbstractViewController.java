package presentation.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.IOException;

public abstract class AbstractViewController extends PresentationController
{
    public AbstractViewController()
    {

    }

    @FXML
    protected abstract void initialize();
}
