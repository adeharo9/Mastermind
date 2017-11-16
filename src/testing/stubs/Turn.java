package testing.stubs;

import enums.Color;

import java.util.ArrayList;
import java.util.List;

public class Turn {

    private ArrayList<Color> correctionPins;

    public void setCorrection(Color c){
        correctionPins.add(c);
    }
    public final List<Color> getCorrectionPins()
    {
        return correctionPins;
    }
    public final Color getCorrectionPinAt(final int i) { return correctionPins.get(i); }


}
