package util;

public abstract class UncheckedCast
{
    @SuppressWarnings("unchecked")
    public static <T> T cast(final Object object)
    {
        return (T) object;
    }
}
