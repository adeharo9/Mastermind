package domain.classes;

import enums.Color;
import enums.Difficulty;
import util.Constants;
import util.DeepCopyable;

import java.io.Serializable;
import java.util.*;

/**
 * Clase CPU.
 *
 * Esta clase representa la CPU como un posible jugador de la partida.
 *
 * @author Alex, Rafael, Alejandro de Haro
 */
public class CPU extends Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private Code currentGuess;
    private HashSet<Code> solutions = new HashSet<>();
    private HashSet<Code> guesses = new HashSet<>();

    /* CONSTRUCTION METHODS */

    /**
     * Constructora vacía.
     *
     * Instancia un jugador de tipo cpu vacío.
     */
    public CPU()
    {
        super();
    }

    /**
     * Constructora por id.
     *
     * Instancia un jugador de tipo cpu con el identificador dado.
     * @param id Identificador de la cpu.
     */
    public CPU(final String id) throws IllegalArgumentException
    {
        super(id);
    }

    /**
     * Constructora por copia.
     *
     * Instancia un jugador de tipo cpu por copia
     * a partir de otro dado.
     * @param cpu Jugador a copiar.
     */
    public CPU(final CPU cpu) throws IllegalArgumentException, NullPointerException
    {
        super(cpu);
    }

    /* SET METHODS */

    /* GET METHODS */

    /* TESTING METHODS */

    /* CLONING METHODS */


    @Override
    public CPU deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new CPU(this);
    }

    /* PLAYING METHODS */

    /**
     * Acción como code maker.
     *
     * Permite al jugador de tipo cpu realizar una acción como code maker.
     *
     * @param difficulty Dificultad de la partida.
     */
    @Override
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

    /**
     * Acción como code breaker.
     *
     * Permite al jugador de tipo cpu realizar una acción como code breaker.
     *
     * @param difficulty Dificultad de la partida.
     * @param lastTurn Último turno de la partida.
     * @param isFirstTurn Indica si se trata del primer turno.
     */
    @Override
    public Action codeBreak(final Difficulty difficulty, final Turn lastTurn, final boolean isFirstTurn)
    {
        Code proposedSolutionByCPU = getCodeBreak(difficulty, lastTurn, isFirstTurn);
        return new CodeBreak(proposedSolutionByCPU);
    }

    /**
     * Acción como code correct.
     *
     * Permite al jugador de tipo corregir la acción de otro jugador.
     *
     * @param difficulty Dificultad de la partida.
     * @param code Código introducido a corregir.
     * @param solution Código de colores que representa la solución.
     */
    @Override
    public Action codeCorrect(final Difficulty difficulty, final Code code, final Code solution)
    {
        Code correction = getCodeCorrect(code, solution);
        return new CodeCorrect(correction);
    }

    /**
     * Código para jugar.
     *
     * Devuelve el código de colores propuesto por
     * el algoritmo para intentar batir al code maker.
     *
     * @param difficulty Dificultad de la partida.
     * @param lastTurn Último turno de la partida.
     * @param isFirstTurn Indica si es el primer turno.
     * @return Código de colores del algoritmo.
     */
    private Code getCodeBreak(final Difficulty difficulty, final Turn lastTurn, final boolean isFirstTurn) throws IllegalArgumentException
    {
        int coincidences;
        int minCoincidences;
        int maxCoincidences;
        Code correction;
        HashMap<Code, Integer> coincidencesByCorrection = new HashMap<>();
        HashMap<Code, Integer> maxNotEliminatedByGuess = new HashMap<>();

        if(isFirstTurn)
        {
            switch (difficulty)
            {
                case EASY:
                    generateCombinations(difficulty);
                    break;
                case MEDIUM:
                case HARD:
                    generatePermutations(difficulty);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
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

                correction = getCodeCorrect(solution, currentGuess);

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
                    correction = getCodeCorrect(solution, guess);

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

    /**
     * Permutaciones de colores.
     *
     * Realiza las diferentes permutaciones de colores posibles.
     *
     * @param currDepth Límite actual de la permutación.
     * @param maxDepth Límite máximo de la permutación.
     * @param colorCollection Colores posibles.
     * @param aux Lista auxiliar de colores.
     */
    private void permute(final int currDepth, final int maxDepth, final Collection<Color> colorCollection, List<Color> aux)
    {
        if(currDepth >= maxDepth)
        {
            final Code code = new Code(aux);
            solutions.add(code);
            guesses.add(code);
        }
        else
        {
            for(final Color color : colorCollection)
            {
                aux.add(color);
                permute(currDepth + 1, maxDepth, colorCollection, aux);
                aux.remove(aux.size() - 1);
            }
        }
    }

    /**
     * Permutaciones de colores.
     *
     * Realiza las diferentes permutaciones de colores posibles en función de la dificultad.
     *
     * @param difficulty Dificultad de la partida.
     */
    protected void generatePermutations(final Difficulty difficulty)
    {
        int numPins = Constants.getNumPinsByDifficulty(difficulty);
        Collection<Color> colorCollection = Color.getValues(difficulty);

        permute(0, numPins, colorCollection, new ArrayList<>(numPins));
    }

    /**
     * Combinaciones.
     *
     * Obtiene las diferentes permutaciones posibles pero sin repeticiones.
     *
     * @param currDepth Límite actual de la permutación.
     * @param maxDepth Límite máximo de la permutación.
     * @param colorCollection Colores posibles.
     * @param aux Lista auxiliar de colores.
     * @param visited Lista de colores ya visitados.
     */
    private void combine(final int currDepth, final int maxDepth, final Collection<Color> colorCollection, ArrayList<Color> aux, final Map<Color, Boolean> visited)
    {
        if(currDepth >= maxDepth)
        {
            final Code code = new Code(aux);
            solutions.add(code);
            guesses.add(code);
        }
        else
        {
            for(final Color color : colorCollection)
            {
                if(!visited.get(color))
                {
                    visited.put(color, Boolean.TRUE);
                    aux.add(color);

                    combine(currDepth + 1, maxDepth, colorCollection, aux, visited);

                    aux.remove(aux.size() - 1);
                    visited.put(color, Boolean.FALSE);
                }
            }
        }
    }

    /**
     * Combinaciones.
     *
     * Obtiene las diferentes permutaciones posibles pero sin repeticiones.
     *
     * @param difficulty Dificultad de la partida.
     */
    protected void generateCombinations(final Difficulty difficulty)
    {
        int numPins = Constants.getNumPinsByDifficulty(difficulty);
        Collection<Color> colorCollection = Color.getValues(difficulty);
        Map<Color, Boolean> visited = new HashMap<>(colorCollection.size());

        for(final Color color : colorCollection)
        {
            visited.put(color, Boolean.FALSE);
        }

        combine(0, numPins, colorCollection, new ArrayList<>(numPins), visited);
    }

    /**
     * Acción como code correct.
     *
     * Obtiene la corrección del código de colores introducidos.
     *
     * @param code Código introducido a corregir.
     * @param solution Código de colores que representa la solución.
     * @return Corrección del código de colores.
     */
    @SuppressWarnings("Duplicates")
    private Code getCodeCorrect(final Code code, final Code solution)
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

    /**
     * Primer código de colores.
     *
     * Obtiene el primer código de colores a partir
     * del cual se desarrollará el algoritmo.
     *
     * @param difficulty Dificultad de la partida.
     * @return Primera jugada del jugador CPU.
     * @throws IllegalArgumentException Si el objeto esperado no se corresponde con el parámetro enviado.
     */
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
                colorSet.remove(color);
                first = true;
            }

            colorList.add(color);
        }

        return new Code(colorList);
    }

    /* TESTING FUNCTIONS*/
    /**
     * Getter de solutions.
     *
     * Devuelve el HashSet que contiene las posibles
     * soluciones de la partida.
     *
     * @return solutions.
     */
    protected HashSet<Code> getSolutions()
    {
        return solutions;
    }
}