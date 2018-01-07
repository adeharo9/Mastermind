package util;

public abstract class ioUtils
{
    public static String input()
    {
        return Input.next();
    }

    public static void print(final String msg, final int channel)
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

    public static void print(final String msg)
    {
        print(msg, 1);
    }

    public static void println(final String msg)
    {
        print(msg, 1);
        endLine();
    }

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

    public static void endLine()
    {
        endLine(1);
    }

    public static void printOut(final String msg)
    {
        print(msg, 1);
    }

    public static void printErr(final String msg)
    {
        print(msg, 2);
    }

    public static void printOutLn(final String msg)
    {
        printOut(msg);
        endLine(1);
    }

    public static void printErrLn(final String msg)
    {
        printErr(msg);
        endLine(2);
    }
}
