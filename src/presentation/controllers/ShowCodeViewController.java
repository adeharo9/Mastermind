package presentation.controllers;

import enums.Color;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author Alejandro de Haro
 */

public class ShowCodeViewController extends NonRegisteringPresentationController
{
    /* ATTRIBUTES */

    @FXML private HBox codeHBox;

    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador de vista de código de solución.
     */
    public ShowCodeViewController()
    {

    }

    /* FXML */

    /**
     * Método de captura de inicialización de JavaFX.
     *
     * Método a ejecutar cuando todos los campos fxml han sido construidos
     * e inicializados, implementado con la finalidad de inicializar los distintos
     * campos y no bloquear el controlador de dominio.
     */
    @FXML
    public void initialize()
    {
        int column = 0;

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        for(final Color color : solution)
        {
            Node pin = getNewPin(color);

            gridPane.add(pin, column, 0);

            ++column;
        }

        codeHBox.getChildren().setAll(gridPane);
    }
}
