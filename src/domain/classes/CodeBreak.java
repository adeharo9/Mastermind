package domain.classes;

import enums.Color;
import enums.Difficulty;
import exceptions.IllegalActionException;
import util.*;

import java.util.List;
import java.util.Set;

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

    public CodeBreak(final CodeBreak codeBreak) throws IllegalArgumentException, NullPointerException
    {
        super(codeBreak);
    }

    public CodeBreak(final Code code) {
        super(code);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* OTHER METHODS */

    @Override
    public void addSelfToBoard(final Board board)
    {
        board.addCode(getCode());
    }

    @Override
    public void checkAction(final Difficulty difficulty, final Code code, final Code solution) throws IllegalActionException
    {
        boolean repetitionPolicy = Constants.getRepetitionPolicyByDifficulty(difficulty);
        checkPinsInRange(difficulty, repetitionPolicy, Color.getValues(difficulty));
    }

    /* CLONING METHODS */

    public CodeBreak deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CodeBreak(this);
    }
}