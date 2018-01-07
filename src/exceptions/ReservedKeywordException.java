package exceptions;

/**
 * Excepción de palabra reservada.
 *
 * Indica que el usuario ha introducido algún caracter o
 * símbolo de control reservado para acciones concretas que
 * no está relacionado con el motivo usual por el que
 * introduce datos al programa.
 *
 * @author Alejandro de Haro
 */

public class ReservedKeywordException extends AbstractException
{
    /**
     * Constructor por defecto.
     *
     * Construye una excepción con motivo vacío.
     */
    public ReservedKeywordException()
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
    public ReservedKeywordException(final String message)
    {
        super(message);
    }
}
