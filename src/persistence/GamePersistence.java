package persistence;

import domain.classes.Game;
import exceptions.FileDoesNotExist;
import exceptions.IntegrityCorruption;
import util.Translate;
import util.Utils;

import java.io.*;

public class GamePersistence extends AbstractPersistence
{
    public GamePersistence()
    {

    }

    public boolean exists(String key)
    {
        return true;
    }

    public Game load(String id)
    {
        File fileGame = new File(basePath + gamesPath + id);
        Game load = new Game();
        try {
            FileInputStream in = new FileInputStream(fileGame);
            ObjectInputStream s = new ObjectInputStream(in);
            load = (Game)s.readObject();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return load;
    }

    private void firstTime(File f){
        try{
            f.mkdirs();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean save(Object game) {
        String nameFile = Integer.toString(((Game) game).getId()) + ".gm";
        File fileGame = new File(basePath + gamesPath + nameFile);

        if(!fileGame.exists())
        {
            firstTime(fileGame);
        }

        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileGame));
            oos.writeObject(game);
            oos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return fileGame.exists();
    }

    public void delete(String key) throws FileDoesNotExist
    {

    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
