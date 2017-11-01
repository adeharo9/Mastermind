package testing.drivers;
import presentation.controllers.PresentationController;

public class BoardTest
{
    public static void main(String args[])
    {
        String [][] codes= new String [4][8];
        for (int i=0;i<codes.length;++i){
                codes [i][0] = "R";
                codes [i][1] = "G";
                codes [i][2] = "B";
                codes [i][3] = "Y";
                codes [i][4] = "■";
                codes [i][5] = "Ø";
                codes [i][6] = "o";
                codes [i][7] = "o";
    }
        PresentationController.printBoard(codes);
    }
}
