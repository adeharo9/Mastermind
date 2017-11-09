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
    protected ArrayList<Color> bPins;

    /* CONSTRUCTION METHODS */

    @Deprecated
    public Code()
    {
        size = 0;
        bPins = new ArrayList<>();
    }

    @Deprecated
    public Code(int size) throws IllegalArgumentException
    {
        boolean b = size >= 0;
        if(!b) throw new IllegalArgumentException();

        this.size = size;
        bPins = new ArrayList<>(size);
    }

    public <C extends Collection<Color>> Code(C bPins)
    {
        this.size = bPins.size();
        setBPins(bPins);
    }

    public Code(Difficulty difficulty) throws IllegalArgumentException
    {
        size = 0;
        bPins = new ArrayList<>();
    }

    public Code(Code code) throws IllegalArgumentException, NullPointerException
    {
        size = code.size();
        setBPins(code.getBPins());
    }

    /* SET METHODS */

    public <C extends  Collection<Color>> void setBPins(C bPins) throws IllegalArgumentException, NullPointerException
    {
        boolean b = Utils.isValidCollection(bPins);
        if(!b) throw new IllegalArgumentException();

        this.bPins = new ArrayList<>(bPins.size());
        this.bPins.addAll(bPins);
    }

    public void addBPin(Color bPin) throws IndexOutOfBoundsException
    {
        if(this.bPins.size() >= this.size()) throw new IndexOutOfBoundsException();

        if(this.bPins == null)
        {
            this.bPins = new ArrayList<>();
        }

        this.bPins.add(bPin);
    }

    /* GET METHODS */

    public ArrayList<Color> getBPins()
    {
        return bPins;
    }

    public Color getBPinAt(int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return bPins.get(i);
    }

    /* CONSULTING METHODS */

    public int size()
    {
        return size;
    }

    public boolean equivalents(Code code)
    {
        return ((this.size == code.size()) && (this.bPins == code.getBPins()));
    }

    /* MODIFYING METHODS */

    public void clear()
    {
        bPins.clear();
    }

    /* VALIDITY METHODS */

    public boolean isValid()
    {
        return bPins != null;
    }

    /* CLONING METHODS */

    public Code deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Code(this);
    }
}