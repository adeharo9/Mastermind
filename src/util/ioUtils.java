package util;

import java.util.Scanner;

public class ioUtils
{
    public static Input in = Input.getInstance();

    public static String input()
    {
        return in.next();
    }

    public static void print(String msg, int channel)
    {
        try
        {
            switch(channel)
            {
                case 1:
                    System.out.print(msg);
                    break;
                case 2:
                    System.err.print(msg);
                    break;
                default:
                    throw new Exception("Exception thrown on println(String msg, int channel): channel must be 1 (stdout) or 2 (stderr)");
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    public static void print(String msg)
    {
        print(msg, 1);
    }

    public static void println(String msg)
    {
        print(msg, 1);
        endLine();
    }

    public static void endLine(int channel)
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

    public static void endLine()
    {
        endLine(1);
    }

    public static void printOut(String msg)
    {
        print(msg, 1);
    }

    public static void printErr(String msg)
    {
        print(msg, 2);
    }

    public static void printOutLn(String msg)
    {
        printOut(msg);
        endLine(1);
    }

    public static void printErrLn(String msg)
    {
        printErr(msg);
        endLine(2);
    }
}
