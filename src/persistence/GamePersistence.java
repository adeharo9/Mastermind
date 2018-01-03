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

    public void setPlayerPath(String playerPath)
    {
        this.playerPath = playerPath;
    }

    public boolean exists(String key)
    {
        File fileGame = new File(getFilePath(key));
        return fileGame.exists();
    }

    public boolean exists(String key, String player)
    {
        setPlayerPath(player + "/");
        return exists(key);
    }

    public String getDirPath() { return BASE_PATH + PLAYER_PATH + playerPath + GAME_PATH; }

    public Game load(String id, String player) throws IOException, ClassNotFoundException
    {
        setPlayerPath(player + "/");
        return (Game) super.load(id);
    }

    public void save(Object game, String player) throws IOException
    {
        setPlayerPath(player + "/");
        String id = ((Game) game).getId();
        super.save(id, game);
    }
}
