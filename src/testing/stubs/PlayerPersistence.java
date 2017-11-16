package testing.stubs;

import java.util.*;

public class PlayerPersistence {
    public Player load(String username){return new Player();}
    public boolean exists(String username){return false;}
    public void save(Player player){}
    public List<String> loadSavedGames(String id)
    {
        List<String> savedGames = new ArrayList<>();
        savedGames.add("alexgame");
        savedGames.add("rafaelgame");
        savedGames.add("alejandrogame");

        return savedGames;
    }
    public void savePlayerGame(String gameId, String playerId){}
}
