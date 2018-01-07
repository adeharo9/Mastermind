package util;

import java.io.Serializable;

/**
 * Serializable pair class.
 *
 * Stores a serializable pair of values.
 *
 * @param <T1> Type of first pair element, extending Serializable interface.
 * @param <T2> Type of second pair element, extending Serializable interface.
 *
 * @author Alejandro de Haro
 */
@SuppressWarnings({"CanBeFinal", "unused", "WeakerAccess"})
public class Pair<T1 extends Serializable, T2 extends Serializable> implements Serializable
{
    /**
     * First serializable element of pair.
     */
    public T1 first;
    /**
     * Second serializable element of pair.
     */
    public T2 second;

    /**
     * Default constructor.
     *
     * Constructs a serializable pair of serializable null values.
     */
    public Pair()
    {
        this.first = null;
        this.second = null;
    }

    /**
     * Initialization constructor.
     *
     * Constructs a serializable pair given two serializable values.
     *
     * @param first First serializable element of pair.
     * @param second Second serializable element of pair.
     */
    public Pair(T1 first, T2 second)
    {
        this.first = first;
        this.second = second;
    }
}
