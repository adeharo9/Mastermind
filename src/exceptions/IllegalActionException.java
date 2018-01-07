package exceptions;

/**
 * Excepción de acción ilegal.
 *
 * Indica que cierta acción realizada durante el juego no es legal,
 * es decir, no cumple con las normas del juego.
 *
 * @author Alejandro de Haro
 */
public class IllegalActionException extends AbstractException
{
    /**
     * Constructor por defecto.
     *
     * Construye una excepción con motivo vacío.
     */
    public IllegalActionException()
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
    public IllegalActionException(final String message)
    {
        super(message);
    }
}
