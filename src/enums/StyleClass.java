package enums;

/**
 * enum StyleClass.
 *
 * Estilos para objetos de las vistas.
 *
 * @author Alejandro
 */

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
    ERROR_LABEL ("error-label"),
    RADIO_BUTTON ("radio-button"),
    SCROLL_BAR ("scroll-bar"),
    TEXT ("text"),
    TEXT_FIELD ("text-field"),
    TITLE ("title"),
    TOOLBAR ("toolbar");

    private final String stringId;

    /**
     * Creadora.
     *
     * Crea un StyleClass con el valor
     * del parametro de entrada.
     */

    StyleClass(final String stringId)
    {
        this.stringId = stringId;
    }

    /**
     * Getter id.
     *
     * Devuelve el stringId del StyleClass.
     *
     * @return Id.
     */

    @Override
    public String toString()
    {
        return stringId;
    }
}
