package domain.controllers;

import domain.classes.*;
import enums.Difficulty;
import enums.Color;
import util.Constants;

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
        super(player);
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
        Color color;
        ArrayList<Color> code = new ArrayList<>();

        Set<Color> possibleColors = Color.getValues(difficulty);

        int numPins = Constants.getNumPinsByDifficulty(difficulty);
        int numColors = Constants.getNumColorsByDifficulty(difficulty);


        for(int i = 0; i < numPins; ++i)
        {
            if(difficulty == Difficulty.EASY)
            {
                color = Color.getRandomColor(possibleColors);
                possibleColors.remove(color);
            }
            else
            {
                color = Color.getRandomColor(numColors);
            }

            code.add(color);
        }

        Code solution = new Code(code);
        return new CodeMake(solution);
    }

    protected Code getCodeBreak(Difficulty difficulty, Turn lastTurn)
    {
        int coincidences;
        int minCoincidences;
        int maxCoincidences;
        Code correction;
        HashMap<Code, Integer> coincidencesByCorrection = new HashMap<>();
        HashMap<Code, Integer> maxNotEliminatedByGuess = new HashMap<>();

        if(firstTurn)
        {
            generatePermutations(difficulty);
            List<Color> colors = new ArrayList<>(Arrays.asList(Color.RED, Color.RED, Color.GREEN, Color.GREEN));
            currentGuess = new Code(colors);

            guesses.remove(currentGuess);
        }
        else
        {
            Code lastCorrection = new Code(lastTurn.getCorrection());
            solutions.remove(currentGuess);

            for(Iterator<Code> codeIterator = solutions.iterator(); codeIterator.hasNext();)
            {
                Code solution = codeIterator.next();

                correction = getCodeCorrect(solution, currentGuess, difficulty);

                if(!correction.equals(lastCorrection))
                {
                    codeIterator.remove();
                }
            }

            maxCoincidences = 0;
            minCoincidences = Integer.MAX_VALUE;

            for(final Code guess : guesses)
            {
                coincidencesByCorrection.clear();

                for (final Code solution : solutions)
                {
                    correction = getCodeCorrect(solution, guess, difficulty);

                    if (coincidencesByCorrection.containsKey(correction))
                    {
                        coincidences = coincidencesByCorrection.get(correction) + 1;
                    }
                    else
                    {
                        coincidences = 1;
                    }

                    coincidencesByCorrection.put(correction, coincidences);

                    if(coincidences > maxCoincidences)
                    {
                        maxCoincidences = coincidences;
                    }
                }

                /*maxCoincidences = 0;

                for(final Map.Entry<Code, Integer> entry : coincidencesByCorrection.entrySet())
                {
                    if(entry.getValue() > maxCoincidences)
                    {
                        maxCoincidences = entry.getValue();
                    }
                }*/

                maxNotEliminatedByGuess.put(guess, maxCoincidences);

                if(maxCoincidences < minCoincidences)
                {
                    minCoincidences = maxCoincidences;
                }
            }

            boolean firstTime = true;

            for(final Map.Entry<Code, Integer> entry : maxNotEliminatedByGuess.entrySet())
            {
                if(entry.getValue() == minCoincidences)
                {
                    if(solutions.contains(entry.getKey()))
                    {
                        currentGuess = entry.getKey();
                        break;
                    }
                    else if(firstTime)
                    {
                        currentGuess = entry.getKey();
                        firstTime = false;
                    }
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
            final Code code = new Code(aux);
            solutions.add(code);
            guesses.add(code);
        }
        else
        {
            for(final Color element : elementSet)
            {
                aux.add(element);
                permute(currDepth + 1, maxDepth, elementSet, aux);
                aux.remove(aux.size() - 1);
            }
        }
    }

    protected void generatePermutations(Difficulty difficulty)
    {
        int numPins = Constants.getNumPinsByDifficulty(difficulty);
        Collection<Color> colorCollection = Color.getValues(difficulty);

        permute(0, numPins, colorCollection, new ArrayList<>());
    }

    protected Code getCodeCorrect(final Code code, final Code solution, final Difficulty difficulty)
    {
        int size = code.size();

        ArrayList<Boolean> match = new ArrayList<>(Collections.nCopies(size, Boolean.FALSE));

        ArrayList<Boolean> processed = new ArrayList<>(Collections.nCopies(size, Boolean.FALSE));

        List<Color> pins = new ArrayList<>(size);
        final List<Color> playerProposedSolution = code.getPins();
        final List<Color> sol = solution.getPins();

        for(int i = 0; i < size; ++i)
        {
            if(playerProposedSolution.get(i) == sol.get(i))
            {
                match.set(i, true);
                processed.set(i, true);
                pins.add(Color.BLACK);
            }
        }

        for(int i = 0; i < size; ++i)
        {
            if(!match.get(i))
            {
                for(int j = 0; j < size; ++j)
                {
                    if(!processed.get(j) && playerProposedSolution.get(i) == sol.get(j))
                    {
                        match.set(i, true);
                        processed.set(j, true);
                        pins.add(Color.WHITE);
                        break;
                    }
                }
            }
        }

        /*if(difficulty != Difficulty.EASY)
        {
            Collections.shuffle(pins);
        }*/

        for(int i = pins.size(); i < size; ++i)
        {
            pins.add(Color.NONE);
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
