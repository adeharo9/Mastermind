package domain.classes;

import enums.Color;
import enums.Difficulty;
import exceptions.IllegalActionException;
import util.DeepCopyable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * Constructora de CodeCorrect por copia.
     *
     * Crea una copia de la instancia de CodeCorrect y de Action
     * pasada como parámetro.
     *
     * @param codeCorrect CodeCorrect a copiar.
     * @throws IllegalArgumentException En caso que codeCorrect no sea válido.
     * @throws NullPointerException En caso que codeCorrect sea nulo.
     */
    public CodeCorrect(final CodeCorrect codeCorrect) throws IllegalArgumentException, NullPointerException
    {
        super(codeCorrect);
    }

    /**
     * Constructora de CodeCorrect a partir de un código.
     *
     * Crea una nueva instancia de CodeCorrect y Action a partir de un código.
     *
     * @param code código del padre, es decir, de Action.
     */
    public CodeCorrect(final Code code)
    {
        super(code);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

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
        board.addCorrection(getCode());
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
        checkPinsInRange(difficulty, true, Color.getFullCorrectionValues());
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
    /**
     * Copiar acción
     *
     * Hace copia profunda del CodeCorrect.
     *
     * @return una copia del CodeCorrect.
     */
    public CodeCorrect deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CodeCorrect(this);
    }
}
