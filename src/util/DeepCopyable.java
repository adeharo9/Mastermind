package util;

public interface DeepCopyable<T>
{
    T deepCopy() throws Exception;
}
