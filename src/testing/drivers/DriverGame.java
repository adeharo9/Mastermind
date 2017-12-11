package testing.drivers;

import domain.classes.Board;
import domain.classes.Game;
import domain.classes.Human;
import domain.classes.Player;
import enums.Difficulty;
import enums.Mode;
import util.ioUtils;

import java.util.ArrayList;
import java.util.List;


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
        ioUtils.printOutLn("Test of the constructors of Game");
        Game randomGame = new Game();
        ioUtils.printOutLn("Introduce the difficulty of the Game (h: hard, m: medium, e:easy). For example: e");
        String dif = ioUtils.input();
        Difficulty difficulty = null;
        switch(dif)
        {
            case "e":
                difficulty = Difficulty.EASY;
                break;
            case "m":
                difficulty = Difficulty.MEDIUM;
                break;
            case "h":
                difficulty = Difficulty.HARD;
                break;
            default:
                throw new IllegalArgumentException("Error in the input");
        }

        ioUtils.printOutLn("Choose a mode introducing a number:\n" +
                "1. HUMAN_VS_HUMAN\n" +
                "2. HUMAN_VS_CPU\n" +
                "3. CPU_VS_CPU");
        int mode = Integer.parseInt(ioUtils.input());
        Mode modeGame = null;
        switch(mode)
        {
            case 1:
                modeGame = Mode.HUMAN_VS_HUMAN;
                break;
            case 2:
                modeGame = Mode.HUMAN_VS_CPU;
                break;
            case 3:
                modeGame = Mode.CPU_VS_CPU;
                break;
            default:
                throw new IllegalArgumentException("Error in the input");
        }

        Game gameWithDifficulty = new Game(difficulty, modeGame);

        ioUtils.printOutLn("Now, introduce a id of a game:");
        String id = ioUtils.input();
        Game gameWithDifficultyAndId = new Game(id, difficulty, modeGame);
        this.testedGame = gameWithDifficultyAndId;
    }

    private void testGetsAndSets()
    {
        ioUtils.printOutLn("Test of getters and setters");

        ioUtils.printOutLn("Introduce points to test the setPoints function:");
        Integer initialPoints = Integer.parseInt(ioUtils.input());
        testedGame.setPoints(initialPoints);

        ioUtils.printOutLn("Introduce 2 id players to test the setPlayer (Input example: alex lucia):");
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < 2; ++i)
        {
            players.add(new Human(ioUtils.input()));
        }
        testedGame.setPlayers(players);

        ioUtils.printOutLn("If the setters and getters have worked correctly, the data input has been:\n" +
                            "Id: " + testedGame.getId() + "\nPoints: " + testedGame.getPoints() + "\nPlayers: " +
                            testedGame.getPlayers().get(0).getId() + " " + testedGame.getPlayers().get(1).getId());

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
