package enums;

import util.Constants;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public enum Color
{
    RED(0, "R", "Red"),
    GREEN(1, "G", "Green"),
    BLUE(2, "B", "Blue"),
    ORANGE(3, "O", "Orange"),
    PURPLE(4, "P", "Purple"),
    YELLOW(5, "Y", "Yellow"),
    CYAN(6, "C", "Cyan"),
    MAGENTA(7, "M", "Magenta"),

    NONE(8, "N", "None"),
    BLACK(9, "K", "Black"),
    WHITE(10, "W", "White");

    private final int id;
    private final String strId;
    private final String strDescription;

    private static HashMap<Integer, Color> int2Color = new HashMap<>();
    private static HashMap<String, Color> str2Color = new HashMap<>();

    static
    {
        for(final Color color : Color.values())
        {
            int2Color.put(color.id, color);
            str2Color.put(color.strId, color);
        }
    }

    private Color(final int id, final String strId, final String strDescription)
    {
        this.id = id;
        this.strId = strId;
        this.strDescription = strDescription;
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

    public static Set<Color> getValues(final Difficulty difficulty) throws IllegalArgumentException
    {
        int n = Constants.getNumColorsByDifficulty(difficulty);

        Set<Color> colorCollection = new HashSet<>(n);

        for(int i = 0; i < n; ++i)
        {
            colorCollection.add(getColor(i));
        }

        return colorCollection;
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
