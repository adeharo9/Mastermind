package presentation.controllers;

import javafx.fxml.FXML;

import java.awt.event.ActionListener;

public abstract class AbstractViewController
{
    @FXML
    protected abstract void initialize();

    public abstract void start() throws Exception;
}
