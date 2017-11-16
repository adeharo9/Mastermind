package testing.stubs;

import java.util.*;

public class StubGame {
    private int points;
    private StubBoard Board;

    public void setPoints(int p){points = p;}
    public int getPoints(){return points;}
    public StubBoard getBoard(){return new StubBoard();}

    public List<StubPlayer> getPlayers(){return new ArrayList<>();}
}
