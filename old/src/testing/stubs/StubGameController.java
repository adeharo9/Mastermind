package testing.stubs;

import enums.Difficulty;
import enums.Mode;

import java.util.*;

public class StubGameController {

    public Mode getMode(){return Mode.HUMAN_VS_HUMAN;}
    public StubGame getGame(){return new StubGame();}
    public String getId(){return "alex";}
    public void newGame(String id, Difficulty difficulty, Mode mode, StubBoard board, List<StubPlayer> players){}
    public void setGameByReference(StubGame game){}
}
