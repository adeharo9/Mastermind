package domain.classes;

import enums.Color;
import enums.Difficulty;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Code implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    protected final int size;
    protected ArrayList<Color> pins;

    /* CONSTRUCTION METHODS */

    @Deprecated
    public Code(final int size) throws IllegalArgumentException
    {
        boolean b = size >= 0;
        if(!b) throw new IllegalArgumentException();

        this.size = size;
        pins = new ArrayList<>(size);
    }

    public <C extends List<Color>> Code(final C pins)
    {
        this.size = pins.size();
        setPins(pins);
    }

    public Code(final Difficulty difficulty) throws IllegalArgumentException
    {
        size = 0;
        pins = new ArrayList<>();
    }

    public Code(final Code code) throws IllegalArgumentException, NullPointerException
    {
        size = code.size();
        setPins(code.getPins());
    }

    /* SET METHODS */

    public final <C extends  Collection<Color>> void setPins(final C bPins) throws IllegalArgumentException, NullPointerException
    {
        boolean b = Utils.isValidCollection(bPins);
        if(!b) throw new IllegalArgumentException();

        this.pins = new ArrayList<>(bPins.size());
        this.pins.addAll(bPins);
    }

    /* GET METHODS */

    public final List<Color> getPins()
    {
        return pins;
    }

    /* CONSULTING METHODS */

    public final int size()
    {
        return size;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Code code = (Code) o;

        if (this.size != code.size) return false;
        return getPins() != null ? getPins().equals(code.getPins()) : code.getPins() == null;
    }

    @Override
    public int hashCode()
    {
        int result = size;
        result = 31 * result + (getPins() != null ? getPins().hashCode() : 0);
        return result;
    }

    /* MODIFYING METHODS */

    public void clear()
    {
        pins.clear();
    }

    /* VALIDITY METHODS */

    public boolean isValid()
    {
        return pins != null;
    }

    /* CLONING METHODS */

    public Code deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Code(this);
    }
}