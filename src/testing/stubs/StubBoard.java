package testing.stubs;

public class StubBoard {

    private int currentTurn;
    private StubTurn lastTurn;

    public void setCurrentTurnNumber(int t){currentTurn = t;}
    public int getCurrentTurnNumber(){return currentTurn;}
    public StubTurn getLastTurn(){return lastTurn;}

}
