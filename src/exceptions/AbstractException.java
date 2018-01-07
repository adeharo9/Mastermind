package exceptions;

/**
 * Excepción abstracta.
 *
 * Clase base de excepción con la implementación básica de
 * todos los métodos necesarios para implementar una excepción
 * de Java.
 *
 * @author Alejandro de Haro
 */

public abstract class AbstractException extends Exception
{
    /**
     * Motivo de la excepción.
     */
    private final String message;

    /**
     * Constructor por defecto.
     *
     * Construye una excepción con motivo vacío.
     */
    public AbstractException()
    {
        message = "";
    }

    /**
     * Constructor con motivo.
     *
     * Construye una excepción con motivo determinado.
     *
     * @param message Motivo de la excepción.
     */
    public AbstractException(final String message)
    {
        this.message = message;
    }

    /**
     * Motivo de excepción.
     *
     * Devuelve el motivo por el que se lanzó la excepción.
     *
     * @return Motivo de la excepción.
     */
    public String getMessage()
    {
        return message;
    }
}
