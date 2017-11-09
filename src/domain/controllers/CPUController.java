package domain.controllers;

import domain.classes.*;
import enums.Difficulty;
import enums.Color;

import java.lang.reflect.Array;
import java.util.*;

public class CPUController extends PlayerController
{
    /* CONSTRUCTION METHODS */

    private Code currentGuess;
    private HashSet<Code> solutions;
    private HashSet<Code> guesses;

    public CPUController()
    {
        solutions = new HashSet<>();
        guesses = new HashSet<>();

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
        Color color = null;
        ArrayList<Color> code = new ArrayList<>();

        switch(difficulty) {

            case EASY:

                HashSet<Color> possibleColors = new HashSet<>(Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE));
                boolean firstTime = true;

                for(int i = 0; i < 4; ++i)
                {
                    if(firstTime)
                    {
                        firstTime = false;
                    }

                    else
                    {
                        possibleColors.remove(color);
                    }

                    color = Color.getRandomColor(possibleColors);
                    code.add(color);
                }
                break;

            case MEDIUM:

                for(int i = 0; i < 4; ++i)
                {
                    color = Color.getRandomColor(6);
                    code.add(color);
                }
                break;

            case HARD:
                for(int i = 0; i < 6; ++i)
                {
                    color = Color.getRandomColor(8);
                    code.add(color);
                }
                break;
        }

        Code solution = new Code(code);
        return new CodeMake(solution);
    }

    protected Code getCodeBreak(Difficulty difficulty, Turn lastTurn)
    {
        int minCoincidences;
        int maxCoincidences;
        Code correction;
        HashMap<Code, Integer> coincidencesByCorrection = new HashMap<>();
        HashMap<Code, Integer> maxNotEliminatedByGuess = new HashMap<>();

        if(firstTurn)
        {
            generatePermutations(difficulty);
            Collection<Color> colors = new ArrayList<>(Arrays.asList(Color.RED, Color.RED, Color.GREEN, Color.GREEN));
            currentGuess = new Code(colors);

            guesses.remove(currentGuess);
        }
        else
        {
            Code lastCorrection = new Code(lastTurn.getSPins());
            solutions.remove(currentGuess);

            for(Code solution : solutions)
            {
                correction = getCodeCorrect(solution, currentGuess, difficulty);

                if(!correction.equivalents(lastCorrection))
                {
                    solutions.remove(solution);     // WARNING: COMPROBAR QUE NO HACE LO MISMO QUE C++ CON LOS RANGE-BASED LOOPS
                }
            }

            for(Code guess : guesses)
            {
                coincidencesByCorrection.clear();

                for (Code solution : solutions) {
                    correction = getCodeCorrect(solution, guess, difficulty);

                    if (coincidencesByCorrection.containsKey(correction))
                    {
                        coincidencesByCorrection.put(correction, coincidencesByCorrection.get(correction) + 1);
                    }
                    else
                    {
                        coincidencesByCorrection.put(correction, 1);
                    }
                }

                maxCoincidences = 0;

                for(Map.Entry<Code, Integer> entry : coincidencesByCorrection.entrySet())
                {
                    if(entry.getValue() > maxCoincidences)
                    {
                        maxCoincidences = entry.getValue();
                    }
                }

                maxNotEliminatedByGuess.put(guess, maxCoincidences);
            }

            minCoincidences = Integer.MAX_VALUE;

            for(Map.Entry<Code, Integer> entry : maxNotEliminatedByGuess.entrySet())
            {
                if(entry.getValue() < minCoincidences)
                {
                    minCoincidences = entry.getValue();
                    currentGuess = entry.getKey();
                }
            }

            guesses.remove(currentGuess);
        }

        return currentGuess;
    }

    protected Action codeBreak(Difficulty difficulty, Turn lastTurn)
    {
        Code proposedSolutionByCPU = getCodeBreak(difficulty, lastTurn);
        return new CodeBreak(proposedSolutionByCPU);
    }

    private <T extends Collection<Color>> void permute(int currDepth, final int maxDepth, final T elementSet, ArrayList<Color> aux)
    {
        if(currDepth >= maxDepth)
        {
            Code code = new Code(aux);
            solutions.add(code);
        }
        else
        {
            for(Color element : elementSet)
            {
                aux.add(element);
                permute(currDepth + 1, maxDepth, elementSet, aux);
                aux.remove(element);
            }
        }
    }

    protected void generatePermutations(Difficulty difficulty)
    {
        Collection<Color> colorCollection = Color.getValues(difficulty);
        permute(0, 4, colorCollection, new ArrayList<>());
    }

    protected Code getCodeCorrect(Code code, Code solution, Difficulty difficulty)
    {
        int size = code.size();

        ArrayList<Boolean> match = new ArrayList<Boolean>(size);
        Collections.fill(match, Boolean.FALSE);

        ArrayList<Boolean> processed = new ArrayList<Boolean>(size);
        Collections.fill(processed, Boolean.FALSE);

        ArrayList<Color> pins = new ArrayList<>(size);
        ArrayList<Color> playerProposedSolution = new ArrayList<>(size);
        ArrayList<Color> sol = new ArrayList<>(size);

        playerProposedSolution = code.getBPins();
        sol = solution.getBPins();

        for(int i = 0; i < size; ++i)
        {
            if(playerProposedSolution.get(i) == sol.get(i))
            {
                match.add(i, true);
                processed.add(i, true);
                pins.add(Color.BLACK);
            }
        }

        for(int i = 0; i < size; ++i)
        {
            if(!match.get(i))
            {
                for(int j = 0; j < size; ++j)
                {
                    if(!processed.get(i) && playerProposedSolution.get(i) == sol.get(j))
                    {
                        match.add(i, true);
                        processed.add(j, true);
                        pins.add(Color.WHITE);
                        break;
                    }
                }
            }
        }

        if(difficulty != Difficulty.EASY)
        {
            Collections.shuffle(pins);
        }

        return new Code(pins);
    }

    protected Action codeCorrect(Code code, Code solution, Difficulty difficulty)
    {
        Code correction = getCodeCorrect(code, solution, difficulty);
        return new CodeCorrect(correction);
    }

    /* TESTING FUNCTIONS*/

    protected HashSet<Code> getSolutions()
    {
        return solutions;
    }
}
