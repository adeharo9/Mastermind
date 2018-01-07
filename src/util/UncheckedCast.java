package util;

/**
 * Unchecked class class.
 *
 * Silences compiler warnings about unchecked casts spread throughout the code
 * and concentrates them in a unique point of the code, at the same time increasing
 * explicitly the usage of unchecked casts when used.
 *
 * @author Alejandro de Haro
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class UncheckedCast
{
    /**
     * Unchecked cast.
     *
     * Silences compiler warnings about unchecked casts spread throughout the code
     * and concentrates them in a unique point of the code, at the same time increasing
     * explicitly the usage of unchecked casts when used.
     *
     * @param object Cast origin type object.
     * @param <T> Cast destination type.
     * @return Casted object from original type to type T
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(final Object object)
    {
        return (T) object;
    }
}
