package util;

/**
 * Console input/output utilities class
 *
 * Handles different operations of input/output
 * through console seamlessly.
 *
 * @author Alejandro de Haro
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class ioUtils
{
    /**
     * Input data.
     *
     * Gets the next word of input data.
     *
     * @return Next word to be processed.
     */
    public static String input()
    {
        return Input.next();
    }

    /**
     * Print message to channel.
     *
     * Prints the given message to the specified channel.
     *
     * @param message Message to be printed.
     * @param channel Channel ID where the message has to be printed.
     */
    public static void print(final String message, final int channel)
    {
        try
        {
            switch(channel)
            {
                case 1:
                    System.out.print(message);
                    break;
                case 2:
                    System.err.print(message);
                    break;
                default:
                    throw new IllegalArgumentException("channel must be 1 (stdout) or 2 (stderr)");
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Print message to stdout.
     *
     * Prints the given message to stdout channel.
     *
     * @param message Message to be printed to stdout.
     */
    public static void print(final String message)
    {
        print(message, 1);
    }

    /**
     * Print message with end of line to stdout.
     *
     * Prints the given message with a trailing end of line character to stdout channel.
     *
     * @param message Message to be printed to stdout.
     */
    public static void println(final String message)
    {
        print(message, 1);
        endLine();
    }

    /**
     * End of line character.
     *
     * Prints an end of line character to the specified channel.
     *
     * @param channel Channel where to print the end of line character.
     */
    public static void endLine(final int channel)
    {
        switch(channel)
        {
            case 1:
                System.out.println();
                break;
            case 2:
                System.err.println();
                break;
        }
    }

    /**
     * End of line character to stdout.
     *
     * Prints an end of line character to stdout channel.
     */
    public static void endLine()
    {
        endLine(1);
    }

    /**
     * Print message to stdout.
     *
     * Prints the given message to stdout channel.
     *
     * @param message Message to be printed to stdout.
     */
    public static void printOut(final String message)
    {
        print(message, 1);
    }

    /**
     * Print message to stderr.
     *
     * Prints the given message to stderr channel.
     *
     * @param message Message to be printed to stderr.
     */
    public static void printErr(final String message)
    {
        print(message, 2);
    }

    /**
     * Print message with end of line character to stdout.
     *
     * Prints the given message with a trailing end of line character to stdout channel.
     *
     * @param message Message to be printed to stdout.
     */
    public static void printOutLn(final String message)
    {
        printOut(message);
        endLine(1);
    }

    /**
     * Print message with end of line character to stderr.
     *
     * Prints the given message with a trailing end of line character to stderr channel.
     *
     * @param message Message to be printed to stderr.
     */
    public static void printErrLn(final String message)
    {
        printErr(message);
        endLine(2);
    }
}
