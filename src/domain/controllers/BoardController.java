package domain.controllers;

import domain.classes.Action;
import domain.classes.Board;
import domain.classes.Code;
import domain.classes.Turn;
import enums.Difficulty;
import exceptions.IllegalActionException;

import java.util.List;

/**
 * BoardController.
 *
 * @author Alejandro de Haro
 */

public class BoardController
{
    /* ATTRIBUTES */

    private Board board;

    /* PRIVATE METHODS */

    /* CONSTRUCTION METHODS */

    public BoardController()
    {
        //this.board = new Board();
    }

    /* INITIALIZATION METHODS */

    /**
     * Constructor de controlador de tablero
     *
     * Crea el controlador de tablero con
     * un tablero de dificultad especificada
     *
     * @param difficulty Dificultad del tablero creado
     * @return Tablero.
     * @throws IllegalArgumentException si la dificultad introducida no es valida.
     * @throws NullPointerException si la dificultad es nula.
     */

    public Board newBoard(Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        this.board = new Board(difficulty);

        return this.board;
    }

    /* SET METHODS */

    /**
     * Setter de tablero
     *
     * El valor del parametro board del controlador
     * pasa a ser el del parametro introducido.
     *
     * @param board Tablero
     * @exception IllegalArgumentException si el tablero introducido no es valido.
     */

    public void setBoardByReference(Board board) throws IllegalArgumentException
    {
        boolean b = board.isValid();
        if(!b) throw new IllegalArgumentException();

        this.board = board;
    }

    /* GET METHODS */

    /**
     * Getter del tablero.
     *
     * Devuelve el tablero cargado en el  controlador.
     *
     * @return tablero asociado al controlador
     */

    public Board getBoard()
    {
        return board;
    }

    /**
     * Getter de la dificultad del tablero.
     *
     * Devuelve la dificultad del tablero
     * cargado en el controlador.
     *
     * @return dificultad del tablero asociado al controlador
     */

    public Difficulty getDifficulty()
    {
        return board.getDifficulty();
    }

    /**
     * Getter del codigo solucion del tablero.
     *
     * Devuelve el codigo solucion del tablero
     * cargado en el controlador.
     *
     * @return codigo solucion del tablero asociado al controlador.
     */

    public Code getSolution()
    {
        return board.getSolution();
    }

    /**
     * Getter de turnos del tablero.
     *
     * Devuelve la lista de turnos del tablero
     * cargado en el controlador.
     *
     * @return lista de turnos del tablero asociado al controlador.
     */

    public List<Turn> getTurnSet()
    {
        return board.getTurnSet();
    }

    /**
     * Getter del ultimo turno del tablero.
     *
     * Devuelve el ultimo turno del tablero
     * cargado en el controlador.
     *
     * @return ultimo turno del tablero asociado al controlador.
     */

    public Turn getLastTurn()
    {
        return board.getLastTurn();
    }

    /**
     * Primer turno.
     *
     * Informa de si se esta jugando el primer
     * turno en el tablero cargado en el controlador
     *
     * @return true si se juega el primer turno, false en caso contrario
     */

    public boolean isFirstTurn()
    {
        return board.isFirstTurn();
    }

    /**
     * Codigo para corregir.
     *
     * Informa de si el ultimo turno del tablero
     * cargado en el contralor tiene codigo corregido
     *
     * @return true en caso de tener codigo corregido, false en caso contrario
     */

    public boolean hasCodeToCorrect()
    {
        Turn lastTurn = getLastTurn();

        return lastTurn != null && lastTurn.getCorrectionCode().size() == 0;
    }

    /* VALIDATION METHODS */

    /**
     * Comprobar accion
     *
     * Comproeba que la accion pasada como
     * parametro es correcta.
     *
     * @param action Accion
     * @exception IllegalActionException La acción no es valida
     */

    public void checkAction(Action action) throws IllegalActionException
    {
        Code code;

        if(getLastTurn() == null)
        {
            code = null;
        }
        else
        {
            code = getLastTurn().getCode();
        }

        action.checkAction(getDifficulty(), code, getSolution());
    }

    /**
     * Añadir accion.
     *
     * Se añade la accion pasada por parametro
     * al tablero asociado al controlador.
     *
     * @param action Accion
     */

    public void addAction(Action action)
    {
        action.addSelfToBoard(board);
    }
}
