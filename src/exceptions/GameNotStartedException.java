package exceptions;

/**
 * Excepción de juego no comenzado.
 *
 * Indica que cierta acción se ha realizado sin
 * que el juego haya empezado, es decir, sin que se
 * haya llegado a jugar el primer turno.
 *
 * @author Rafael Ramírez
 */

public class GameNotStartedException extends AbstractException
{
    /**
     * Constructor por defecto.
     *
     * Construye una excepción con motivo vacío.
     */
    public GameNotStartedException()
    {
        super();
    }

    /**
     * Constructor con motivo.
     *
     * Construye una excepción con motivo determinado.
     *
     * @param message Motivo de la excepción.
     */
    public GameNotStartedException(final String message)
    {
        super(message);
    }
}
