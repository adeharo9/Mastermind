package exceptions;

/**
 * Excepción por no coincidencia de contraseña.
 *
 * Indica que la contraseña introducida por el usuario no coincide
 * con la confirmación de contraseña del mismo.
 *
 * @author Alex Sánchez
 */

public class PasswordMismatchException extends AbstractException
{
    /**
     * Constructor por defecto.
     *
     * Construye una excepción con motivo vacío.
     */
    public PasswordMismatchException()
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
    public PasswordMismatchException(final String message)
    {
        super(message);
    }
}
