package testing.drivers;

import domain.classes.Game;
import domain.classes.Board;
import domain.classes.Player;
import enums.Difficulty;
import enums.Mode;
import util.*;
import java.util.*;


public class DriverGame {

    private Game testedGame;

    public static void main(String[] args)
    {
        DriverGame driverGame = new DriverGame();
        driverGame.exe();
    }

    private void exe()
    {
        testConstructors();
        testGetsAndSets();
        testIsValid();
    }

    private void testConstructors()
    {
        Game randomGame = new Game();
        Game gameWithDifficulty = new Game(Difficulty.HARD, Mode.HUMAN_VS_CPU);
        Game gameWithDifficultyAndId = new Game(Utils.autoID(), Difficulty.HARD, Mode.HUMAN_VS_CPU);
        this.testedGame = gameWithDifficultyAndId;
    }

    private void testGetsAndSets()
    {
        String id = testedGame.getId();
        Integer initialPoints = testedGame.getPoints();

        List<Player> players = new ArrayList<>();
        players = testedGame.getPlayers();

        testedGame.setPoints(1000);
        if(testedGame.getPoints() != 1000){ioUtils.printOutLn("Error in setPoints!\n");}
        Board board = new Board();
        boolean correct = false;
        try{
            testedGame.setBoard(board);
        }
        catch(IllegalArgumentException e){
            correct = true;
        }
        if(!correct){ioUtils.printOutLn("Error in setBoard!");}
    }

    private void testIsValid()
    {
        if(!testedGame.isValid()){ioUtils.printOutLn("Error in isValid(). It detects as invalid a game that is valid.\n");}

        testedGame = new Game();

        if(testedGame.isValid()){ioUtils.printOutLn("Error in isValid(). It detects as valid a game that is invalid.\n");}
    }
}
