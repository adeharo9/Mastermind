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

    protected Action codeMake(Difficulty difficulty)
    {
        ArrayList<Color> code = new ArrayList<>();

        int i = 0;
        switch(difficulty) {
            case EASY:
                ArrayList<Color> usedColors = new ArrayList<>();

                while(i < 4) {
                    Color color = Color.getRandomColor(3); //o 4
                    usedColors.add(color);

                    if(!repeatedColor(usedColors, color)) {
                        code.add(color);
                        ++i;
                    }
                }
                break;

            case MEDIUM:
                while(i < 6) {
                    Color color = Color.getRandomColor(5); //o 6
                    code.add(color);
                    ++i;
                }
                break;

            case HARD:
                while(i < 8) {
                    Color color = Color.getRandomColor(7); //o 8
                    code.add(color);
                    ++i;
                }
                break;
        }
        Combination solution = new Combination(code);
        return new CodeMake(solution);
    }

    private boolean repeatedColor(ArrayList<Color> usedColors, Color color) {
        for(Color col : usedColors) {
            if(col == color) return true;
        }
        usedColors.remove(usedColors.size()-1);
        return false;
    }

    protected Action codeBreak()
    {
        return null;
    }

    protected Action codeCorrect(Combination combination, Code solution, Difficulty difficulty)
    {
        int size = combination.size();
        ArrayList<Color> pins = new ArrayList<>(size);
        ArrayList<Color> proposedSolution = new ArrayList<>(size);
        ArrayList<Color> sol = new ArrayList<>(size);

        proposedSolution = combination.getBPins();
        sol = solution.getBPins();
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

        if(difficulty != Difficulty.EASY) {
            Collections.shuffle(pins);
        }

        Combination feedback = new Combination(pins);
        return new CodeCorrect(feedback);
    }
}
