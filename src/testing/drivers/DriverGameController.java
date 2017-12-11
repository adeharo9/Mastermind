package testing.drivers;


import domain.classes.Board;
import domain.classes.Code;
import domain.classes.Game;
import domain.classes.Turn;
import domain.controllers.GameController;
import enums.Color;
import enums.Difficulty;
import enums.Mode;
import util.ioUtils;

import java.util.ArrayList;
import java.util.List;

public class DriverGameController {

    public static void main (String args[]){
        DriverGameController driverGameController = new DriverGameController();
        driverGameController.exe();
    }

    public void exe()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("1.- Test PointsEndTurn");
        ioUtils.printOutLn("2.- Test PointsClue");
        ioUtils.printOutLn("0.- Close");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        String i = ioUtils.input();
        switch (i) {
            case "1":
                testPointsEndTurn();
                break;
            case "2":
                testPointsClue();
                break;
            case "0":
                break;
        }
        ioUtils.endLine();
    }

    public void testPointsEndTurn(){
        GameController gameController = new GameController();
        Game game = new Game("hola", Difficulty.MEDIUM, Mode.HUMAN_VS_CPU);
        ioUtils.printOut("Input current points (int):");
        game.setPoints(Integer.parseInt(ioUtils.input()));
        ioUtils.endLine();
        Board board = new Board(Difficulty.MEDIUM);
        ioUtils.printOut("Input current turn (int):");
        int turnnumber = Integer.parseInt(ioUtils.input());
        ioUtils.endLine();
        ioUtils.printOut("Input code correction (one letter: K, W or N):");
        Color c = Color.getColor(ioUtils.input());
        List<Color> cl = new ArrayList<>();
        cl.add(Color.RED);
        cl.add(Color.RED);
        cl.add(Color.RED);
        cl.add(Color.RED);
        Code code = new Code(cl);
        Turn turn = new Turn(code);
        List<Color> corr = new ArrayList<>();
        corr.add(c);
        ioUtils.printOut("Input code correction (K, W or N):");
        c = Color.getColor(ioUtils.input());
        corr.add(c);
        ioUtils.printOut("Input code correction (K, W or N):");
        c = Color.getColor(ioUtils.input());
        corr.add(c);
        ioUtils.printOut("Input code correction (K, W or N):");
        c = Color.getColor(ioUtils.input());
        corr.add(c);
        Code correction = new Code(corr);
        turn.setCorrection(correction);
        for (int i=0; i<turnnumber;++i){
            board.addTurn(turn);
        }
        game.setBoard(board);
        gameController.setGameByReference(game);
        gameController.pointsEndTurn();
        gameController.getPoints();
        ioUtils.printOut(Integer.toString(gameController.getPoints()));
    }

    public void testPointsClue(){
        GameController gameController = new GameController();
        Game game = new Game("hola", Difficulty.MEDIUM, Mode.HUMAN_VS_CPU);
        ioUtils.printOut("Input current points (int):");
        game.setPoints(Integer.parseInt(ioUtils.input()));
        gameController.setGameByReference(game);
        gameController.pointsClue();
        gameController.getPoints();
        ioUtils.printOut(Integer.toString(gameController.getPoints()));
        ioUtils.endLine();
    }
}
