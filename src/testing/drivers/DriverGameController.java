package testing.drivers;

import domain.classes.Board;
import domain.controllers.GameController;
import enums.Difficulty;
import util.ioUtils;

public class DriverGameController {

    public void testPointsEndTurn(){
        GameController gameController = new GameController();
        
        ioUtils.printOut("Input current points (int):");
        int cpoints = Integer.parseInt(ioUtils.input());
        ioUtils.endLine();


    }

    public static void main (String args[]){
        DriverGameController driverGameController = new DriverGameController();
        driverGameController.exe();
    }

    public void exe(){

    }
}
