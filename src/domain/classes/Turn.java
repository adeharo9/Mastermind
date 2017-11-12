package domain.classes;

import enums.Color;
import enums.Difficulty;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Turn extends Code implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private ArrayList<Color> correction;

    /* CONSTRUCTION METHODS */

    @Deprecated
    public Turn(final int n) throws IllegalArgumentException
    {
        super(n);
        correction = new ArrayList<>(n);
    }

    public Turn(final Difficulty difficulty)
    {
        super(difficulty);
        correction = new ArrayList<>();
    }

    public Turn(final Code code) throws IllegalArgumentException, NullPointerException
    {
        super(code);
    }

    public Turn(final Turn turn) throws IllegalArgumentException, NullPointerException
    {
        super(turn);

        setCorrection(turn.getCorrection());
    }

    /* SET METHODS */

    public void setCorrection(final List<Color> sPins) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValid(sPins);
        if(!b) throw new IllegalArgumentException();

        this.correction = new ArrayList<>(sPins.size());
        this.correction.addAll(sPins);
    }

    public void setCorrection(final Code code) throws IllegalArgumentException
    {
        setCorrection(code.getPins());
    }

    /* GET METHODS */

    public final List<Color> getCorrection()
    {
        return correction;
    }

    public final Color getCorrectionAt(final int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return correction.get(i);
    }

    /* CONSULTING METHODS */

    /* CONVERSION METHODS */

    public List<Color> toList()
    {
        List<Color> colorList = new ArrayList<>(pins.size() + correction.size());

        colorList.addAll(pins);
        colorList.addAll(correction);

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
        return isValid(correction);
    }

    /* CLONING METHODS */

    public Turn deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Turn(this);
    }
}