package persistence;

import domain.classes.Game;
import java.io.File;

public class GamePersistence extends AbstractPersistence
{
    public GamePersistence()
    {

    }

    public Game load(Object id)
    {
        return new Game();
    }

    public boolean save(Object game)
    {
        String idGame, path;
        idGame = Integer.toString(((Game)game).getId()) + "/";
        path = basePath + gamesPath + idGame;
        File gameDir;
        gameDir = new File(path);
        try
        {
            boolean create;
            create = gameDir.mkdirs();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }
}
