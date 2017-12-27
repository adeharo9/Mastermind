package enums;

public enum StyleClass
{
    BACKGROUND ("background"),
    BUTTON ("button"),
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
