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

    public GamePersistence()
    {

    }

    public boolean exists(String key)
    {
        File fileGame = new File(getFilePath(key));
        return fileGame.exists();
    }

    public String getDirPath()
    {
        return BASE_PATH + GAME_PATH;
    }

    public Game load(String id) throws IOException, ClassNotFoundException
    {
        return (Game) super.load(id);
    }

    public void save(Object game) throws IOException
    {
        String id = ((Game) game).getId();
        super.save(id, game);
    }
}
