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
    public Code()
    {
        size = 0;
        pins = new ArrayList<>();
    }

    @Deprecated
    public Code(int size) throws IllegalArgumentException
    {
        boolean b = size >= 0;
        if(!b) throw new IllegalArgumentException();

        this.size = size;
        pins = new ArrayList<>(size);
    }

    public <C extends List<Color>> Code(C pins)
    {
        this.size = pins.size();
        setPins(pins);
    }

    public Code(Difficulty difficulty) throws IllegalArgumentException
    {
        size = 0;
        pins = new ArrayList<>();
    }

    public Code(Code code) throws IllegalArgumentException, NullPointerException
    {
        size = code.size();
        setPins(code.getPins());
    }

    /* SET METHODS */

    public <C extends  Collection<Color>> void setPins(C bPins) throws IllegalArgumentException, NullPointerException
    {
        boolean b = Utils.isValidCollection(bPins);
        if(!b) throw new IllegalArgumentException();

        this.pins = new ArrayList<>(bPins.size());
        this.pins.addAll(bPins);
    }

    @Deprecated
    public void addPin(Color bPin) throws IndexOutOfBoundsException
    {
        if(this.pins.size() >= this.size()) throw new IndexOutOfBoundsException();

        if(this.pins == null)
        {
            this.pins = new ArrayList<>();
        }

        this.pins.add(bPin);
    }

    /* GET METHODS */

    public List<Color> getPins()
    {
        return pins;
    }

    @Deprecated
    public Color getPinAt(int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return pins.get(i);
    }

    /* CONSULTING METHODS */

    public int size()
    {
        return size;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Code code = (Code) o;

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

    @Deprecated
    public boolean equivalents(Code code)
    {
        if(this.size == code.size())
        {
            for(int i = 0; i < this.size; ++i)
            {
                if(this.pins.get(i) != code.getPins().get(i)) return false;
            }
            return true;
        }

        return false;
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