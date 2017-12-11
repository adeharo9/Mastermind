package testing.stubs;

import java.util.ArrayList;
import java.util.List;

public class StubPlayerPersistence {
    public StubPlayer load(String username){return new StubPlayer();}
    public boolean exists(String username){return false;}
    public void save(StubPlayer player){}
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
