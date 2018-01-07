package testing.stubs;

public class StubGamePersistence {
    public StubGame load(String id){return new StubGame();}
    public void save(StubGame game){}
}
