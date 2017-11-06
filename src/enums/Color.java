package enums;

import java.util.concurrent.ThreadLocalRandom;

public enum Color
{
    BLACK,
    BLUE,
    GREEN,
    ORANGE,
    PURPLE,
    RED,
    WHITE,
    YELLOW;

    public Color getRandomColor(Difficulty difficulty) throws IllegalArgumentException
    {
        int bound;
        Color randColor;

        switch(difficulty)
        {
            case EASY:
            case MEDIUM:
                bound = 4;
                break;
            case HARD:
                bound  = 6;
                break;
            default:
                throw new IllegalArgumentException();
        }

        int rand = ThreadLocalRandom.current().nextInt(0, bound);

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
                randColor = YELLOW;
                break;
            case 4:
                randColor = PURPLE;
                break;
            case 5:
                randColor = ORANGE;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return randColor;
    }
}
