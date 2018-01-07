package persistence;

import domain.classes.Ranking;

import java.io.File;
import java.io.IOException;

/**
 * RankingPersistence.
 *
 * @author Alex
 */

public class RankingPersistence extends AbstractPersistence
{

    protected final static String RANKING_PATH = "ranking/";

    public RankingPersistence()
    {

    }

    /**
     * Existe en persistencia.
     *
     * Indica si el ranking existe
     * en la capa de datos.
     *
     * @return True si el archivo de ranking existe.
     */
    public boolean exists()
    {
        File fileRanking = new File(getDirPath() + "ranking.mm");
        return fileRanking.exists();
    }

    /**
     * Existe en la persistencia.
     *
     * Indica si ranking identificado por key existe
     * en la capa de datos.
     *
     * @param key Identificador del objeto.
     */
    public boolean exists(String key)
    {
        File fileRanking = new File(getDirPath() + "ranking.mm");
        return fileRanking.exists();
    }

    /**
     * Getter del directorio.
     *
     * Devuelve el path del directorio en el
     * cual se encuentra el ranking.
     *
     */
    public String getDirPath()
    {
        return BASE_PATH + RANKING_PATH;
    }

    /**
     * Cargar ranking.
     *
     * Devuelve el ranking.
     *
     * @return Ranking cargado.
     * @throws IOException Si el ranking no existe en la capa de persistencia.
     * @throws ClassNotFoundException Si la definición del objeto no se corresponde a la leída en el documento.
     */
    public Ranking load() throws IOException, ClassNotFoundException
    {
        return (Ranking) super.load("ranking");
    }

    /**
     * Cargar ranking.
     *
     * Devuelve el ranking.
     *
     * @param key Identificador del ranking.
     * @throws IOException Si el ranking no existe en la capa de persistencia.
     * @throws ClassNotFoundException Si la definición del objeto no se corresponde a la leída en el documento.
     */
    public Ranking load(String key) throws IOException, ClassNotFoundException
    {
        return (Ranking) super.load("ranking");
    }

    /**
     * Guardar ranking.
     *
     * Guarda en la capa de persistencia el objeto ranking.
     *
     * @param ranking  Ranking a guardar.
     * @throws IOException Si el ranking ya existe en la capa de persistencia.
     */
    public void save(Object ranking) throws IOException
    {
        super.save("ranking", ranking);
    }

    /**
     * Eliminar ranking.
     *
     * Elimina de la capa de persistencia el ranking identificado por key.
     *
     * @throws IOException Si el ranking no existe en la capa de persistencia.
     */
    public void delete() throws IOException
    {
        super.delete("ranking");
    }
}
