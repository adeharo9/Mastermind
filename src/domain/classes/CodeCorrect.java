package domain.classes;

import enums.Color;
import enums.Difficulty;
import exceptions.IllegalActionException;
import util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Clase CodeCorrect.
 *
 * Esta clase representa el rol de una acción realizada
 * por el jugador para corregir una jugada.
 *
 * @author Alex
 */
public class CodeCorrect extends Action implements DeepCopyable
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */

    /**
     * Constructora de CodeCorrect vacío.
     *
     * Crea una nueva instancia de CodeCorrect y de Action.
     */
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
    public void checkAction(final Difficulty difficulty, final Code code, final Code solution) throws IllegalActionException
    {
        checkPinsInRange(difficulty, true, Color.getCorrectionValues());
        Code correction = getCodeCorrect(difficulty, code, solution);

        boolean b = correction.unorderedEquals(this.code);
        if(!b) throw new IllegalActionException("Wrong correction");
    }

    @SuppressWarnings("Duplicates")
    private Code getCodeCorrect(final Difficulty difficulty, final Code code, final Code solution)
    {
        int size = code.size();

        ArrayList<Boolean> match = new ArrayList<>(Collections.nCopies(size, Boolean.FALSE));

        ArrayList<Boolean> processed = new ArrayList<>(Collections.nCopies(size, Boolean.FALSE));

        List<Color> pins = new ArrayList<>(size);
        final List<Color> playerProposedSolution = code.getCodePins();
        final List<Color> sol = solution.getCodePins();

        for(int i = 0; i < size; ++i)
        {
            if(playerProposedSolution.get(i) == sol.get(i))
            {
                match.set(i, true);
                processed.set(i, true);
                pins.add(Color.BLACK);
            }
        }

        for(int i = 0; i < size; ++i)
        {
            if(!match.get(i))
            {
                for(int j = 0; j < size; ++j)
                {
                    if(!processed.get(j) && playerProposedSolution.get(i) == sol.get(j))
                    {
                        match.set(i, true);
                        processed.set(j, true);
                        pins.add(Color.WHITE);
                        break;
                    }
                }
            }
        }

        for(int i = pins.size(); i < size; ++i)
        {
            pins.add(Color.NONE);
        }

        return new Code(pins);
    }

    /* CLONING METHODS */

    public CodeCorrect deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CodeCorrect(this);
    }
}
