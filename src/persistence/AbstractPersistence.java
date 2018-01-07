package persistence;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
/**
 * AbstractPersistence.
 *
 * @author Alex
 */

public abstract class AbstractPersistence
{

    protected final static String BASE_PATH = "./";
    protected final static String GAME_EXTENSION = ".mm";

    /**
     * Existe en la persistencia.
     *
     * Indica si el objeto identificado por key existe
     * en la capa de datos.
     *
     * @param key Identificador del objeto.
     * @return True si el objeto existe.
     */
    public abstract boolean exists(String key);

    /**
     * Getter del directorio.
     *
     * Devuelve el path del directorio en el
     * cual se encuentra el objeto correspondiente.
     *
     * @return Path del directorio del objeto correspondiente.
     */
    public abstract String getDirPath();

    /**
     * Getter del fichero.
     *
     * Devuelve el path del fichero concreto en el
     * cual se encuentra el objeto identificado por key.
     *
     * @param key Identificador del objeto.
     * @return Path del fichero del objeto identificado por key.
     */
    public String getFilePath(String key)
    {
        return getDirPath() + key + GAME_EXTENSION;
    }

    /**
     * Cargar.
     *
     * Devuelve el objeto identificado por key.
     *
     * @param key Identificador del objeto.
     * @throws IOException Si el objeto no existe en la capa de persistencia.
     * @return Objeto identificado por key.
     * @throws ClassNotFoundException Si la definición del objeto no se corresponde a la leída en el documento.
     */
    public Object load(String key) throws IOException, ClassNotFoundException
    {
        boolean b = exists(key);
        if(!b) throw new FileNotFoundException();

        File gameFile = new File(getFilePath(key));

        FileInputStream in = new FileInputStream(gameFile);
        ObjectInputStream s = new ObjectInputStream(in);

        Object object = s.readObject();

        s.close();
        in.close();

        return object;
    }

    /**
     * Guardar.
     *
     * Guarda en la capa de persistencia el objeto object identificado por objectName.
     *
     * @param objectName Identificador del objeto a guardar.
     * @param object Objeto a guardar.
     * @throws IOException Si el objeto ya existe en la capa de persistencia.
     */
    public void save(String objectName, Object object) throws IOException
    {
        boolean b;

        File gameDirectory = new File(getDirPath());
        File gameFile = new File(getFilePath(objectName));

        gameDirectory.mkdirs();

        b = gameFile.exists();
        if(b) throw new FileAlreadyExistsException(getFilePath(objectName));

        gameFile.createNewFile();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(gameFile));
        oos.writeObject(object);
        oos.close();
    }

    /**
     * Eliminar.
     *
     * Elimina de la capa de persistencia el objeto identificado por key.
     *
     * @param key Identificador del objeto a eliminar.
     * @throws IOException Si el objeto identificado por key no existe en la capa de persistencia.
     */
    public void delete(String key) throws IOException
    {
        boolean b = exists(key);
        if(!b) throw new FileNotFoundException();

        String path = getFilePath(key);
        File file = new File(path);

        b = file.delete();
        if (!b) throw new IOException();
    }
}
