package testing.drivers;

import domain.classes.Code;
import domain.classes.Turn;
import enums.Color;

import java.util.ArrayList;

public class DriverTurn {

    public void testConstructor(){

        ArrayList<Color> cl = new ArrayList<>();
        Code c = new Code(cl);
        Turn t = new Turn(c);


    }

    public void testSet(){

    }

    public void testGetCode(){

    }

    public void testGetCorrection(){

    }

    public static void main (String args[]){

    }



}
