package persistence;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import domain.classes.Player;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashSet;
import java.util.Set;

/**
 * PlayerPersistence.
 *
 * @author Alex
 */

public class PlayerPersistence extends AbstractPersistence
{

    protected final static String PLAYERS_PATH = "players/";
    protected final static String CONFIG_FILE = "config.cfg";
    protected String playerPath;

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
     * Getter del directorio config.
     *
     * Devuelve el path del directorio config.cfg
     * del jugador identificador por playerId.
     *
     * @param playerId Identificador del jugador.
     *
     */
    protected String getConfigFilePath(String playerId)
    {
        setPlayerPath(playerId + "/");
        return  BASE_PATH + PLAYERS_PATH + playerPath + CONFIG_FILE;
    }

    /**
     * Getter del dircectorio.
     *
     * Devuelve el path del directorio del jugador playerPath.
     *
     */
    public String getDirPath()
    {
        return BASE_PATH + PLAYERS_PATH + playerPath;
    }



    public PlayerPersistence()
    {

    }

    /**
     * Existe el jugador en la persistencia.
     *
     * Indica si el jugador identificado por key existe
     * en la capa de datos.
     *
     * @param key Identificador del jugador.
     */
    public boolean exists(String key)
    {
        File filePlayer = new File(getDirPath() + key + GAME_EXTENSION);
        return filePlayer.exists();
    }

    /**
     * Existe el directorio del jugador en la persistencia.
     *
     * Indica si el directorio del jugador identificado por playerPath existe
     * en la capa de datos.
     *
     */
    public boolean existsDir()
    {
        File filePlayer = new File(getDirPath());
        return filePlayer.exists();
    }

    /**
     * Cargar jugador.
     *
     * Devuelve el jugador identificado por playerId.
     *
     * @param playerId Identificador del jugador.
     * @throws IOException Si el jugador no existe en la capa de persistencia.
     * @throws ClassNotFoundException Si la definición del objeto no se corresponde a la leída en el documento.
     */
    public Player load(String playerId) throws IOException, ClassNotFoundException
    {
        setPlayerPath(playerId + "/");
        return (Player) super.load(playerId);
    }

    /**
     * Guardar jugador.
     *
     * Guarda en la capa de persistencia el jugador player.
     *
     * @param player Jugador a guardar.
     * @throws IOException Si el jugador ya existe en la capa de persistencia.
     */
    public void save(Object player) throws IOException
    {
        String playerId = ((Player) player).getId();
        setPlayerPath(playerId + "/");

        super.save(playerId, player);
    }

    /**
     * Guardar partida de jugador.
     *
     * Guarda en la capa de persistencia la partida gameId del jugador playerId.
     *
     * @param gameId Identificador de la partida a guardar.
     * @param playerId Identificador del jugador al que pertenece la partida.
     * @throws IOException Si el jugador ya tiene guardada una partida con el mismo nombre en la capa de persistencia.
     */
    public void savePlayerGame(String gameId, String playerId) throws IOException
    {
        String filePath = getConfigFilePath(playerId);
        File configFile = new File(filePath);

        boolean b = configFile.exists();
        if(!b) b = configFile.createNewFile();
        if(!b) throw new FileAlreadyExistsException("");

        BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));
        out.write(gameId);
        out.write("\n");
        out.close();
    }

    /**
     * Cambiar nombre de jugador.
     *
     * Cambia el nombre del jugador identificado por oldUsername por el nombre newUsername.
     *
     * @param oldUsername Identificador del nombre actual del jugador.
     * @param newUsername Identificador del nuevo nombre del jugador.
     * @throws IOException Si ya existe un jugador identificado por newUsername.
     */
    public void renamePlayer(final String oldUsername, final String newUsername) throws IOException
    {
        setPlayerPath(newUsername + "/");

        boolean b = existsDir();
        if(b) throw new IOException();

        setPlayerPath(oldUsername + "/");
        String playerFilePath = getFilePath(oldUsername);
        String newPlayerFilePath = getFilePath(newUsername);

        File oldPlayerFile = new File(playerFilePath);
        File newPlayerFile = new File(newPlayerFilePath);

        b = oldPlayerFile.renameTo(newPlayerFile);
        if(!b) throw new IOException();

        String oldPlayerDirPath = getDirPath();
        setPlayerPath(newUsername + "/");
        String newPlayerDirPath = getDirPath();

        File oldPlayerDir = new File(oldPlayerDirPath);
        File newPlayerDir = new File(newPlayerDirPath);

        b = oldPlayerDir.renameTo(newPlayerDir);
        if(!b) throw new IOException();
    }

    /**
     * Guardar partidas de jugador.
     *
     * Guarda en la capa de persistencia el conjunto de identificadores
     * de las partidas gamesId del jugador playerId.
     *
     * @param gamesId Conjunto de partidas a guardar.
     * @param playerId Identificador del jugador al que pertenecen las partidas.
     * @throws IOException Si el jugador ya tiene guardada una partida con el mismo nombre en la capa de persistencia.
     */
    public void savePlayerGames(Set<String> gamesId, String playerId) throws IOException
    {
        deleteConfigFile(playerId);
        for(String gameId : gamesId)
        {
            savePlayerGame(gameId, playerId);
        }
    }

    /**
     * Cargar partidas de jugador.
     *
     * Devuelve el conjunto de identificadores de las partidas
     * del jugador identificado por playerId.
     *
     * @param playerId Identificador del jugador.
     * @throws IOException Si el jugador no tiene ninguna partida guardada.
     */
    public Set<String> loadSavedGames(String playerId) throws IOException
    {
        String savedGame;
        Set<String> savedGames = new HashSet<>();

        String filePath = getConfigFilePath(playerId);
        File configFile = new File(filePath);

        boolean b = configFile.exists();
        if(!b) throw new FileNotFoundException();

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        savedGame = br.readLine();
        while(savedGame != null)
        {
            savedGames.add(savedGame);
            savedGame = br.readLine();
        }

        br.close();

        return savedGames;
    }

    /**
     * Eliminar partida de jugador.
     *
     * Elimina el identificador de partida gameId del conjunto de identificadores de las partidas
     * del jugador identificado por playerId.
     *
     * @param gameId Identificador de la partida a eliminar.
     * @param playerId Identificador del jugador.
     * @throws IOException Si el jugador no tiene ninguna partida guardada.
     */
    public void deletePlayerGame(String gameId, String playerId) throws IOException
    {
        Set<String> savedGames = loadSavedGames(playerId);
        savedGames.remove(gameId);

        savePlayerGames(savedGames, playerId);

    }

    /**
     * Eliminar fichero config.
     *
     * Elimina el fichero que contiene los identificadores de las partidas
     * del jugador identificado por username.
     *
     * @param username Identificador del jugador.
     * @throws IOException Si el jugador no tiene ninguna partida guardada.
     */
    public void deleteConfigFile(final String username) throws IOException
    {
        String configFilePath = getConfigFilePath(username);

        File file = new File(configFilePath);
        file.delete();
    }
}
