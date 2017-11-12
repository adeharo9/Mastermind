package util;

import enums.Difficulty;

public final class Constants
{
    /* NUMBER OF COLORS ALLOWED */
    public static final int NUM_COLORS_EASY = 6;
    public static final int NUM_COLORS_MEDIUM = 6;
    public static final int NUM_COLORS_HARD = 8;

    /* NUMBER OF PINS TO BE GUESSED */
    public static final int NUM_PINS_EASY = 4;
    public static final int NUM_PINS_MEDIUM = 4;
    public static final int NUM_PINS_HARD = 6;

    /* MAXIMUM NUMBER OF ROUNDS */
    public static final int MAX_ROUNDS_EASY = 30;
    public static final int MAX_ROUNDS_MEDIUM = 25;
    public static final int MAX_ROUNDS_HARD = 20;

    /* REPETITIONS POLICY */
    public static final boolean REPETITIONS_EASY = false;
    public static final boolean REPETITIONS_MEDIUM = true;
    public static final boolean REPETITIONS_HARD = true;

    /* RANKING SYSTEM */

    public static final int RANKING_SIZE = 10;

    /* POINT SYSTEM */
    public static final int POINTS_WHITE = 25;
    public static final int POINTS_BLACK = 50;
    public static final int POINTS_ROUND = -10;
    public static final int POINTS_TIME = -1;
    public static final int POINTS_CLUE = -50;

    public static int getNumColorsByDifficulty(Difficulty difficulty) throws IllegalArgumentException
    {
        return getGenericByDifficulty(difficulty, NUM_COLORS_EASY, NUM_COLORS_MEDIUM, NUM_COLORS_HARD);
    }

    public static int getNumPinsByDifficulty(Difficulty difficulty) throws IllegalArgumentException
    {
        return getGenericByDifficulty(difficulty, NUM_PINS_EASY, NUM_PINS_MEDIUM, NUM_PINS_HARD);
    }

    public static int getMaxRoundsByDifficulty(Difficulty difficulty) throws IllegalArgumentException
    {
        return getGenericByDifficulty(difficulty, MAX_ROUNDS_EASY, MAX_ROUNDS_MEDIUM, MAX_ROUNDS_HARD);
    }

    public static boolean getRepetitionPolicyByDifficulty(Difficulty difficulty) throws IllegalArgumentException
    {
        return getGenericByDifficulty(difficulty, REPETITIONS_EASY, REPETITIONS_MEDIUM, REPETITIONS_HARD);
    }

    private Constants()
    {
        // Do not allow instantiation nor inheritance
    }

    private static <T> T getGenericByDifficulty(Difficulty difficulty, T easy, T medium, T hard)
    {
        T generic;

        switch (difficulty)
        {
            case EASY:
                generic = easy;
                break;
            case MEDIUM:
                generic = medium;
                break;
            case HARD:
                generic = hard;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return generic;
    }
}
