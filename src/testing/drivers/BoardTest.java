package testing.drivers;
import presentation.controllers.PresentationController;
import util.ioUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BoardTest
{
    public static void main(String args[])
    {
        PresentationController presentationController = new PresentationController();
        ArrayList<ArrayList<String>> codes = new ArrayList<>(4);
        ArrayList<String> code1 = new ArrayList<>();
        for(int i = 0; i < 4; ++i)
        {
            codes.add(new ArrayList<>());
        }

        for (ArrayList<String> code : codes){
            code.add("R");
            code.add("G");
            code.add("B");
            code.add("Y");
            code.add("W");
            code.add("W");
            code.add("W");
            code.add("W");
    }

            code1.add("R");
            code1.add("G");
            code1.add("B");
            code1.add("Y");


        presentationController.printBoard(codes);
        presentationController.printCode(code1);
    }
}
