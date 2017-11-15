package domain.classes;

import enums.Color;
import enums.Difficulty;
import exceptions.IllegalActionException;
import util.*;

import java.util.List;
import java.util.Set;

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

    public final void setCode(final Code code) throws IllegalArgumentException, NullPointerException
    {
        boolean b = code.isValid();
        if(!b) throw new IllegalArgumentException();

        this.code = code.deepCopy();
    }

    /* GET METHODS */

    public final Code getCode()
    {
        return code;
    }

    /* OTHER METHODS */

    public abstract void addSelfToBoard(Board board);

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

    public boolean isValid()
    {
        return code != null;
    }

    /* CLONING METHODS */

    public abstract Action deepCopy();
}