package util;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Utils
{
    public static <T> boolean isValidCollection(Collection<T> arrayList) throws NullPointerException
    {
        boolean b = true;

        for(final T element : arrayList)
        {
            b = element != null;
            if(!b) break;
        }

        return b;
    }

    private final static int ID_SIZE = 32;
    private final static String ALPHA_MAYUS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String ALPHA_MINUS = ALPHA_MAYUS.toLowerCase();
    private final static String NUMERIC = "0123456789";
    private final static String ALPHANUMERIC = ALPHA_MAYUS + ALPHA_MINUS + NUMERIC;

    private static String autoID(String charSet)
    {
        int rand;
        String randChar;
        String id = "";

        for(int i = 0; i < ID_SIZE; ++i)
        {
            rand = ThreadLocalRandom.current().nextInt(0, charSet.length());
            randChar = Character.toString(charSet.charAt(rand));
            id = id.concat(randChar);
        }

        return id;
    }

    public static String autoID()
    {
        return autoID(ALPHANUMERIC);
    }
}
