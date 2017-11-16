package testing.stubs;

import java.util.*;

public class Game {
    public Board getBoard(){return new Board();}
    public List<Player> getPlayers(){return new ArrayList<>();}
}
