package persistence;

import domain.classes.Game;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class GamePersistence extends AbstractPersistence
{
    public GamePersistence()
    {

    }

    public Game load(Object id)
    {


        return new Game();
    }

    public boolean save(Object game) {
        String idGame, difficulty, time, path;
        boolean create = false;
        idGame = Integer.toString(((Game) game).getId());
        path = basePath + gamesPath + idGame;
        difficulty = path + "/difficulty.gm";
        time = path + "/time.gm";
        try {
            File gameDir = new File(path);
            create = gameDir.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (create) {
            try {
                File dif = new File(difficulty);
                BufferedWriter bw;
                bw = new BufferedWriter(new FileWriter(dif));
                bw.write(Integer.toString(((Game) game).getDifficulty()));
                File t = new File(time);
                bw = new BufferedWriter(new FileWriter(t));
                bw.write(Integer.toString(((Game) game).getTime()));
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return create;
    }
}
