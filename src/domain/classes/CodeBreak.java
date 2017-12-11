package domain.classes;

import enums.Color;
import enums.Difficulty;
import exceptions.IllegalActionException;
import util.Constants;
import util.DeepCopyable;

/**
 * Clase CodeBreak.
 *
 * Esta clase representa el rol de una acción realizada
 * por el jugador para intentar averiguar el código.
 *
 * @author Alex
 */

public class CodeBreak extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */
    /**
     * Constructora de CodeBreak por copia.
     *
     * Crea una copia de la instancia de CodeBreak y de Action
     * pasada como parámetro.
     *
     * @param codeBreak CodeBreak a copiar.
     * @throws IllegalArgumentException En caso que codeBreak no sea válido.
     * @throws NullPointerException En caso que codeBreak sea nulo.
     */
    public CodeBreak(final CodeBreak codeBreak) throws IllegalArgumentException, NullPointerException
    {
        super(codeBreak);
    }

    /**
     * Constructora de CodeBreak a partir de un código.
     *
     * Crea una nueva instancia de CodeBreak y Action a partir de un código.
     *
     * @param code código del padre, es decir, de Action.
     */
    public CodeBreak(final Code code) {
        super(code);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* OTHER METHODS */

    /* OTHER METHODS */

    /**
     * Añadir código al board.
     *
     * Añade el código de la acción al Board
     * que se le pasa como parámetro.
     *
     * @param board Board al que añadir el código.
     */

    @Override
    public void addSelfToBoard(final Board board)
    {
        board.addCode(getCode());
    }

    /**
     * Comprobar acción.
     *
     * Comprueba que las normas del juego
     * dado un código y la solución.
     *
     * @param difficulty Dificultad para comprobar normas.
     * @param code Código a comprobar.
     * @param solution Código solución.
     * @throws IllegalActionException En el caso que no cumpla las normas del juego.
     */

    @Override
    public void checkAction(final Difficulty difficulty, final Code code, final Code solution) throws IllegalActionException
    {
        boolean repetitionPolicy = Constants.getRepetitionPolicyByDifficulty(difficulty);
        checkPinsInRange(difficulty, repetitionPolicy, Color.getValues(difficulty));
    }

    /* CLONING METHODS */
    /**
     * Copiar acción
     *
     * Hace copia profunda del CodeBreak.
     *
     * @return una copia del CodeBreak.
     */
    public CodeBreak deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CodeBreak(this);
    }
}