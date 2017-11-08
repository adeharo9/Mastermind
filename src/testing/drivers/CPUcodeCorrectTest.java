package testing.drivers;

import domain.classes.*;
import domain.controllers.*;
import enums.*;
import java.util.*;

public class CPUcodeCorrectTest {

    public static void main(String args[]) {

        ArrayList<Color> proposedSolution = new ArrayList<>();
        ArrayList<Color> solution = new ArrayList<>();
        CPUController cpuController = new CPUController();

        for(int i = 0; i < 4; ++i) {
            proposedSolution.add(Color.getRandomColor(4));
        }

        for(int i = 0; i < 4; ++i) {
            solution.add(Color.getRandomColor(4));
        }

        Code code = new Code(proposedSolution);
        Code sol = new Code(solution);
        Difficulty dif = Difficulty.MEDIUM;

        Action result = new CodeCorrect();

        //result = cpuController.codeCorrect(code, sol, dif);
    }
}
