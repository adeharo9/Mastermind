package enums;

import javafx.stage.Modality;
import util.Constants;

public enum PopUpWindowStyle
{
    INFO ("Mastermind", Constants.ICON_FILE, Modality.NONE),
    INTERACTION("Mastermind", Constants.ICON_FILE, Modality.APPLICATION_MODAL),
    WARNING ("Warning", Constants.WARNING_ICON_FILE, Modality.APPLICATION_MODAL);

    private final String title;
    private final String iconFile;
    private final Modality modality;

    private PopUpWindowStyle(final String title, final String iconFile, final Modality modality)
    {
        this.title = title;
        this.iconFile = iconFile;
        this.modality = modality;
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
