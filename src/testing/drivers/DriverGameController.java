package testing.drivers;


import domain.controllers.GameController;
import enums.Color;
import testing.stubs.Board;
import testing.stubs.Game;
import testing.stubs.Turn;
import util.ioUtils;

public class DriverGameController {

    public static void main (String args[]){
        DriverGameController driverGameController = new DriverGameController();
        driverGameController.exe();
    }

    public void exe(){

    }

    public void testPointsEndTurn(){
        GameController gameController = new GameController();
        Game game = new Game();
        ioUtils.printOut("Input current points (int):");
        game.setPoints(Integer.parseInt(ioUtils.input()));
        ioUtils.endLine();
        Board board = new Board();
        ioUtils.printOut("Input current turn (int):");
        board.setCurrentTurnNumber(Integer.parseInt(ioUtils.input()));
        ioUtils.endLine();
        ioUtils.printOut("Input code correction (K, W or N):");
        Color c = Color.getColor(ioUtils.input());
        Turn turn = new Turn();
        turn.setCorrection(c);
        gameController.pointsEndTurn();
        gameController.getPoints();
        ioUtils.printOut(Integer.toString(gameController.getPoints()));


    }
}
