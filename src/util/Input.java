package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public abstract class Input
{
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> buffer = new ArrayList<>();

    private static boolean isEmpty()
    {
        return buffer.isEmpty();
    }

    public static String next()
    {
        String next;

        if(isEmpty())
        {
            buffer = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
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
