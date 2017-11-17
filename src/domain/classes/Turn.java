package domain.classes;

import enums.Color;
import util.*;

import java.io.Serializable;
import java.util.*;

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

    /*@Deprecated
    public Turn(final int n) throws IllegalArgumentException
    {
        super(n);
        correctionPins = new ArrayList<>(n);
    }*/

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
     *
     * @return
     */
    public final Code getCode()
    {
        return new Code(codePins);
    }

    public final Code getCorrectionCode()
    {
        return new Code(correctionPins);
    }

    public final List<Color> getCorrectionPins()
    {
        return correctionPins;
    }

    public final Color getCorrectionPinAt(final int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return correctionPins.get(i);
    }

    /* CONSULTING METHODS */

    /* CONVERSION METHODS */

    @Deprecated
    public List<Color> toList()
    {
        List<Color> colorList = new ArrayList<>(codePins.size() + correctionPins.size());

        colorList.addAll(codePins);
        colorList.addAll(correctionPins);

        return colorList;
    }

    /* TESTING METHODS */

    @Deprecated
    private boolean isValid(final List<Color> sPins) throws NullPointerException
    {
        return super.size() == sPins.size();
    }

    @Deprecated
    public boolean isValid() throws NullPointerException
    {
        return isValid(correctionPins);
    }

    /* CLONING METHODS */

    public Turn deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Turn(this);
    }
}