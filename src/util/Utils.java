package util;

import java.util.*;

public class Utils
{
    public static <T extends DeepCopyable<T>> ArrayList<T> deepCopy(ArrayList<T> arrayList1)
    {
        ArrayList<T> arrayList2 = new ArrayList<>(arrayList1.size());

        for(T element : arrayList1)
        {
            arrayList2.add(element.deepCopy());
        }

        return arrayList2;
    }
}
