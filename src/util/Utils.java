package util;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Utils
{
    public static <T> boolean isValidCollection(Collection<T> arrayList) throws NullPointerException
    {
        boolean b = true;

        for(T element : arrayList)
        {
            b = element != null;
            if(!b) break;
        }

        return b;
    }

    private final static int idSize = 32;
    private final static String alphaMayus = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String alphaMinus = alphaMayus.toLowerCase();
    private final static String numeric = "0123456789";
    private final static String alphanumeric = alphaMayus + alphaMinus + numeric;

    private static String autoID(String charSet)
    {
        int rand;
        String randChar;
        String id = "";

        for(int i = 0; i < idSize; ++i)
        {
            rand = ThreadLocalRandom.current().nextInt(0, charSet.length());
            randChar = Character.toString(charSet.charAt(rand));
            id = id.concat(randChar);
        }

        return id;
    }

    public static String autoID()
    {
        return autoID(alphanumeric);
    }
}
