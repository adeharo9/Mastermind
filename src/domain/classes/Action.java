package domain.classes;

import enums.Color;
import enums.Difficulty;
import exceptions.IllegalActionException;
import util.Constants;
import util.DeepCopyable;

import java.util.List;
import java.util.Set;

/**
 * Clase Action.
 *
 * Esta clase representa el rol de una acción realizada
 * por el jugador.
 *
 * @author Alex
 */

public abstract class Action implements DeepCopyable
{
    /* ATTRIBUTES */

    protected Code code;

    /* CONSTRUCTION METHODS */

    protected Action()
    {

    }

    protected Action(final Code code) {
        this.code = code;
    }

    protected Action(final Action action) throws IllegalArgumentException, NullPointerException
    {
        setCode(action.getCode());
    }

    /* SET METHODS */

    /**
     * Setter del código.
     *
     * Indica el código, es decir, la combinación de
     * colores de la Acción del Player con su respectivo size.
     *
     * @param code Código de colores.
     * @throws IllegalArgumentException Si el código que entra como parámetro no es válido.
     * @throws NullPointerException Si el código es nulo.
     */

    public final void setCode(final Code code) throws IllegalArgumentException, NullPointerException
    {
        boolean b = code.isValid();
        if(!b) throw new IllegalArgumentException();

        this.code = code.deepCopy();
    }

    /* GET METHODS */

    /**
     * Getter del código.
     *
     * Devuelve el código, es decir, la combinación de colores y el size de la misma.
     *
     * @return Código asociado a la acción.
     */

    public final Code getCode()
    {
        return code;
    }

    /* OTHER METHODS */

    /**
     * Añadir código al board.
     *
     * Añade el código de la acción al Board
     * que se le pasa como parámetro.
     *
     * @param board Board al que añadir el código.
     */

    public abstract void addSelfToBoard(Board board);

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

    public abstract void checkAction(final Difficulty difficulty, final Code code, final Code solution) throws IllegalActionException;

    protected final void checkPinsInRange(Difficulty difficulty, boolean repetitionPolicy, Set<Color> colorSet) throws IllegalActionException
    {
        boolean b;
        int numPins = Constants.getNumPinsByDifficulty(difficulty);
        List<Color> colorList = code.getCodePins();
        b = colorList.size() == numPins;
        if(!b) throw new IllegalActionException("Code is of size " + colorList.size() + "; size " + numPins + " expected");


        for(final Color color : colorList)
        {
            b = colorSet.contains(color);
            if(!b) throw new IllegalActionException("Color " + color.getStrId() + " should not be there");

            if(!repetitionPolicy)
            {
                colorSet.remove(color);
            }
        }
    }

    /* VALIDATION METHODS */

    /**
     * Código válido
     *
     * Comprueba si el código de la acción es válido.
     *
     * @return true si no es null, en cualquier otro caso false.
     */

    public boolean isValid()
    {
        return code != null;
    }

    /* CLONING METHODS */

    /**
     * Copiar acción
     *
     * Hace copia profunda de la acción.
     *
     * @return una copia de la acción.
     */
    @Override
    public abstract Action deepCopy();
}