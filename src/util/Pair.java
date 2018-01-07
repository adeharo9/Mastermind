package util;

import java.io.Serializable;

/**
 * Clase par.
 *
 * Almacena un par de valores.
 *
 * @param <T1> Tipo del primer elemento del par.
 * @param <T2> Tipo del segundo elemento del par.
 */

public class Pair<T1 extends Serializable, T2 extends Serializable> implements Serializable
{
    public T1 first;
    public T2 second;

    /**
     * Constructor por defecto.
     *
     * Construye un par de valores nulos.
     */
    public Pair()
    {
        this.first = null;
        this.second = null;
    }

    /**
     * Constructor por inicialización.
     *
     * Construye un par dados dos valores.
     * @param first Primer elemento del par.
     * @param second Segundo elemento del par.
     */
    public Pair(T1 first, T2 second)
    {
        this.first = first;
        this.second = second;
    }
}
