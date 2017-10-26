package testing.drivers;
import domain.classes.Game;
import persistence.GamePersistence;
import util.Difficulty;

public class TestPersistence {
    public static void main(String args[]) {
        Game test = new Game(10, Difficulty.easy);
        GamePersistence bd = new GamePersistence();
        try{
            bd.save(test);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try{
            bd.load(Integer.toString(10));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

