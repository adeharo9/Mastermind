package exceptions;

/**
 * Excepción de contraseña incorrecta.
 *
 * Indica que la contraseña introducida por el usuario no coincide con
 * la almacenada en sus archivos de datos.
 *
 * @author Alex Sánchez
 */

public class WrongPasswordException extends AbstractException
{
    /**
     * Constructor por defecto.
     *
     * Construye una excepción con motivo vacío.
     */
    public WrongPasswordException()
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
    public WrongPasswordException(final String message)
    {
        super(message);
    }
}
