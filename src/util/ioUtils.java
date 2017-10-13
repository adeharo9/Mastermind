package util;

import java.util.Scanner;

public class ioUtils
{
    public static String input()
    {
        return new Scanner(System.in).next();
    }

    public static void println(String msg, int channel)
    {
        try
        {
            switch(channel)
            {
                case 1:
                    System.out.println(msg);
                    break;
                case 2:
                    System.err.println(msg);
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

    public static void printOut(String msg)
    {
        println(msg, 1);
    }

    public static void printErr(String msg)
    {
        println(msg, 2);
    }

    public static void endLine()
    {
        printOut("");
    }
}
