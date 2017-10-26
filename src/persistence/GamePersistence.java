package persistence;

import domain.classes.Game;
import exceptions.IntegrityCorruption;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class GamePersistence extends AbstractPersistence
{
    public GamePersistence()
    {

    }

    public boolean exists(String key)
    {
        File fileGame = new File(basePath + gamesPath + key + ".gm");
        return fileGame.exists();
    }

    public Game load(String id) throws IOException, ClassNotFoundException
    {
        if(!exists(id)) throw new FileNotFoundException();
        else {
            File fileGame = new File(basePath + gamesPath + id + ".gm");
            Game load = new Game();
            FileInputStream in = new FileInputStream(fileGame);
            ObjectInputStream s = new ObjectInputStream(in);
            load = (Game) s.readObject();
            return load;
        }
    }

    public void save(Object game) throws IOException, FileAlreadyExistsException
    {
        String nameFile = Integer.toString(((Game) game).getId()) + ".gm";
        File directoryGame = new File(basePath + gamesPath);
        File fileGame = new File(basePath + gamesPath + nameFile);
        directoryGame.mkdirs();
        if(fileGame.exists()) throw new FileAlreadyExistsException(basePath + gamesPath + nameFile);
        else fileGame.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileGame));
        oos.writeObject((Game) game);
        oos.close();
    }

    public String getPath(String key)
    {
        return basePath + gamesPath + key +".gm";
    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
