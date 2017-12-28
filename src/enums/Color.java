package enums;

import util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

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

    private static HashMap<Integer, Color> int2Color = new HashMap<>();
    private static HashMap<String, Color> str2Color = new HashMap<>();
    private static HashMap<String, Color> style2Color = new HashMap<>();

    static
    {
        for(final Color color : Color.values())
        {
            int2Color.put(color.id, color);
            str2Color.put(color.strId, color);
            style2Color.put(color.cssStyleClass, color);
        }
    }

    private Color(final int id, final String strId, final String strDescription, final String cssStyleClass)
    {
        this.id = id;
        this.strId = strId;
        this.strDescription = strDescription;
        this.cssStyleClass = cssStyleClass;
    }

    public static Color getColor(final int id) throws IllegalArgumentException
    {
        Color color = int2Color.get(id);

        if(color == null) throw new IllegalArgumentException();

        return color;
    }

    public static Color getColor(final String strId) throws IllegalArgumentException
    {
        Color color = str2Color.get(strId);

        if(color == null) throw new IllegalArgumentException();

        return color;
    }

    public static Color getColorByStyle(final String style) throws IllegalArgumentException
    {
        Color color = style2Color.get(style);

        if(color == null) throw new IllegalArgumentException();

        return color;
    }

    public int getId()
    {
        return id;
    }

    public String getStrId()
    {
        return strId;
    }

    public String getStrDescription()
    {
        return strDescription;
    }

    public String getCssStyleClass()
    {
        return cssStyleClass;
    }

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

    public static Set<Color> getCorrectionValues()
    {
        Set<Color> colorSet = new HashSet<>();

        for(int i = NONE.getId(); i <= WHITE.getId(); ++i)
        {
            colorSet.add(getColor(i));
        }

        return colorSet;
    }

    public static <T extends Set<S>, S> S getRandomColor(final T colorSet) throws IllegalArgumentException
    {
        if(colorSet.isEmpty()) throw new IllegalArgumentException();

        int rand = ThreadLocalRandom.current().nextInt(colorSet.size());

        ArrayList<S> colorArray = new ArrayList<>(colorSet);

        return colorArray.get(rand);
    }

    public static Color getRandomColor(final int upperBound) throws IllegalArgumentException
    {
        if(upperBound < 1) throw new IllegalArgumentException();

        int rand = ThreadLocalRandom.current().nextInt(upperBound);      // [0, uppedBound)

        return Color.getColor(rand);
    }
}
