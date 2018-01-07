package util;

/**
 * Interfaz de copia profunda de objeto.
 *
 * Interfaz para copia completa (profunda) de un objeto cualquiera.
 *
 * @param <T> Tipo del objeto a copiar.
 *
 * @author Alejandro de Haro
 */
public interface DeepCopyable<T>
{
    /**
     * Copia profunda.
     *
     * Devuelve una copia profunda del objeto sobre el que se ejecuta dicho método.
     *
     * @return Copia profunda del objeto.
     */
    T deepCopy();
}
