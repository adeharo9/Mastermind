package domain.classes;

import enums.Color;
import enums.Difficulty;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Code implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    protected final long orderedHash;
    protected final long unorderedHash;
    protected final int size;
    protected ArrayList<Color> codePins;

    public static Code getSolutionCorrection(Difficulty difficulty)
    {
        Color color = Color.BLACK;
        int numPins = Constants.getNumPinsByDifficulty(difficulty);

        List<Color> colorList = new ArrayList<>(numPins);

        for(int i = 0; i < numPins; ++i)
        {
            colorList.add(color);
        }

        return new Code(colorList);
    }

    private static long calcOrderedHash(List<Color> code)
    {
        int k = Color.SIZE;
        long orderedHash = 0;

        for(int i = 0; i < code.size(); ++i)
        {
            orderedHash += code.get(i).getId() * Math.pow((k), i);
        }

        return orderedHash;
    }

    private static long calcUnorderedHash(Collection<Color> code)
    {
        int k;
        int n = code.size();
        long unorderedHash = 0;

        for(Color color : code)
        {
            k = color.getId();
            unorderedHash += (Math.pow(n, k) - 1) / (n - 1);
        }

        return unorderedHash;
    }

    /* CONSTRUCTION METHODS */

    /*@Deprecated
    public Code(final int size) throws IllegalArgumentException
    {
        boolean b = size >= 0;
        if(!b) throw new IllegalArgumentException();

        this.size = size;
        codePins = new ArrayList<>(size);
    }*/

    public <C extends List<Color>> Code(final C codePins)
    {
        this.orderedHash = calcOrderedHash(codePins);
        this.unorderedHash = calcUnorderedHash(codePins);
        this.size = codePins.size();
        setCodePins(codePins);
    }

    public Code(final Code code) throws IllegalArgumentException, NullPointerException
    {
        this.orderedHash = code.orderedHash;
        this.unorderedHash = code.unorderedHash;
        size = code.size();
        setCodePins(code.getCodePins());
    }

    /* SET METHODS */

    protected final <C extends  Collection<Color>> void setCodePins(final C codePins) throws IllegalArgumentException, NullPointerException
    {
        boolean b = Utils.isValidCollection(codePins);
        if(!b) throw new IllegalArgumentException();

        this.codePins = new ArrayList<>(codePins.size());
        this.codePins.addAll(codePins);
    }

    /* GET METHODS */

    public final List<Color> getCodePins()
    {
        return codePins;
    }

    /* CONSULTING METHODS */

    public final int size()
    {
        return size;
    }

    /* MODIFYING METHODS */

    public void clear()
    {
        codePins.clear();
    }

    /* VALIDITY METHODS */

    public boolean isValid()
    {
        return codePins != null;
    }

    /* CLONING METHODS */

    public Code deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Code(this);
    }

    /* HASHING METHODS */

    public boolean orderedEquals(final Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;

        final Code code = (Code) o;

        return this.orderedHash == code.orderedHash;
    }

    public boolean unorderedEquals(final Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;

        final Code code = (Code) o;

        return this.unorderedHash == code.unorderedHash;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Code code = (Code) o;

        if (this.size != code.size) return false;
        return getCodePins() != null ? getCodePins().equals(code.getCodePins()) : code.getCodePins() == null;
    }

    @Override
    public int hashCode()
    {
        int result = size;
        result = 31 * result + (getCodePins() != null ? getCodePins().hashCode() : 0);
        return result;
    }
}