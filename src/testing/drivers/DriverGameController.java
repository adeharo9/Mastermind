package testing.drivers;


import domain.controllers.GameController;
import enums.Color;
import testing.stubs.sBoard;
import testing.stubs.sGame;
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
        sGame game = new sGame();
        ioUtils.printOut("Input current points (int):");
        game.setPoints(Integer.parseInt(ioUtils.input()));
        ioUtils.endLine();
        sBoard board = new sBoard();
        ioUtils.printOut("Input current turn (int):");
        board.setCurrentTurnNumber(Integer.parseInt(ioUtils.input()));
        ioUtils.endLine();
        ioUtils.printOut("Input code correction (K, W or N):");
        Color c = Color.getColor(ioUtils.input());


    }
}
