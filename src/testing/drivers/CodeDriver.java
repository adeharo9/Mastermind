package testing.drivers;


import domain.classes.Code;
import enums.Color;
import util.ioUtils;

import java.util.ArrayList;
import java.util.List;

public class CodeDriver {

    public static void testConstructionMethod() {
        List<Color> lc = new ArrayList<>();
        lc.add(Color.ORANGE);
        lc.add(Color.ORANGE);
        lc.add(Color.ORANGE);
        lc.add(Color.ORANGE);
        Code c = new Code(lc);
    }

    public void testSetCodePins() {

    }

    public void testGetCodePins() {
        
    }

    public static void main (String args[]) {
        testConstructionMethod();
    }
}



