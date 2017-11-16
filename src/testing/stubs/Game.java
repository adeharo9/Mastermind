package testing.stubs;

import java.util.*;

public class Game {
    private int points;
    private Board Board;

    public void setPoints(int p){points = p;}
    public int getPoints(){return points;}
    public Board getBoard(){return Board;}

    public List<Player> getPlayers(){return new ArrayList<>();}
}
