package util;

import java.util.*;

public class Utils
{
    public static <T extends Cloneable<T>> ArrayList<T> deepCopy(ArrayList<T> arrayList1)
    {
        ArrayList<T> arrayList2 = new ArrayList<>(arrayList1.size());

        for(T element : arrayList1)
        {
            arrayList2.add(element.clone());
        }

        return arrayList2;
    }
}
