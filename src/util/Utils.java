package util;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase de utilidades.
 *
 * Concentra distintas utilidades a ser usadas a lo largo del código.
 *
 * @author Rafael Ramírez.
 */

public abstract class Utils
{
    /**
     * Comprobador de colección de elementos válida.
     *
     * Comprueba si una colección de elementos es válida.
     *
     * @param collection Colección de elementos.
     * @param <T> Tipo de los elementos de la colección.
     * @return True si la colección no contiene elementos nulos.
     * @throws NullPointerException En caso que la colección sea nula.
     */
    public static <T> boolean isValidCollection(Collection<T> collection) throws NullPointerException
    {
        boolean b = true;

        for(final T element : collection)
        {
            b = element != null;
            if(!b) break;
        }

        return b;
    }

    private final static int ID_SIZE = 32;
    private final static String ALPHA_MAYUS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String ALPHA_MINUS = ALPHA_MAYUS.toLowerCase();
    private final static String NUMERIC = "0123456789";
    private final static String ALPHANUMERIC = ALPHA_MAYUS + ALPHA_MINUS + NUMERIC;

    /**
     * Auto ID sin colisiones.
     *
     * Genera un ID con probabilidad de colisión aproximada de 5.26*10^(-44)%.
     *
     * @param charSet Set de caracteres sobre el que generar el ID.
     * @return ID generado.
     */
    private static String autoID(String charSet)
    {
        int rand;
        String randChar;
        String id = "";

        for(int i = 0; i < ID_SIZE; ++i)
        {
            rand = ThreadLocalRandom.current().nextInt(0, charSet.length());
            randChar = Character.toString(charSet.charAt(rand));
            id = id.concat(randChar);
        }

        return id;
    }

    /**
     * Auto ID alfanumérico sin colisiones.
     *
     * Genera un ID alfanumérico con probabilidad de colisión aproximada de 5.26*10^(-44)%.
     *
     * @return ID generado.
     */
    public static String autoID()
    {
        return autoID(ALPHANUMERIC);
    }
}
