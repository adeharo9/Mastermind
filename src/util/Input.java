package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public abstract class Input
{
    private static Scanner scanner;
    private static ArrayList<String> buffer;

    private Input()
    {
        scanner = new Scanner(System.in);
        buffer = new ArrayList<>();
    }

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

        next = buffer.get(0);
        buffer.remove(0);

        return next;
    }
}
