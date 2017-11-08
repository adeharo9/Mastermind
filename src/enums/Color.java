package enums;

import java.util.concurrent.ThreadLocalRandom;

public enum Color
{
    RED,
    GREEN,
    BLUE,
    ORANGE,
    PURPLE,
    YELLOW,
    NONE,
    BROWN,
    PINK,

    BLACK,
    WHITE;

    public static Color getRandomColor(int upperBound) throws IllegalArgumentException
    {
        if(upperBound < 1) throw new IllegalArgumentException();

        Color randColor;

        int rand = ThreadLocalRandom.current().nextInt(0, upperBound);      // [0, uppedBound)

        switch (rand)
        {
            case 0:
                randColor = RED;
                break;
            case 1:
                randColor = GREEN;
                break;
            case 2:
                randColor = BLUE;
                break;
            case 3:
                randColor = ORANGE;
                break;
            case 4:
                randColor = PURPLE;
                break;
            case 5:
                randColor = NONE;
                break;
            case 6:
                randColor = YELLOW;
                break;
            case 7:
                randColor = BROWN;
                break;
            case 8:
                randColor = WHITE;
                break;
            case 9:
                randColor = BLACK;
                break;
            case 10:
                randColor = PINK;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return randColor;
    }
}
