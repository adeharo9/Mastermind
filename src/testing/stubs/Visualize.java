package testing.stubs;

import domain.classes.Turn;
import util.ioUtils;

public class Visualize
{
    public static void visualizeTurn(Turn turn)
    {
        for(int i : turn.getBPins())
        {
            ioUtils.print(Integer.toString(i));
            ioUtils.print(" ");
        }
        ioUtils.endLine();
        for(int i : turn.getSPins())
        {
            ioUtils.print(Integer.toString(i));
            ioUtils.print(" ");
        }
        ioUtils.endLine();
    }
}
