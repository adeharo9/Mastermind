package enums;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public enum Color
{
    RED(0, "R"),
    GREEN(1, "G"),
    BLUE(2, "B"),
    ORANGE(3, "O"),
    PURPLE(4, "P"),
    YELLOW(5, "Y"),
    CYAN(6, "C"),
    MAGENTA(7, "M"),

    NONE(8, "N"),
    BLACK(9, "K"),
    WHITE(10, "W");

    private final int id;
    private final String strId;

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

    private Color(int id, String strId)
    {
        this.id = id;
        this.strId = strId;

    }

    public static Color getColor(int id) throws IllegalArgumentException
    {
        Color color;

        color = int2Color.get(id);

        if(color == null) throw new IllegalArgumentException();

        return color;
    }

    public static Color getColor(String strId) throws IllegalArgumentException
    {
        Color color =  str2Color.get(strId);

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

    public static Collection<Color> getValues(Difficulty difficulty) throws IllegalArgumentException
    {
        int n;

        switch (difficulty)
        {
            case EASY:
            case MEDIUM:
                n = 6;
                break;
            case HARD:
                n = 8;
                break;
            default:
                throw new IllegalArgumentException();
        }

        Collection<Color> colorCollection = new HashSet<>(n);

        for(int i = 0; i < n; ++i)
        {
            colorCollection.add(getColor(i));
        }

        return colorCollection;
    }

    public static <T extends Set<S>, S> S getRandomColor(T colorSet) throws IllegalArgumentException
    {
        if(colorSet.isEmpty()) throw new IllegalArgumentException();

        int rand = ThreadLocalRandom.current().nextInt(colorSet.size());

        ArrayList<S> colorArray = new ArrayList<>(colorSet);

        return colorArray.get(rand);
    }

    public static Color getRandomColor(int upperBound) throws IllegalArgumentException
    {
        if(upperBound < 1) throw new IllegalArgumentException();

        int rand = ThreadLocalRandom.current().nextInt(upperBound);      // [0, uppedBound)

        return Color.getColor(rand);
    }
}
