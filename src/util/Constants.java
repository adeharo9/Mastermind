package util;

import enums.Difficulty;

/**
 * Constantes del juego.
 *
 * Contenedor de las distintas constantes de configuración del juego.
 *
 * @author Alejandro de Haro, Alex Sánchez, Rafael Ramírez
 */

public final class Constants
{
    /* NUMBER OF COLORS ALLOWED */
    public static final int NUM_COLORS_EASY = 6;
    public static final int NUM_COLORS_MEDIUM = 6;
    public static final int NUM_COLORS_HARD = 8;

    /* NUMBER OF PINS TO BE GUESSED */
    public static final int NUM_PINS_EASY = 4;
    public static final int NUM_PINS_MEDIUM = 4;
    public static final int NUM_PINS_HARD = 5;

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
    public static final int POINTS_INIT = 3250;
    public static final int POINTS_WHITE = 35;
    public static final int POINTS_BLACK = 75;
    public static final int POINTS_ROUND = -10;
    public static final int POINTS_CLUE = -150;

    /* DIRECTORIES */
    public static final String VIEWS_PATH = "/presentation/views/";
    public static final String RESOURCES_PATH = "/resources/";

    /* IMAGES */
    public static final String ICON_FILE = "icon.png";
    public static final String WARNING_ICON_FILE = "warning-icon.png";

    /* GUI CONSTANTS */
    public static final int LOADING_TIME_MS = 1250;

    /* GENERAL MESSAGES */

    public static final String INFO_MESSAGE =
            "In game instructions:\n" +
            "    Drag and drop the circles in the corresponding area for set your color code.\n" +
            "    Click an area in your answer code to eliminate the color in this area.\n" +
            "    In CodeMaker mode, you can see your code in any moment (Show Code button).\n" +
            "    In codebreaker mode, you can get a clue of the CPU code in any moment in exchange of points (Help button).\n" +
            "\n" +
            "Player modes:\n" +
            "    Human vs. Human: play against other human\n" +
            "    Human vs. CPU: play against our algorithm\n" +
            "    CPU vs. CPU: see how our algorithm plays against himself\n" +
            "\n" +
            "Game modes:\n" +
            "    CODEMAKER: Make a code and CPU will try to solve it\n" +
            "    CODEBREAKER: CPU makes a code with specific rules and you should solve it\n" +
            "\n" +
            "Difficulty modes:\n" +
            "    EASY MODE: Code of 4 non-repeated colors, 6 possible colors\n" +
            "    MEDIUM MODE: Code of 4 colors, 6 possible colors\n" +
            "    HARD MODE: Code of 5 colors, 6 possible colors\n" +
            "\n" +
            "Correction rules:\n" +
            "    Black tokens: The color and the position of a token coincide\n" +
            "    White tokens: The color of a token coincide\n" +
            "    None tokens: The color of a token don't coincide";

    /* ERROR MESSAGES */
    public static final String WRONG_USERNAME_OR_PASSWORD = "Wrong username and/or password";
    public static final String PASSWORDS_MUST_MATCH = "Passwords must match";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    public static final String USER_REGISTERING_ERROR = "Error while registering user";
    public static final String USER_LOADING_ERROR = "Error while loading user";
    public static final String GAME_DOESNT_EXISTS  = "Selected game does not exist";
    public static final String GAME_LOADING_ERROR = "Error while loading game";
    public static final String EDIT_USERNAME = "Show rename text field";
    public static final String WRONG_PASSWORD = "Wrong current password";
    public static final String RANKING_LOAD_ERROR = "Error while saving ranking";
    public static final String GAME_NOT_STARTED_ERROR = "The game still has no code";
    public static final String NEW_PASSWORD_SAVED = "New password correctly saved";
    public static final String EMPTY_PASSWORD_ERROR = "Password should have at least 1 character";
    public static final String PLAYER_HAS_NO_SAVED_GAMES = "There are no saved games for this player";
    public static final String SAVED_GAMES_LIST_LOAD_ERROR = "Error while loading saved games list";
    public static final String RANKING_LOADING_ERROR = "Error while loading ranking";
    public static final String GAME_SAVING_ERROR = "Error while saving game";
    public static final String GAME_DELETING_ERROR = "Error while deleting game";
    public static final String PASSWORD_CHANGE_ERROR = "Error while changing password";

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
