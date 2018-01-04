package enums;

import javafx.stage.Modality;
import util.Constants;

public enum PopUpWindowStyle
{
    GAME_OVER (250, 250, "Game Over", Constants.ICON_FILE, Modality.APPLICATION_MODAL),
    INFO (250, 100, "Mastermind", Constants.ICON_FILE, Modality.NONE),
    INFO_HARD (300, 100, "Mastermind", Constants.ICON_FILE, Modality.NONE),
    INTERACTION(250, 100, "Mastermind", Constants.ICON_FILE, Modality.APPLICATION_MODAL),
    WARNING (250, 100, "Warning", Constants.WARNING_ICON_FILE, Modality.APPLICATION_MODAL);

    private final int width;
    private final int height;
    private final String title;
    private final String iconFile;
    private final Modality modality;

    private PopUpWindowStyle(final int width, final int height, final String title, final String iconFile, final Modality modality)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        this.iconFile = iconFile;
        this.modality = modality;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public String getTitle()
    {
        return title;
    }

    public String getIconFile()
    {
        return iconFile;
    }

    public Modality getModality()
    {
        return modality;
    }
}
