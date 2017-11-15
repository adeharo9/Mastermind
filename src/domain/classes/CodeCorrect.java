package domain.classes;

import enums.Color;
import enums.Difficulty;
import exceptions.IllegalActionException;
import util.*;

import java.util.List;
import java.util.Set;

public class CodeCorrect extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    public CodeCorrect()
    {
        super();
    }

    public CodeCorrect(final CodeCorrect codeCorrect) throws IllegalArgumentException, NullPointerException
    {
        super(codeCorrect);
    }

    public CodeCorrect(final Code code)
    {
        super(code);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* OTHER METHODS */

    @Override
    public void addSelfToBoard(final Board board)
    {
        board.addCorrection(getCode());
    }

    @Override
    public void checkAction(Difficulty difficulty) throws IllegalActionException
    {
        checkPinsInRange(difficulty, true);
    }

    /* CLONING METHODS */

    public CodeCorrect deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CodeCorrect(this);
    }
}
