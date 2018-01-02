package domain.classes;

import enums.Color;
import util.DeepCopyable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Turno.
 *
 * Esta clase representa un turno con sus respectivos código y corrección para ser almacenados
 * en el tablero correspondiente
 *
 * @author Alejandro de Haro
 */

public class Turn extends Code implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private ArrayList<Color> correctionPins;

    /* CONSTRUCTION METHODS */

    /**
     * Constructora de turno.
     *
     * Instancia un turno con un código determinado y con una corrección vacía (aun sin realizar).
     *
     * @param code Código de colores del turno.
     * @throws IllegalArgumentException En caso de que el turno no sea válido.
     * @throws NullPointerException En caso de que code sea nulo.
     */
    public Turn(final Code code) throws IllegalArgumentException, NullPointerException
    {
        super(code);
        correctionPins = new ArrayList<>();
    }

    /**
     * Constructora por copia.
     *
     * Instancia un turno copia de otro turno.
     *
     * @param turn Turno a partir del cual se quiere instanciar una copia.
     * @throws IllegalArgumentException En caso de que el turno no sea válido.
     * @throws NullPointerException En caso de que el turno sea nulo.
     */
    public Turn(final Turn turn) throws IllegalArgumentException, NullPointerException
    {
        super(turn);

        setCorrectionPins(turn.getCorrectionPins());
    }

    /* SET METHODS */

    /**
     * Setter de corrección por lista de colores.
     *
     * Indica la corrección asociada al turno en cuestión dada una lista de colores representativa
     * de dicha corrección.
     *
     * @param correctionPins Lista de colores representativa de la corrección
     * @throws IllegalArgumentException En caso de que la lista de colores no sea válida
     * @throws NullPointerException En caso de que la lista de colores sea nula
     */
    public void setCorrectionPins(final List<Color> correctionPins) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValid(correctionPins);
        if(!b) throw new IllegalArgumentException();

        this.correctionPins = new ArrayList<>(correctionPins.size());
        this.correctionPins.addAll(correctionPins);
    }

    /**
     * Setter de corrección por código.
     *
     * Indica la corrección asociada al turno en cuestión dado un código representativo de la corrección en cuestión.
     *
     * @param code Código representativo de la corrección.
     * @throws IllegalArgumentException En caso de que code no sea un código válido.
     */
    public void setCorrection(final Code code) throws IllegalArgumentException
    {
        setCorrectionPins(code.getCodePins());
    }

    /* GET METHODS */

    /**
     * Getter de código.
     *
     * Devuelve el código de este turno.
     *
     * @return Código que representa al turno.
     */
    public final Code getCode()
    {
        return new Code(codePins);
    }

    /**
     * Getter de código de corrección.
     *
     * Devuelve el código de la corrección asociada a este turno.
     *
     * @return Código que representa la corrección del turno.
     */
    public final Code getCorrectionCode()
    {
        return new Code(correctionPins);
    }

    /**
     * Getter de pines de corrección.
     *
     * Devuelve una lista de colores representativa de la corrección asociada a este turno.
     *
     * @return Lista de colores de la corrección del turno.
     */
    public final List<Color> getCorrectionPins()
    {
        return correctionPins;
    }

    /**
     * Getter de pines de corrección de acceso aleatorio.
     *
     * Devuelve el i-ésimo color de la corrección del turno.
     *
     * @param i Índice del color a devolver
     * @return I-ésimo color
     * @throws IndexOutOfBoundsException En caso de que i sea negativa o superior al tamaño de la corrección
     * @throws NullPointerException En caso de que la corrección sea nula
     */
    public final Color getCorrectionPinAt(final int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return correctionPins.get(i);
    }

    /* CONSULTING METHODS */

    /* CONVERSION METHODS */

    /* TESTING METHODS */

    /**
     * Consultora de validez.
     *
     * Comprueba si una lista de colores es válida.
     *
     * @param correction Lista de colores representativa de la corrección.
     * @return Devuelve true si el tamaño de la lista es igual que el tamaño del código asociado.
     * @throws NullPointerException En caso de que correction sea nulo
     * @deprecated En próximas revisiones se eliminará dado que no hay suficientes llamadas como para
     * mantener un método específico para esta función.
     */
    @Deprecated
    private boolean isValid(final List<Color> correction) throws NullPointerException
    {
        return super.size() == correction.size();
    }

    /**
     * Consultora de validez.
     *
     * Comprueba si la corrección de este turno es válida.
     *
     * @return Devuelve true si el tamaño de la corrección es igual que el tamaño del código asociado.
     * @throws NullPointerException En caso de que la corrección sea nula.
     * @deprecated En próximas revisiones se eliminará dado que no hay suficientes llamadas como para
     * mantener un método específico para esta función.
     */
    @Deprecated
    @Override
    public boolean isValid() throws NullPointerException
    {
        return isValid(correctionPins);
    }

    /* CLONING METHODS */

    /**
     * Copia de objeto.
     *
     * Devuelve una copia profunda del objeto actual.
     *
     * @return Copia profunda del objeto actual.
     * @throws IllegalArgumentException En caso de que alguno de los valores de este turno no sea válido.
     * @throws NullPointerException En caso de que alguno de los campos de este turno no esté inicializado.
     */
    @Override
    public Turn deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Turn(this);
    }
}