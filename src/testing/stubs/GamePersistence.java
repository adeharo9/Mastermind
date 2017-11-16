package testing.stubs;

public class GamePersistence {
    public Game load(String id){return new Game();}
    public void save(Game game){}
}
