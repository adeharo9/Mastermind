package testing.stubs;

import enums.Difficulty;
import java.util.*;

public class BoardController {
    public Board newBoard(Difficulty difficulty){return new Board();}
    public void setBoardByReference(Board board){}
    public List<Turn> getTurnSet(){return new ArrayList<>();}
}
