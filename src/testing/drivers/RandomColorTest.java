package testing.drivers;

import domain.classes.Code;
import enums.Color;
import util.Pair;
import util.ioUtils;

import java.util.ArrayList;

public class RandomColorTest
{
    public static void main(String args[])
    {
        String colorStr;
        int numTests = 2000000;
        ArrayList<String> colors = new ArrayList<>(11);
        ArrayList<Pair<String, Integer>> counters = new ArrayList<>(11);

        colors.set(0, "RD");
        colors.set(1, "");

        for(int i = 0; i < colors.size(); ++i)
        {
            //counters.first = colors.get(i);
            //counter.second = 0;
        }

        Code code = new Code(6);

        for(int i = 0; i < numTests; ++i)
        {
            code.clear();
            code.setRandomCode();
            for (Color color : code.getBPins()) {
                switch (color) {
                    case RED:
                        colorStr = "RD";
                        counters.set(0, counters.get(0) + 1);
                        break;
                    case BLUE:
                        colorStr = "BL";
                        counters.set(1, counters.get(1) + 1);
                        break;
                    case BLACK:
                        colorStr = "BK";
                        counters.set(2, counters.get(2) + 1);
                        break;
                    case BROWN:
                        colorStr = "BR";
                        counters.set(3, counters.get(3) + 1);
                        break;
                    case GREEN:
                        colorStr = "GR";
                        counters.set(4, counters.get(4) + 1);
                        break;
                    case WHITE:
                        colorStr = "WH";
                        counters.set(5, counters.get(5) + 1);
                        break;
                    case ORANGE:
                        colorStr = "OR";
                        counters.set(6, counters.get(6) + 1);
                        break;
                    case YELLOW:
                        colorStr = "YE";
                        counters.set(7, counters.get(7) + 1);
                        break;
                    case NONE:
                        colorStr = "NO";
                        counters.set(8, counters.get(8) + 1);
                        break;
                    case PINK:
                        colorStr = "PI";
                        counters.set(9, counters.get(9) + 1);
                        break;
                    case PURPLE:
                        colorStr = "PU";
                        counters.set(10, counters.get(10) + 1);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }

                ioUtils.printOut(colorStr);
                ioUtils.printOut(" ");
            }
            ioUtils.endLine();
        }

        for(Integer counter : counters)
        {

        }
    }
}
