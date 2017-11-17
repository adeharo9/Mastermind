package testing.stubs;

public class Board {

    private int currentTurn;
    private Turn lastTurn;

    public void setCurrentTurnNumber(int t){currentTurn = t;}
    public int getCurrentTurnNumber(){return currentTurn;}
    public Turn getLastTurn(){return lastTurn;}

}
