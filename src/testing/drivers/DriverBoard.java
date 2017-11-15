package testing.drivers;

import domain.classes.Board;
import domain.classes.Turn;
import domain.classes.Code;
import enums.*;
import java.util.*;
import util.ioUtils;

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
        Board boardWithDifficulty = new Board(Difficulty.EASY);
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
        Difficulty difficulty = testedBoard.getDifficulty();
        int maxAttempts = testedBoard.getMaxAttempts();
        int turn = testedBoard.getCurrentTurnNumber();
        int nColumns = testedBoard.getNColumns();

        ioUtils.printOutLn("Difficulty: " + difficulty.toString() + " Max attempts: " + Integer.toString(maxAttempts) +
            " Actual turn: " + Integer.toString(turn) + " Number of columns: " + Integer.toString(nColumns) + "\n");

        testedBoard.setDifficulty(Difficulty.MEDIUM);
        testedBoard.setMaxAttempts(15);
        testedBoard.setNColumns(6);

        maxAttempts = testedBoard.getMaxAttempts();
        turn = testedBoard.getCurrentTurnNumber();
        nColumns = testedBoard.getNColumns();

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
        List<Color> pins = new ArrayList<>();
        pins.add(Color.BLUE);
        pins.add(Color.ORANGE);
        pins.add(Color.PURPLE);
        pins.add(Color.YELLOW);

        Code code = new Code(pins);
        try
        {
            testedBoard.addCode(code);
        }
        catch(IllegalArgumentException e)
        {
            ioUtils.printOutLn("Test passed. You can't add a code of 4 pins in a hard board that needs 6!\n");
        }

        pins.add(Color.ORANGE);
        pins.add(Color.YELLOW);
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


