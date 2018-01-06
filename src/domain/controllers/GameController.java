package domain.controllers;

import domain.classes.Board;
import domain.classes.Game;
import domain.classes.Player;
import domain.classes.Turn;
import enums.Color;
import enums.Difficulty;
import enums.Mode;
import util.Constants;

import java.util.List;

/**
 * GameController.
 *
 * @author Alejandro de Haro, Rafael
 */

public class GameController
{
    private Game game;

    /**
     * Constructor del controlador de partida
     *
     * Crea el controlador de partida con una
     * partida vacia.
     */
    public GameController()
    {
        game = null;
    }

    /**
     * Setter de partida nueva.
     *
     * Crea una partida nueva segun los parametros
     * introducidos y lo guarda dentro del parametro
     * game del controlador.
     *
     * @param id Nombre de la partida.
     * @param difficulty Dificultad de la partida.
     * @param mode Modo de la partida.
     * @param board Tablero en el que se jugara la partida.
     * @param players Jugadores que jugaran la partida.
     */

    public void newGame(String id, Difficulty difficulty, Mode mode, Board board, List<Player> players)
    {
        game = new Game(id, difficulty, mode);

        game.setBoard(board);
        game.setPlayers(players);
    }

    /**
     * Setter de partida existente.
     *
     * Se guarda en el parametro game del
     * controlador una partida ya creada.
     *
     * @param game Partida que se guardara en el controlador de partida.
     * @throws IllegalArgumentException La partida que entra como parametro no es valida.
     */

    public void setGameByReference(Game game) throws IllegalArgumentException
    {
        boolean b = game.isValid();
        if(!b) throw new IllegalArgumentException();

        this.game = game;
    }

    /**
     * Getter de la partida.
     *
     * Devuelve la partida cargada en el controlador.
     *
     * @return partida asociada al controlador
     */

    public Game getGame()
    {
        return game;
    }

    /**
     * Getter del nombre de la partida.
     *
     * Devuelve el nombre de la partida
     * cargada en el controlador.
     *
     * @return nombre de la partida asociada al controlador
     */

    public String getId()
    {
        return game.getId();
    }

    /**
     * Getter de los puntos de la partida
     *
     * Devuelve los puntos de la partida
     * cargada en el controlador.
     *
     * @return Puntos de la partida asociada al controlador
     */

    public int getPoints()
    {
        return game.getPoints();
    }

    /**
     * Getter del modo de la partida.
     *
     * Devuelve el modo de la partida
     * cargada en el controlador.
     *
     * @return modo de la partida asociada al controlador.
     */

    public Mode getMode()
    {
        return game.getMode();
    }

    /**
     * Partida comenzada
     *
     * Informa de si la partida cargada en el controlador
     * ha comenzado o no
     *
     * @return true si la partida ha comenzado, false en caso contrario
     */

    public boolean hasStarted()
    {
        return game.hasStarted();
    }

    /**
     * Partida acabada
     *
     * Informa de si la partida cargada en el controlador
     * ha terminado o no
     *
     * @return true si la partida ha terminado, false en caso contrario
     */

    public boolean hasFinished()
    {
        return game.hasFinished();
    }

    /**
     * Calculo de puntos de turno
     *
     * Cuando cambia el valor de turno en la
     * partida que hay cargada en el controlador,
     * hace un calculo de los puntos ganados o
     * perdidos en ese turno.
     */

    public void pointsEndTurn()
    {
        int newPoints = game.getPoints();
        int modify = game.getBoard().getCurrentTurnNumber();
        newPoints = newPoints + modify * Constants.POINTS_ROUND;
        Turn lastTurn = game.getBoard().getLastTurn();
        for (int i = 0; i < lastTurn.getCorrectionPins().size(); ++i)
        {
            Color c = lastTurn.getCorrectionPinAt(i);
            switch (c)
            {
                case BLACK:
                    newPoints = newPoints + Constants.POINTS_BLACK;
                    break;
                case WHITE:
                    newPoints = newPoints + Constants.POINTS_WHITE;
                    break;
                case NONE:
                    break;
            }
        }
        game.setPoints(newPoints);
    }

    /*public void pointsEndGame()
    {
        int newpoints = game.getPoints();
        long modify = game.getTime();
        newpoints = newpoints + ((int)System.currentTimeMillis()-(int)modify) * Constants.POINTS_TIME;
        if (newpoints<0) newpoints = 0;
        game.setPoints(newpoints);
    }*/

    /**
     * Calculo de puntos por pista
     *
     * Resta puntos en la partida cargada
     * en el controlador por el uso de
     * la funcion de pedir pista.
     */

    public void pointsClue()
    {
        int newpoints = game.getPoints() + Constants.POINTS_CLUE;
        game.setPoints(newpoints);
    }
}
