package testing.drivers;
import presentation.controllers.PresentationController;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BoardTest
{
    public static void main(String args[])
    {
        PresentationController presentationController = new PresentationController();
        ArrayList<ArrayList<String>> codes = new ArrayList<>(4);

        for(int i = 0; i < 4; ++i)
        {
            codes.add(new ArrayList<>());
        }

        for (ArrayList<String> code : codes){
            code.add("R");
            code.add("G");
            code.add("B");
            code.add("Y");
            code.add("R");
            code.add("G");
            code.add("B");
            code.add("W");
            code.add(" ");
            code.add(" ");
            code.add(" ");
            code.add(" ");
    }
        PresentationController.printBoard(codes);
    }
}
