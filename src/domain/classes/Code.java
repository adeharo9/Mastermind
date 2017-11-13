package domain.classes;

import enums.Color;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Code implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    protected final int size;
    protected ArrayList<Color> codePins;

    /* CONSTRUCTION METHODS */

    @Deprecated
    public Code(final int size) throws IllegalArgumentException
    {
        boolean b = size >= 0;
        if(!b) throw new IllegalArgumentException();

        this.size = size;
        codePins = new ArrayList<>(size);
    }

    public <C extends List<Color>> Code(final C codePins)
    {
        this.size = codePins.size();
        setCodePins(codePins);
    }

    public Code(final Code code) throws IllegalArgumentException, NullPointerException
    {
        size = code.size();
        setCodePins(code.getCodePins());
    }

    /* SET METHODS */

    public final <C extends  Collection<Color>> void setCodePins(final C codePins) throws IllegalArgumentException, NullPointerException
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