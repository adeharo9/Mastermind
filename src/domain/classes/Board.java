package domain.classes;

import enums.Color;
import enums.Difficulty;
import util.Constants;
import util.DeepCopyable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Clase Tablero.
 *
 * Esta clase representa un tablero de Mastermind
 * con su clave, sus intentos y sus respectivas
 * correcciones.
 *
 * @author Alejandro de Haro
 */

public class Board implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private final int nColumns;
    private final int maxAttempts;
    private final Difficulty difficulty;

    private Code solution;
    private ArrayList<Turn> turnSet;
    private final Code solutionCorrection;

    /**
     * Validar columnas.
     *
     * Comprueba si el nombre de columnas es válido.
     *
     * @param nColumns Nombre de columnas a validar.
     * @return Devuelve true si  el número de columnas es válido.
     */
    private static boolean isValidNColumns(final int nColumns)
    {
        return nColumns > 0;
    }

    /**
     * Validar intentos.
     *
     * Comprueba si el nombre de máximos intentos es válido.
     *
     * @param maxAttempts Nombre de máximos intentos a validar.
     * @return Devuelve true si el número de máximos intentos es válido.
     */
    private static boolean isValidMaxAttempts(final int maxAttempts)
    {
        return maxAttempts > 0;
    }

    /**
     * Validar turnos.
     *
     * Comprueba si los turnos del tablero son válidos.
     *
     * @param turnSet Conjunto de turnos a validar.
     * @return Devuelve true si el conjunto de turnos es válido.
     * @throws NullPointerException Si alguno de los turnos es null.
     */
    private static boolean isValidTurnSet(final Collection<Turn> turnSet) throws NullPointerException
    {
        boolean b = turnSet.isEmpty();

        if(!b)
        {
            for(final Turn turn : turnSet)
            {
                b = turn.isValid();
                if(!b) break;
            }
        }

        return b;
    }

    /**
     * Validar código.
     *
     * Comprueba si el código de colores es válido.
     *
     * @param code Código de colores a validar.
     * @return Devuelve true si el código es válido.
     */
    private boolean isValidCode(final Code code) throws NullPointerException
    {
        return code.size () == nColumns;
    }

    /* CONSTRUCTION METHODS */

    /**
     * Constructor de tablero por dificultad.
     *
     * Instancia un tablero vacío pero válido
     * para empezar a jugar en él
     *
     * @param difficulty Dificultad del tablero
     * @throws IllegalArgumentException En caso que la dificultad no sea correcta
     */
    public Board(final Difficulty difficulty) throws IllegalArgumentException
    {
        this.nColumns = Constants.getNumPinsByDifficulty(difficulty);
        this.maxAttempts = Constants.getMaxRoundsByDifficulty(difficulty);
        this.difficulty = difficulty;
        //setNColumns(Constants.getNumPinsByDifficulty(difficulty));
        //setMaxAttempts(Constants.getMaxRoundsByDifficulty(difficulty));
        //setDifficulty(difficulty);

        solution = null;
        turnSet = new ArrayList<>();

        solutionCorrection = new Code(Collections.nCopies(Constants.getNumPinsByDifficulty(difficulty), Color.BLACK));
    }

    /**
     * Constructora de tablero por copia.
     *
     * Instancia un tablero copia de otro tablero dado.
     *
     * @param board Tablero del que se quiere hacer la copia.
     * @throws IllegalArgumentException En caso que alguno de los parámetros de board no sea correcto.
     */
    public Board(final Board board) throws IllegalArgumentException
    {
        this.nColumns = board.getNColumns();
        this.maxAttempts = board.getMaxAttempts();
        this.difficulty = board.getDifficulty();
        //setNColumns(board.getNColumns());
        //setMaxAttempts (board.getMaxAttempts());
        //setDifficulty(board.getDifficulty());
        setSolution(board.getSolution());
        setTurnSet(board.getTurnSet());

        solutionCorrection = new Code(Collections.nCopies(Constants.getNumPinsByDifficulty(difficulty), Color.BLACK));
    }

    /* SET METHODS */

    /**
     * Setter de código del tablero (solución a la partida)
     *
     * Indica el código solución de la partida actual que se está jugando
     *
     * @param solution Código solución
     * @throws IllegalArgumentException En caso de que el código no sea válido
     * @throws NullPointerException En caso de que solution sea nulo
     */
    public void setSolution(final Code solution) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidCode(solution);
        if(!b) throw new IllegalArgumentException();

        this.solution = solution.deepCopy();
    }

    /**
     * Setter de conjunto de turnos.
     *
     * Indica el conjunto de turnos del tablero jugados hasta el momento.
     * Ha de utilizarse conjuntamente a setSolution
     *
     * @param turnSet Conjunto de turnos de la partida
     * @throws IllegalArgumentException En caso de que el conjunto no sea válido
     * @throws NullPointerException En caso de que el conjunto sea nulo
     */
    public void setTurnSet(final Collection<Turn> turnSet) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidTurnSet(turnSet);
        if(!b) throw new IllegalArgumentException();

        this.turnSet = new ArrayList<>(turnSet.size());

        for(final Turn turn : turnSet)
        {
            addTurn(turn);
        }
    }

    /**
     * Añadir turno
     *
     * Añade un turno al conjunto de turnos del tablero
     *
     * @param turn Turno a añadir al tablero
     * @throws IllegalArgumentException En caso de que el turno no sea válido
     * @throws NullPointerException En caso de que el turno sea nulo
     */
    public void addTurn(final Turn turn) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidCode(turn);
        if(!b) throw new IllegalArgumentException();

        if(turnSet == null)
        {
            turnSet = new ArrayList<>();
        }

        turnSet.add(turn.deepCopy());
    }

    /**
     * Añadir turno sin corrección
     *
     * Añade un turno aun sin corrección, tan solo formado
     * por el código en sí
     *
     * @param code Código del turno sin corrección que añadir al tablero
     * @throws IllegalArgumentException En caso de que el código no sea válido
     */
    public void addCode(final Code code) throws IllegalArgumentException
    {
        boolean b = isValidCode(code);
        if(!b) throw new IllegalArgumentException();

        turnSet.add(new Turn(code));
    }

    /**
     * Añadir corrección
     *
     * Indica la corrección al último turno jugado, la tuviese ya o no
     *
     * @param code Código de corrección
     */
    public void addCorrection(final Code code)
    {
        boolean b = isValidCode(code);
        if(!b) throw new IllegalArgumentException();

        getLastTurn().setCorrection(code);
    }

    /* GET METHODS */

    /**
     * Getter de número de columnas del tablero.
     *
     * Devuelve el número de columnas (es decir, de colores por turno) que caben en el tablero.
     *
     * @return Número de columnas de pines por turno.
     */
    public int getNColumns()
    {
        return nColumns;
    }

    /**
     * Getter de número máximo de jugadas.
     *
     * Devuelve el número máximo de jugadas que se pueden hacer en una partida.
     *
     * @return Número máximo de jugadas de una partida.
     */
    public int getMaxAttempts ()
    {
        return maxAttempts;
    }

    /**
     * Getter de dificultad de tablero.
     *
     * Devuelve la dificultad del tablero actual.
     *
     * @return Dificultad del tablero.
     */
    public Difficulty getDifficulty()
    {
        return difficulty;
    }

    /**
     * Getter de código de solución.
     *
     * Devuelve el código solución del juego actual que se está llevando a cabo.
     *
     * @return Código de solución del tablero.
     */
    public Code getSolution()
    {
        return solution;
    }

    /**
     * Getter de conjunto de turnos del juego
     *
     * Devuelve una referencia a la lista de turnos o jugadas del juego actual
     *
     * @return Lista de turnos del tablero
     */
    public ArrayList<Turn> getTurnSet ()
    {
        return turnSet;
    }

    public Code getSolutionCorrection()
    {
        return solutionCorrection;
    }

    /**
     * Getter de turno de acceso aleatorio
     *
     * Devuelve el turno jugado en la jugaga i-ésima
     *
     * @param i Número de turno que se quiere consultar
     * @return Turno de la jugada i-ésima
     * @throws IndexOutOfBoundsException En caso que el índice no sea un número positivo
     * @throws NullPointerException En caso de que el conjunto de turnos no esté inicializado
     */
    public Turn getTurn(final int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return turnSet.get(i);
    }

    /**
     * Getter de último turno jugado
     *
     * Devuelve el último turno se que ha jugado (es decir, el último que se ha añadido al tablero)
     * en el estado en que se encuentre
     *
     * @return Último turno jugado
     */
    public Turn getLastTurn()
    {
        Turn turn;

        if(turnSet.isEmpty())
        {
            turn = null;
        }
        else
        {
            turn = getTurn(turnSet.size() - 1);
        }

        return turn;
    }

    /**
     * Getter de número de turno actual.
     *
     * Devuelve el número de turno que se está jugando actualmente.
     *
     * @return Número de turno actual.
     */
    public int getCurrentTurnNumber()
    {
        return turnSet.size();
    }

    /**
     * Consultora booleana de primer turno.
     *
     * Devuelve true si aun no se ha añadido ningún turno al conjunto de turnos (es decir, si se está
     * jugando el primer turno).
     *
     * @return Booleano indicando si se está jugando el primer turno
     */
    public boolean isFirstTurn()
    {
        return turnSet.isEmpty();
    }

    /* TESTING METHODS */

    /**
     * Consultora de validez de tablero
     *
     * @return Devuelve true si el tablero actual es válido; esto es: su número de filas y columnas es positivo y
     * mayor que cero, tiene una dificultad asociadad y está listo para jugarse en él.
     */
    public boolean isValid()
    {
        boolean b;

        b = isValidNColumns(nColumns);
        if(!b) return false;

        b = isValidMaxAttempts(maxAttempts);
        if(!b) return false;

        b = difficulty != null;
        if(!b) return false;

        b = turnSet != null;

        return b;
    }

    /* COPY METHODS */

    /**
     * Copia de objeto.
     *
     * Devuelve una copia profunda del objeto actual.
     *
     * @return Copia profunda del objeto actual.
     * @throws IllegalArgumentException En caso de que alguno de los valores de este tablero no sea válido.
     * @throws NullPointerException En caso de que alguno de los campos de este tablero no esté inicializado.
     */
    @Override
    public Board deepCopy () throws IllegalArgumentException, NullPointerException
    {
        return new Board (this);
    }
}
