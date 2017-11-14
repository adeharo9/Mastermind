package domain.classes;

import enums.Color;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Turn extends Code implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private ArrayList<Color> correctionPins;

    /* CONSTRUCTION METHODS */

    @Deprecated
    /*public Turn(final int n) throws IllegalArgumentException
    {
        super(n);
        correctionPins = new ArrayList<>(n);
    }*/

    public Turn(final Code code) throws IllegalArgumentException, NullPointerException
    {
        super(code);
        correctionPins = new ArrayList<>();
    }

    public Turn(final Turn turn) throws IllegalArgumentException, NullPointerException
    {
        super(turn);

        setCorrectionPins(turn.getCorrectionPins());
    }

    /* SET METHODS */

    public void setCorrectionPins(final List<Color> correctionPins) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValid(correctionPins);
        if(!b) throw new IllegalArgumentException();

        this.correctionPins = new ArrayList<>(correctionPins.size());
        this.correctionPins.addAll(correctionPins);
    }

    public void setCorrection(final Code code) throws IllegalArgumentException
    {
        setCorrectionPins(code.getCodePins());
    }

    /* GET METHODS */

    public final Code getCode()
    {
        return new Code(codePins);
    }

    public final Code getCorrectionCode()
    {
        return new Code(correctionPins);
    }

    public final List<Color> getCorrectionPins()
    {
        return correctionPins;
    }

    public final Color getCorrectionPinAt(final int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return correctionPins.get(i);
    }

    /* CONSULTING METHODS */

    /* CONVERSION METHODS */

    public List<Color> toList()
    {
        List<Color> colorList = new ArrayList<>(codePins.size() + correctionPins.size());

        colorList.addAll(codePins);
        colorList.addAll(correctionPins);

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
        return isValid(correctionPins);
    }

    /* CLONING METHODS */

    public Turn deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Turn(this);
    }
}