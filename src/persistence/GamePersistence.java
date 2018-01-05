package persistence;

import domain.classes.Game;

import java.io.File;
import java.io.IOException;

/**
 * GamePersistence.
 *
 * @author Alex
 */

public class GamePersistence extends AbstractPersistence
{
    protected final static String PLAYER_PATH = "players/";
    protected final static String GAME_PATH = "games/";
    protected String playerPath;

    public GamePersistence()
    {

    }

    /**
     * Setter del playerPath.
     *
     * Indica el playerPath, es decir, el path del jugador al que pertenece la partida en cuestión.
     *
     * @param playerPath Path del Player.
     */
    public void setPlayerPath(String playerPath)
    {
        this.playerPath = playerPath;
    }

    /**
     * Existe el Game.
     *
     * Indica si la partida identificado por key existe
     * en la capa de datos.
     *
     * @param key Identificador del game.
     */
    public boolean exists(String key)
    {
        File fileGame = new File(getFilePath(key));
        return fileGame.exists();
    }

    /**
     * Getter del directorio del Game.
     *
     * Devuelve el path del directorio en el
     * cual se encuentra las partidas correspondientes
     * al jugador indicado en el playerPath.
     *
     */
    public String getDirPath() { return BASE_PATH + PLAYER_PATH + playerPath + GAME_PATH; }

    /**
     * Cargar partida.
     *
     * Devuelve la partida identificado por key.
     *
     * @param id Identificador de la partida.
     * @param player Identificador del jugador al que pertenece la partida.
     * @throws IOException Si el objeto no existe en la capa de persistencia.
     * @throws ClassNotFoundException Si la definición del objeto no se corresponde a la leída en el documento.
     */
    public Game load(String id, String player) throws IOException, ClassNotFoundException
    {
        setPlayerPath(player + "/");
        return (Game) super.load(id);
    }

    /**
     * Guardar partida.
     *
     * Guarda en la capa de persistencia la partida game del jugador identificado por player.
     *
     * @param game Partida a guardar.
     * @param player Identificador del objeto a guardar.
     * @throws IOException Si el objeto ya existe en la capa de persistencia.
     */
    public void save(Object game, String player) throws IOException
    {
        setPlayerPath(player + "/");
        String id = ((Game) game).getId();
        super.save(id, game);
    }
}
