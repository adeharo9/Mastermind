package enums;

import javafx.scene.control.Control;
import javafx.stage.Modality;
import util.Constants;

/**
 * enum PopUpWindowStyle.
 *
 * Estilos de las vistas de popUp.
 *
 * @author Rafael Ramírez
 */

public enum PopUpWindowStyle
{
    GAME_OVER (300, 280, "Game Over", Constants.ICON_FILE, Modality.APPLICATION_MODAL),
    INFO (250, 100, "Mastermind", Constants.ICON_FILE, Modality.NONE),
    INFO_HARD (300, 100, "Mastermind", Constants.ICON_FILE, Modality.NONE),
    INTERACTION(250, 100, "Mastermind", Constants.ICON_FILE, Modality.APPLICATION_MODAL),
    WARNING (Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE, "Warning", Constants.WARNING_ICON_FILE, Modality.APPLICATION_MODAL);

    private final double width;
    private final double height;
    private final String title;
    private final String iconFile;
    private final Modality modality;

    /**
     * Constructora
     *
     * Crea un estilo de pop up segun
     * los parametros de entrada.
     *
     * @param width Ancho de la ventana
     * @param height Altura de la ventana.
     * @param title Nombre de la ventana.
     * @param iconFile Icono de la ventana.
     * @param modality Modalidad.
     */

    PopUpWindowStyle(final double width, final double height, final String title, final String iconFile, final Modality modality)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        this.iconFile = iconFile;
        this.modality = modality;
    }

    /**
     * Getter ancho.
     *
     * Devuelve el ancho de la ventana.
     *
     * @return Ancho.
     */

    public double getWidth()
    {
        return width;
    }

    /**
     * Getter altura.
     *
     * Devuelve la altura de la ventana.
     *
     * @return Altura.
     */

    public double getHeight()
    {
        return height;
    }

    /**
     * Getter Titulo.
     *
     * Devuelve el nombre de la ventana.
     *
     * @return Nombre de la ventana.
     */

    public String getTitle()
    {
        return title;
    }

    /**
     * Getter icono.
     *
     * Devuelve el icono de la ventana.
     *
     * @return Icono.
     */

    public String getIconFile()
    {
        return iconFile;
    }

    /**
     * Getter Modalidad.
     *
     * Devuelve la modalidad de la ventana.
     *
     * @return Modalidad.
     */

    public Modality getModality()
    {
        return modality;
    }
}
