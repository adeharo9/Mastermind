package domain.classes;

import enums.Color;
import enums.Difficulty;
import exceptions.IllegalActionException;
import util.Constants;
import util.DeepCopyable;

/**
 * Clase CodeCorrect.
 *
 * Esta clase representa el rol de una acción realizada
 * por el jugador para indica el código del juego.
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

    @Override
    public void addSelfToBoard(final Board board)
    {
        board.setSolution(getCode());
    }

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