package testing.stubs;

import enums.Difficulty;
import enums.Mode;

import java.util.*;

public class GameController {

    public Mode getMode(){return Mode.HUMAN_VS_HUMAN;}
    public Game getGame(){return new Game();}
    public String getId(){return "alex";}
    public void newGame(String id, Difficulty difficulty, Mode mode, Board board, List<Player> players){}
    public void setGameByReference(Game game){}
}
