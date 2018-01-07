package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Input handler.
 *
 * Class responsible for handling input word-wise
 * instead of line-wise.
 *
 * @author Alejandro de Haro
 */
public abstract class Input
{
    /**
     * Scanner of console input.
     */
    private static final Scanner SCANNER = new Scanner(System.in);
    /**
     * Buffer of words. Contains the words already read but not yet given as input.
     */
    private static ArrayList<String> buffer = new ArrayList<>();

    /**
     * Empty checker.
     *
     * Checks if the content of the word buffer is empty.
     *
     * @return True if the buffer is empty.
     */
    private static boolean isEmpty()
    {
        return buffer.isEmpty();
    }

    /**
     * Next word lookup.
     *
     * Processes the next word from the word buffer or from the input in
     * case the buffer is empty.
     *
     * @return Next input word to process.
     */
    public static String next()
    {
        String next;

        if(isEmpty())
        {
            buffer = new ArrayList<>(Arrays.asList(SCANNER.nextLine().split(" ")));
        }

        if(!isEmpty())
        {
            next = buffer.get(0);
            buffer.remove(0);
        }
        else
        {
            next = "";
        }

        return next;
    }
}
