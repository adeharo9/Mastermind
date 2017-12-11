package testing.drivers;

import enums.Color;
import presentation.controllers.OldPresentationController;

import java.util.ArrayList;
import java.util.List;

public class DriverPresentationBoard
{
    public static void main(String args[])
    {
        OldPresentationController oldPresentationController = new OldPresentationController();
        List<List<Color>> codes = new ArrayList<>(4);
        List<List<Color>> corrections = new ArrayList<>(3);
        List<Color> code1 = new ArrayList<>();
        for(int i = 0; i < 4; ++i)
        {
            codes.add(new ArrayList<>());

        }
        for(int i = 0; i < 3; ++i){
            corrections.add(new ArrayList<>());
        }

        for (final List<Color> code : codes){
            code.add(Color.getRandomColor(6));
            code.add(Color.getRandomColor(6));
            code.add(Color.getRandomColor(6));
            code.add(Color.getRandomColor(6));
    }
        for (final List<Color> code : corrections){
            code.add(Color.BLACK);
            code.add(Color.WHITE);
            code.add(Color.NONE);
            code.add(Color.NONE);
        }
        code1.add(Color.getRandomColor(6));
        code1.add(Color.getRandomColor(6));
        code1.add(Color.getRandomColor(6));
        code1.add(Color.getRandomColor(6));

        //oldPresentationController.printBoard(codes,corrections);
        //oldPresentationController.printCode(code1);
    }
}
