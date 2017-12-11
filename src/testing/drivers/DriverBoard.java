package testing.drivers;

import domain.classes.Board;
import domain.classes.Code;
import domain.classes.Turn;
import enums.Color;
import enums.Difficulty;
import util.ioUtils;

import java.util.ArrayList;
import java.util.List;

public class DriverBoard {

    private Board testedBoard;

    public static void main(String[] args)
    {
        DriverBoard driverBoard = new DriverBoard();
        driverBoard.exe();
    }


    private void exe()
    {
        testConstructors();
        testGetsAndSets();
        testAddTurn();
        testAddCodeAndAddCorrection();
    }

    private void testConstructors()
    {
        Board board = new Board();
        Difficulty difficulty = null;
        ioUtils.printOutLn("Introduce a difficulty (introduce h if you want hard " +
                "difficulty, m if you want medium and e if you want easy): ");
        String diff = ioUtils.input();
        switch(diff) {
            case "h":
                difficulty = Difficulty.HARD;
                break;
            case "m":
                difficulty = Difficulty.MEDIUM;
                break;
            case "e":
                difficulty = Difficulty.EASY;
                break;
            default:
                throw new IllegalArgumentException("Error with input");
        }

        Board boardWithDifficulty = new Board(difficulty);
        this.testedBoard = boardWithDifficulty;

        try
        {
            Board boardWithAnotherBoard = new Board(boardWithDifficulty);
        }
        catch (NullPointerException e)
        {
            ioUtils.printOutLn("The constructors functions of Board works correctly!\n");
        }
    }

    private void testGetsAndSets()
    {
        Difficulty difficulty = null;
        ioUtils.printOutLn("Test of getter and setters of Board:");
        ioUtils.printOutLn("Introduce a difficulty (introduce h if you want hard" +
                                "difficulty, m if you want medium and e if you want easy");

        String diff = ioUtils.input();
        switch(diff) {
            case "h":
                difficulty = Difficulty.HARD;
                break;
            case "m":
                difficulty = Difficulty.MEDIUM;
                break;
            case "e":
                difficulty = Difficulty.EASY;
                break;
            default:
                throw new IllegalArgumentException("Error with input");
        }

        testedBoard.setDifficulty(difficulty);
        Difficulty getDifficulty = testedBoard.getDifficulty();
        ioUtils.printOutLn("If the setDifficulty and the getDifficulty work correctly, the difficulty introduced has been: " +
                             getDifficulty.toString());

        ioUtils.printOutLn("Now, introduce a integer that will be the number of maximum attempts:");
        int maxAttempts = 0;
        try{
            maxAttempts = Integer.parseInt(ioUtils.input());
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }

        testedBoard.setMaxAttempts(maxAttempts);
        ioUtils.printOutLn("If the setMaxAttempts and the getMaxAttempts work correctly, the number introduced has been: +" +
                testedBoard.getMaxAttempts());

        ioUtils.printOutLn("Now, introduce a integer that will be the number of columns:");
        int nColumns = 0;
        try{
            nColumns = Integer.parseInt(ioUtils.input());
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }

        testedBoard.setNColumns(nColumns);
        ioUtils.printOutLn("If the setNColumns and the getNColumns work correctly, the number introduced has been: +" +
                testedBoard.getNColumns());

        int turn = testedBoard.getCurrentTurnNumber();

        ioUtils.printOutLn("Difficulty: " + difficulty.toString() + " Max attempts: " + Integer.toString(maxAttempts) +
            " Actual turn: " + Integer.toString(turn) + " Number of columns: " + Integer.toString(nColumns) + "\n");
    }

    private void testAddTurn()
    {
        List<Color> pins = new ArrayList<>();
        pins.add(Color.BLUE);
        pins.add(Color.ORANGE);
        pins.add(Color.PURPLE);
        pins.add(Color.YELLOW);

        Code code = new Code(pins);
        Turn turn = new Turn(code);
        try
        {
            testedBoard.addTurn(turn);
        }
        catch(IllegalArgumentException e)
        {
            ioUtils.printOutLn("Test passed. You can't add a turn of 4 pins in a hard board that needs 6!\n");
        }

        pins.add(Color.ORANGE);
        pins.add(Color.YELLOW);

        code = new Code(pins);
        turn = new Turn(code);

        try
        {
            testedBoard.addTurn(turn);
        }
        catch(Exception e)
        {
            ioUtils.printOutLn("Test passed. You can't add a turn without correction pins!\n");
        }

        List<Color> pinsCorrection = new ArrayList<>();
        pinsCorrection.add(Color.BLACK);
        pinsCorrection.add(Color.WHITE);
        pinsCorrection.add(Color.WHITE);
        pinsCorrection.add(Color.NONE);
        pinsCorrection.add(Color.NONE);
        pinsCorrection.add(Color.NONE);

        Code correction = new Code(pinsCorrection);
        turn.setCorrection(correction);

        try
        {
            testedBoard.addTurn(turn);
        }

        catch(Exception e)
        {
            ioUtils.printOutLn("Test not passed. Error in addTurn function!\n");
        }
    }

    private void testAddCodeAndAddCorrection()
    {
        ioUtils.printOutLn("Test of addCode and addCorrection");
        ioUtils.printOutLn("Write a code of 4 colors using the following letters (Input example: O Y B G):\n" +
                "O: Orange\n" +
                "R: Red\n" +
                "G: Green\n" +
                "B: Blue\n" +
                "Y: Yellow\n" +
                "P: Purple");
        List<Color> pins = new ArrayList<>();
        for(int i = 0; i < 4; ++i)
        {
            Color color = Color.getColor(ioUtils.input());
            pins.add(color);
        }

        Code code = new Code(pins);
        try
        {
            testedBoard.addCode(code);
        }
        catch(IllegalArgumentException e)
        {
            ioUtils.printOutLn("Test passed. You can't add a code of 4 pins in a hard board that needs 5!\n");
        }

        ioUtils.printOutLn("Write a code of 2 colors using the following letters (Input example: O Y):\n" +
                "O: Orange\n" +
                "R: Red\n" +
                "G: Green\n" +
                "B: Blue\n" +
                "Y: Yellow\n" +
                "P: Purple");
        for(int i = 0; i < 2; ++i)
        {
            Color color = Color.getColor(ioUtils.input());
            pins.add(color);
        }

        code = new Code(pins);

        testedBoard.addCode(code);

        Turn turn = new Turn(code);
        List<Color> pinsCorrection = new ArrayList<>();
        pinsCorrection.add(Color.BLACK);
        pinsCorrection.add(Color.WHITE);
        pinsCorrection.add(Color.WHITE);
        pinsCorrection.add(Color.NONE);

        Code correction = new Code(pinsCorrection);
        try
        {
            testedBoard.addCorrection(correction);
        }
        catch(IllegalArgumentException e)
        {
            ioUtils.printOutLn("Test passed. You can't add a correction of 4 pins in a hard board that needs 6!\n");
        }

        pinsCorrection.add(Color.NONE);
        pinsCorrection.add(Color.NONE);
        correction = new Code(pinsCorrection);

        testedBoard.addCorrection(correction);
    }
}


