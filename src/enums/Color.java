package enums;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public enum Color
{
    RED(0, "R"),
    GREEN(1, "G"),
    BLUE(2, "B"),
    ORANGE(3, "O"),
    PURPLE(4, "P"),
    YELLOW(5, "Y"),
    NONE(6, "N"),
    BROWN(7, "R"),
    PINK(8, "I"),

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

    public static <T extends Set<Color>> Color getRandomColor(T colorSet) throws IllegalArgumentException
    {
        if(colorSet.isEmpty()) throw new IllegalArgumentException();

        int rand = ThreadLocalRandom.current().nextInt(colorSet.size());

        ArrayList<Color> colorArray = new ArrayList<>(colorSet);

        return colorArray.get(rand);
    }

    public static Color getRandomColor(int upperBound) throws IllegalArgumentException
    {
        if(upperBound < 1) throw new IllegalArgumentException();

        int rand = ThreadLocalRandom.current().nextInt(upperBound);      // [0, uppedBound)

        return Color.getColor(rand);
    }
}
