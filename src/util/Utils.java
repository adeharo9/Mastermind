package util;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Utils
{

    @Deprecated
    public static <T extends DeepCopyable<T>> ArrayList<T> deepCopy(ArrayList<T> arrayList1) throws Exception
    {
        ArrayList<T> arrayList2 = new ArrayList<>(arrayList1.size());

        for(T element : arrayList1)
        {
            arrayList2.add(element.deepCopy());
        }

        return arrayList2;
    }

    @Deprecated
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    @Deprecated
    public static <T> boolean isValidIndex(ArrayList<T> arrayList, int i)
    {
        return arrayList != null && i >= 0 && i < arrayList.size();
    }

    public static <T> boolean isValidArrayList(ArrayList<T> arrayList) throws NullPointerException
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

    @Deprecated
    public static int autoIDInt()
    {
        return ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
    }
}
