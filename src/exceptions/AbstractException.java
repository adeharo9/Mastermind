package exceptions;

public abstract class AbstractException extends Exception
{
    private final String message;

    public AbstractException()
    {
        message = "";
    }

    public AbstractException(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
