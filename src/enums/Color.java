package enums;

import java.util.HashMap;
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

    private int id;
    private String strId;

    private static HashMap<Integer, Color> int2Color;
    private static HashMap<String, Color> str2Color;

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
        Color color;
        color =  str2Color.get(strId);

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

    public static Color getRandomColor(int upperBound) throws IllegalArgumentException
    {
        if(upperBound < 1) throw new IllegalArgumentException();

        int rand = ThreadLocalRandom.current().nextInt(0, upperBound);      // [0, uppedBound)

        return Color.getColor(rand);
    }
}
