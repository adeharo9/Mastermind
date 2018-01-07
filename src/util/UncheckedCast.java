package util;

/**
 * Clase de casteo sin comprobación
 *
 * Silencia los avisos del compilador y los concentra en un único punto del código,
 * aumentando la explicidad con que se realizan estos casteos.
 */

public abstract class UncheckedCast
{
    /**
     * Casteo sin comprobación
     *
     * Silencia los avisos del compilador y los concentra en un único punto del código,
     * aumentando la explicidad con que se realizan estos casteos.
     *
     * @param object Objeto origen
     * @param <T> Tipo objetivo
     * @return Objeto casteado a tipo T
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(final Object object)
    {
        return (T) object;
    }
}
