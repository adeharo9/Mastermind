package domain.classes;

import enums.Color;
import enums.Difficulty;
import exceptions.IllegalActionException;
import util.Constants;
import util.DeepCopyable;

/**
 * Clase CodeMake.
 *
 * Esta clase representa el rol de una acción realizada
 * por el jugador que indica el código de colores de la solución
 * y es el encargado de realizar la corrección de las diferentes
 * acciones.
 *
 * @author Alex
 */

public class CodeMake extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CodeMake(final Code code) {
        super(code);
    }

    public CodeMake(final CodeMake codeMake) throws IllegalArgumentException, NullPointerException
    {
        super(codeMake);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* OTHER METHODS */

    /**
     * Añadir solución.
     *
     * Añade la solución al tablero.
     *
     * @param board Tablero.
     */
    @Override
    public void addSelfToBoard(final Board board)
    {
        board.setSolution(getCode());
    }

    /**
     * Comprobar acción.
     *
     * Comprueba que la acción es válida.
     *
     * @param difficulty Dificultad.
     * @param code Código de colores propuesto.
     * @param solution Código colores solución.
     * @throws IllegalActionException Si la acción no es válida.
     */
    @Override
    public void checkAction(final Difficulty difficulty, final Code code, final Code solution) throws IllegalActionException
    {
        boolean repetitionPolicy = Constants.getRepetitionPolicyByDifficulty(difficulty);
        checkPinsInRange(difficulty, repetitionPolicy, Color.getValues(difficulty));
    }

    /* CLONING METHODS */

    @Override
    public CodeMake deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CodeMake(this);
    }
}