package domain.controllers;

import domain.classes.*;
import enums.Difficulty;
import enums.Color;
import java.util.*;

public class CPUController extends PlayerController
{
    /* CONSTRUCTION METHODS */

    public CPUController()
    {
        player = new CPU();
    }

    public CPUController(Player player)
    {
        super((CPU) player);
    }

    /* INSTANTIATION METHODS */

    public Player newPlayer(String id)
    {
        player = new CPU(id);
        return player;
    }

    /* OTHER METHODS */

    protected Action codeMake()
    {

        return null;
    }

    protected Action codeBreak()
    {
        return null;
    }

    protected Action codeCorrect(Combination combination, Code solution, Difficulty difficulty)
    {
        ArrayList<Color> pins = new ArrayList<>();
        ArrayList<Color> proposedSolution = new ArrayList<>();
        ArrayList<Color> sol = new ArrayList<>();

        proposedSolution = combination.getBPins();
        sol = solution.getBPins();
        int size = combination.size();
        boolean find;

        for(int i = 0; i < size; ++i) {
            if(proposedSolution.get(i) == sol.get(i)) pins.add(Color.BLACK);
            else{
                find = false;
                for(Color col : sol) {
                    if(col == proposedSolution.get(i)) {
                        pins.add(Color.WHITE);
                        find = true;
                        break;
                    }
                }
                if(!find) pins.add(Color.NONE);
            }
        }
        Combination feedback = new Combination(pins);
        Action result = new CodeCorrect(feedback);

        return result;
    }
}
