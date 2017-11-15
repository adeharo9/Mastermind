package domain.classes;

import enums.Color;
import enums.Difficulty;
import util.Constants;
import util.DeepCopyable;

import java.io.Serializable;
import java.util.*;

public class CPU extends Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private Code currentGuess;
    private HashSet<Code> solutions = new HashSet<>();
    private HashSet<Code> guesses = new HashSet<>();

    /* CONSTRUCTION METHODS */

    public CPU()
    {
        super();
    }

    public CPU(final String id) throws IllegalArgumentException
    {
        super(id);
    }

    public CPU(final CPU cpu) throws IllegalArgumentException, NullPointerException
    {
        super(cpu);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */

    public CPU deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CPU(this);
    }

    /* PLAYING METHODS */

    public Action codeMake(final Difficulty difficulty)
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

    public Action codeBreak(final Difficulty difficulty, final Turn lastTurn, final boolean isFirstTurn)
    {
        Code proposedSolutionByCPU = getCodeBreak(difficulty, lastTurn, isFirstTurn);
        return new CodeBreak(proposedSolutionByCPU);
    }

    public Action codeCorrect(final Difficulty difficulty, final Code code, final Code solution)
    {
        Code correction = getCodeCorrect(difficulty, code, solution);
        return new CodeCorrect(correction);
    }

    private Code getCodeBreak(final Difficulty difficulty, final Turn lastTurn, final boolean isFirstTurn)
    {
        int coincidences;
        int minCoincidences;
        int maxCoincidences;
        Code correction;
        HashMap<Code, Integer> coincidencesByCorrection = new HashMap<>();
        HashMap<Code, Integer> maxNotEliminatedByGuess = new HashMap<>();

        if(isFirstTurn)
        {
            generatePermutations(difficulty);
            currentGuess = getInitialGuess(difficulty);

            guesses.remove(currentGuess);
        }
        else
        {
            Code lastCorrection = new Code(lastTurn.getCorrectionPins());
            solutions.remove(currentGuess);

            for(final Iterator<Code> codeIterator = solutions.iterator(); codeIterator.hasNext();)
            {
                Code solution = codeIterator.next();

                correction = getCodeCorrect(difficulty, solution, currentGuess);

                if(!correction.unorderedEquals(lastCorrection))
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
                    correction = getCodeCorrect(difficulty, solution, guess);

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

    private <T extends Collection<Color>> void permute(final int currDepth, final int maxDepth, final T elementSet, ArrayList<Color> aux)
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

    protected void generatePermutations(final Difficulty difficulty)
    {
        int numPins = Constants.getNumPinsByDifficulty(difficulty);
        Collection<Color> colorCollection = Color.getValues(difficulty);

        permute(0, numPins, colorCollection, new ArrayList<>());
    }

    private Code getCodeCorrect(final Difficulty difficulty, final Code code, final Code solution)
    {
        int size = code.size();

        ArrayList<Boolean> match = new ArrayList<>(Collections.nCopies(size, Boolean.FALSE));

        ArrayList<Boolean> processed = new ArrayList<>(Collections.nCopies(size, Boolean.FALSE));

        List<Color> pins = new ArrayList<>(size);
        final List<Color> playerProposedSolution = code.getCodePins();
        final List<Color> sol = solution.getCodePins();

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

        for(int i = pins.size(); i < size; ++i)
        {
            pins.add(Color.NONE);
        }

        return new Code(pins);
    }

    private Code getInitialGuess(Difficulty difficulty) throws IllegalArgumentException
    {
        Set<Color> colorSet = Color.getValues(difficulty);
        int numPins = Constants.getNumPinsByDifficulty(difficulty);
        boolean repetitionPolicy = Constants.getRepetitionPolicyByDifficulty(difficulty);
        boolean first = true;

        List<Color> colorList = new ArrayList<>(numPins);
        Color lastColor = null;

        for(int i = 0; i < numPins; ++i)
        {
            Color color = Color.getRandomColor(colorSet);

            if(!repetitionPolicy)
            {
                colorSet.remove(color);
            }
            else if(first)
            {
                lastColor = color;
                first = false;
            }
            else
            {
                color = lastColor;
                first = true;
            }

            colorList.add(color);
        }

        return new Code(colorList);
    }

    /* TESTING FUNCTIONS*/

    protected HashSet<Code> getSolutions()
    {
        return solutions;
    }
}