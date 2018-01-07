package testing.stubs;

import enums.Difficulty;
import java.util.*;

public class StubBoardController {
    public StubBoard newBoard(Difficulty difficulty){return new StubBoard();}
    public void setBoardByReference(StubBoard board){}
    public List<StubTurn> getTurnSet(){return new ArrayList<>();}
}
