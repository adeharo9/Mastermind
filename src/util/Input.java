package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Input
{
    private Scanner scanner;
    private ArrayList<String> buffer;

    public Input()
    {
        scanner = new Scanner(System.in);
        buffer = new ArrayList<>();
    }

    public String next()
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

    public boolean isEmpty()
    {
        return buffer.isEmpty();
    }
}
