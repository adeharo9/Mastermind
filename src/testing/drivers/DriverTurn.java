package testing.drivers;

import domain.classes.Code;
import domain.classes.Turn;
import enums.Color;
import util.ioUtils;

import java.util.ArrayList;
import java.util.List;

public class DriverTurn {

    private Turn t;

    public Turn testConstructor(){

        ArrayList<Color> c1 = new ArrayList<>();
        Code c = new Code(c1);
        Turn t = new Turn(c);
        t = new Turn(t);
        ioUtils.printOutLn("Create turn OK");
        return t;
    }

    public void testSet(){
        ArrayList<Color> c1 = new ArrayList<>();
        c1.add(Color.BLACK);
        Code c = new Code(c1);
        t.setCorrection(c);
        ioUtils.printOutLn("Set turn OK");
    }

    public void testGetCode(){
        Code c = t.getCode();
        ioUtils.printOutLn("Get code OK");
    }

    public void testGetCorrection(){
        Code t1 = t.getCorrectionCode();
        ioUtils.printOutLn("Get correction code OK");
    }

    public void testGetCorrectionPins(){
        List<Color> c = t.getCorrectionPins();
        ioUtils.printOutLn("Get corrections pins OK");
    }

    public void testGetCorrectionPinAt(){
        Color c = t.getCorrectionPinAt(0);
        ioUtils.printOutLn("Get correction color OK");
    }

    public void exe(){
        Turn t = testConstructor();
        /*testSet();*/
        testGetCode();
        testGetCorrection();
        testGetCorrectionPins();
        /*testGetCorrectionPinAt();*/
    }

    public static void main (String args[]){

        DriverTurn dt = new DriverTurn();
        dt.exe();

    }

}
