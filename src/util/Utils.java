package util;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Utils
{

    public static <T extends DeepCopyable<T>> ArrayList<T> deepCopy(ArrayList<T> arrayList1) throws Exception
    {
        ArrayList<T> arrayList2 = new ArrayList<>(arrayList1.size());

        for(T element : arrayList1)
        {
            arrayList2.add(element.deepCopy());
        }

        return arrayList2;
    }

    public static boolean isNull(Object object)
    {
        return object == null;
    }

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
            if(!b) return b;
        }

        return b;
    }

    private final static int idSize = 32;
    private final static String alphaMayus = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String alphaMinus = alphaMayus.toLowerCase();
    private final static String numeric = "0123456789";
    private final static String alphanumeric = alphaMayus + alphaMinus + numeric;

    public static String autoID(String charSet)
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

    public static int autoIDInt()
    {
        return Integer.parseInt(autoID(numeric));
    }
}
