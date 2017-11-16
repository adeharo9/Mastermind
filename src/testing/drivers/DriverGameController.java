package testing.drivers;


import domain.controllers.GameController;
import enums.Color;
import testing.stubs.StubBoard;
import testing.stubs.StubGame;
import testing.stubs.StubTurn;
import util.ioUtils;

public class DriverGameController {

    public static void main (String args[]){
        DriverGameController driverGameController = new DriverGameController();
        driverGameController.exe();
    }

    public void exe()
    {
        testPointsEndTurn();
    }

    public void testPointsEndTurn(){
        GameController gameController = new GameController();
        StubGame game = new StubGame();
        ioUtils.printOut("Input current points (int):");
        game.setPoints(Integer.parseInt(ioUtils.input()));
        ioUtils.endLine();
        StubBoard board = new StubBoard();
        ioUtils.printOut("Input current turn (int):");
        board.setCurrentTurnNumber(Integer.parseInt(ioUtils.input()));
        ioUtils.endLine();
        ioUtils.printOut("Input code correction (K, W or N):");
        Color c = Color.getColor(ioUtils.input());
        StubTurn turn = new StubTurn();
        turn.setCorrection(c);
        gameController.pointsEndTurn();
        gameController.getPoints();
        ioUtils.printOut(Integer.toString(gameController.getPoints()));
    }
}
