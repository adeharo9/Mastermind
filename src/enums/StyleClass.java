package enums;

public enum StyleClass
{
    BACKGROUND ("background"),
    BUTTON ("button"),
    COLOR_RED ("color-red"),
    COLOR_GREEN ("color-green"),
    COLOR_BLUE ("color-blue"),
    COLOR_ORANGE ("color-orange"),
    COLOR_PURPLE ("color-purple"),
    COLOR_YELLOW ("color-yellow"),
    COLOR_CYAN ("color-cyan"),
    COLOR_MAGENTA ("color-magenta"),
    COLOR_NONE ("color-none"),
    COLOR_BLACK ("color-black"),
    COLOR_WHITE ("color-white"),
    COLOR_HIDDEN ("color-hidden"),
    CIRCLE ("circle"),
    ERROR_LABEL ("error-label"),
    RADIO_BUTTON ("radio-button"),
    SCROLL_BAR ("scroll-bar"),
    TEXT ("text"),
    TEXT_FIELD ("text-field"),
    TITLE ("title"),
    TOOLBAR ("toolbar");

    private final String stringId;

    private StyleClass(final String stringId)
    {
        this.stringId = stringId;
    }

    @Override
    public String toString()
    {
        return stringId;
    }
}
