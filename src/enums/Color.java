package enums;

import util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * enum Color.
 *
 * Colores que pueden tener las fichas de los codigos
 *
 * @author Alejandro
 */

public enum Color
{
    RED(0, "R", "Red", "color-red"),
    GREEN(RED.getId() + 1, "G", "Green", "color-green"),
    BLUE(GREEN.getId() + 1, "B", "Blue", "color-blue"),
    ORANGE(BLUE.getId() + 1, "O", "Orange", "color-orange"),
    PURPLE(ORANGE.getId() + 1, "P", "Purple", "color-purple"),
    YELLOW(PURPLE.getId() + 1, "Y", "Yellow", "color-yellow"),
    CYAN(YELLOW.getId() + 1, "C", "Cyan", "color-cyan"),
    MAGENTA(CYAN.getId() + 1, "M", "Magenta", "color-magenta"),

    NONE(MAGENTA.getId() + 1, "N", "None", "color-none"),
    BLACK(NONE.getId() + 1, "K", "Black", "color-black"),
    WHITE(BLACK.getId() + 1, "W", "White", "color-white"),

    HIDDEN(WHITE.getId() + 1, "H", "Hidden", "color-hidden");

    private final int id;
    private final String strId;
    private final String strDescription;
    private final String cssStyleClass;

    public static final int SIZE = WHITE.getId() + 1;

    private static final HashMap<Integer, Color> INT_2_COLOR = new HashMap<>();
    private static final HashMap<String, Color> STR_2_COLOR = new HashMap<>();
    private static final HashMap<String, Color> STYLE_2_COLOR = new HashMap<>();

    static
    {
        for(final Color color : Color.values())
        {
            INT_2_COLOR.put(color.id, color);
            STR_2_COLOR.put(color.strId, color);
            STYLE_2_COLOR.put(color.cssStyleClass, color);
        }
    }

    /**
     * Constructora
     *
     * Crea un Color con los parametros
     * de entrada.
     *
     * @param id valor numerico.
     * @param strId letra.
     * @param strDescription Nombre del color.
     * @param cssStyleClass Estilo css del color.
     */

    Color(final int id, final String strId, final String strDescription, final String cssStyleClass)
    {
        this.id = id;
        this.strId = strId;
        this.strDescription = strDescription;
        this.cssStyleClass = cssStyleClass;
    }

    /**
     * Getter color por id.
     *
     * Devuelve uel color segun el id
     * del parametro de entrada.
     *
     * @param id id del color.
     * @return Color.
     * @throws IllegalArgumentException Parametro no valido.
     */

    public static Color getColor(final int id) throws IllegalArgumentException
    {
        Color color = INT_2_COLOR.get(id);

        if(color == null) throw new IllegalArgumentException();

        return color;
    }

    /**
     * Getter color por id letra.
     *
     * Devuelve el color segun el id letra
     * del parametro de entrada.
     *
     * @param strId Id letra del color
     * @return Color.
     * @throws IllegalArgumentException Parametro no valido.
     */

    public static Color getColor(final String strId) throws IllegalArgumentException
    {
        Color color = STR_2_COLOR.get(strId);

        if(color == null) throw new IllegalArgumentException();

        return color;
    }

    /**
     * De estilo a color.
     *
     * Devuelve el color correspondiente al
     * estilo del parametro de entrada.
     *
     * @param style Estilo que corresponde a un color
     * @return Color.
     * @throws IllegalArgumentException Parametro no valido
     */

    public static Color getColorByStyle(final String style) throws IllegalArgumentException
    {
        Color color = STYLE_2_COLOR.get(style);

        if(color == null) throw new IllegalArgumentException();

        return color;
    }

    /**
     * Getter id.
     *
     * Devuelve un id de un color.
     *
     * @return id de color
     */

    public int getId()
    {
        return id;
    }

    /**
     * Getter ID letra.
     *
     * Devuelve un id formato letra de un color.
     *
     * @return id letra de color.
     */

    public String getStrId()
    {
        return strId;
    }

    /**
     * Getter StrDescription.
     *
     * Devuelve el parametro strDescription.
     *
     * @return nombre del color.
     */

    public String getStrDescription()
    {
        return strDescription;
    }

    /**
     * Getter CSS.
     *
     * Devuelve el parametro cssStyleClass
     *
     * @return color segun el estilo css.
     */

    public String getCssStyleClass()
    {
        return cssStyleClass;
    }

    /**
     * Getter lista de colores para crear codigos.
     *
     * Devuelve una lista de colores posibles para
     * crear codigos, segun la dificultad el tamaño varia.
     *
     * @param difficulty Dificultad de la partida.
     * @return Lista de colores.
     * @throws IllegalArgumentException Parametro no valido.
     */

    public static Set<Color> getValues(final Difficulty difficulty) throws IllegalArgumentException
    {
        int n = Constants.getNumColorsByDifficulty(difficulty);

        Set<Color> colorSet = new HashSet<>(n);

        for(int i = 0; i < n; ++i)
        {
            colorSet.add(getColor(i));
        }

        return colorSet;
    }

    /**
     * Getter correccion completa.
     *
     * Devuelve una lista de colores, uno negro, otro blanco
     * y otro vacio.
     *
     * @return Lista de colores de correccion.
     */

    public static Set<Color> getFullCorrectionValues()
    {
        Set<Color> colorSet = new HashSet<>();

        for(int i = NONE.getId(); i <= WHITE.getId(); ++i)
        {
            colorSet.add(getColor(i));
        }

        return colorSet;
    }

    /**
     * Getter correccion.
     *
     * Devuelve una lista de colores, uno negro y otro blanco.
     *
     * @return Lista de colores de correccion.
     */

    public static Set<Color> getCorrectionValues()
    {
        Set<Color> colorSet = new HashSet<>();

        for(int i = BLACK.getId(); i <= WHITE.getId(); ++i)
        {
            colorSet.add(getColor(i));
        }

        return colorSet;
    }

    /**
     * Lista de colores aleatorios.
     *
     * Devuelve una lista de colores aleatorios.
     *
     * @param colorSet Lista de colores
     * @throws IllegalArgumentException Parametro no valido.
     * @return Lista de colores aleatorios.
     */

    public static <T extends Set<S>, S> S getRandomColor(final T colorSet) throws IllegalArgumentException
    {
        if(colorSet.isEmpty()) throw new IllegalArgumentException();

        int rand = ThreadLocalRandom.current().nextInt(colorSet.size());

        ArrayList<S> colorArray = new ArrayList<>(colorSet);

        return colorArray.get(rand);
    }

    /**
     * Getter de Color aleatorio.
     *
     * Devuelve un color aleatorio entre una lista de colores.
     *
     * @param upperBound Numero de colores entre los que escoger.
     * @return Color aleatorio.
     */

    public static Color getRandomColor(final int upperBound) throws IllegalArgumentException
    {
        if(upperBound < 1) throw new IllegalArgumentException();

        int rand = ThreadLocalRandom.current().nextInt(upperBound);      // [0, uppedBound)

        return Color.getColor(rand);
    }
}
