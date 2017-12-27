package enums;

public enum StyleClass
{
    BACKGROUND ("background"),
    BUTTON ("button"),
    ERROR_LABEL ("error-label"),
    RADIO_BUTTON ("radio-button"),
    TEXT ("text"),
    TEXT_FIELD ("text-field"),
    TITLE ("title"),
    TOOLBAR ("toolbar");

    private final String id;

    private StyleClass(final String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return id;
    }
}
