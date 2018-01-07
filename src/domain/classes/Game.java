package domain.classes;

import enums.Difficulty;
import enums.Mode;
import util.Constants;
import util.DeepCopyable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Game.
 *
 * Esta clase representa un contenedor de los parámetros básicos de la partida.
 *
 * @author Rafael Ramírez
 */
public class Game implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private String id;
    private long time;
    private int points;
    private Mode mode;

    private Board board;
    private List<Player> players;

    /* PRIVATE METHODS */

    /**
     * Validar id.
     *
     * Comprueba que el identificador de la partida no está vacío.
     *
     * @param id Identificador de partida.
     * @return Devuelve true si el id de la partida es válido.
     */
    private static boolean isValidId(final String id)
    {
        return !id.isEmpty();
    }

    /**
     * Validar tiempo.
     *
     * Comprueba que el tiempo de la partida es válido.
     *
     * @param time Tiempo.
     * @return Devuelve true si el tiempo de la partida es válido.
     */
    private static boolean isValidTime(final long time)
    {
        return time >= 0 && time <= System.currentTimeMillis();
    }

    /**
     * Validar jugadores.
     *
     * Comprueba que los diferentes jugadores son válidos.
     *
     * @param players Lista de jugadores.
     * @return Devuelve true si los jugadores son válidos.
     * @throws NullPointerException Si alguno de los jugadores están vacíos.
     */
    private static boolean isValidPlayers(final List<Player> players) throws NullPointerException
    {
        boolean b = true;

        for(final Player player : players)
        {
            b = player.isValid();
            if(!b) break;
        }

        return b;
    }

    /**
     * Setter del tiempo.
     *
     * Indica el tiempo de la partida.
     *
     * @param time Duración partida.
     * @throws IllegalArgumentException Si el tiempo no es válido.
     */
    private void setTime(final long time) throws IllegalArgumentException
    {
        boolean b = isValidTime(time);
        if(!b) throw new IllegalArgumentException();

        this.time = time;
    }

    /**
     * Añadir jugador.
     *
     * Añade un jugador a la lista de jugadores
     * que conforman la partida.
     *
     * @param player Jugador a añadir.
     * @throws IllegalArgumentException Si el jugador no es válido.
     * @throws NullPointerException Si el jugador está vacío.
     */
    private void addPlayer(final Player player) throws IllegalArgumentException, NullPointerException
    {
        boolean b = player.isValid();
        if(!b) throw new IllegalArgumentException();

        if(players == null)
        {
            players = new ArrayList<>();
        }

        this.players.add(player);
    }

    /**
     * Añadir jugador por copia.
     *
     * Añade la copia de un jugador a la lista de jugadores
     * que conforman la partida.
     *
     * @param player Jugador a añadir.
     * @throws IllegalArgumentException Si el jugador no es válido.
     * @throws NullPointerException Si el jugador está vacío.
     */
    private void addPlayerByCopy(final Player player) throws IllegalArgumentException, NullPointerException
    {
        boolean b = player.isValid();
        if(!b) throw new IllegalArgumentException();

        if(players == null)
        {
            players = new ArrayList<>();
        }

        this.players.add(player.deepCopy());
    }

    /* CONSTRUCTION METHODS */

    /**
     * Constructora por id, dificultad y modo.
     *
     * Instancia una partida con el identificador,
     * dificultad y modo dados.
     *
     * @param id Identificador de la partida.
     * @param difficulty Dificultad de la partida.
     * @param mode Modo de juego.
     * @throws IllegalArgumentException Si el modo o el id no es válido.
     * @throws NullPointerException Si el modo es null.
     */
    public Game(final String id, final Difficulty difficulty, final Mode mode) throws IllegalArgumentException, NullPointerException
    {
        this.id = id;
        setTime();
        setPoints(Constants.POINTS_INIT);
        setMode(mode);

        board = new Board(difficulty);
        players = new ArrayList<>();
    }

    /**
     * Constructora por copia.
     *
     * Instancia una partida por copia
     * a partir de otra dada.
     *
     * @param game Partida a copiar.
     * @throws IllegalArgumentException Si el modo no es válido.
     * @throws NullPointerException Si el modo es null.
     */
    public Game(final Game game) throws IllegalArgumentException, NullPointerException
    {
        id = game.getId();
        setTime(game.getTime());
        setPoints(game.getPoints());
        setMode(game.getMode());

        setBoardByCopy(game.getBoard());
        setPlayersByCopy(game.getPlayers());
    }

    /* SET METHODS */

    /**
     * Setter id.
     *
     * Indica el identificador de la partida.
     *
     * @param id Identificador partida.
     */
    public void setId(String id) { this.id = id; }

    /**
     * Setter del tiempo.
     *
     * Indica el tiempo de la partida.
     *
     */
    public void setTime()
    {
        this.time = System.currentTimeMillis();
    }

    /**
     * Setter modo de juego.
     *
     * Indica el modo de juego de la partida.
     *
     * @param mode Modo de juego.
     * @throws IllegalArgumentException Si el modo de juego no es válido.
     */
    public void setMode(final Mode mode) throws IllegalArgumentException
    {
        boolean b = mode != null;
        if(!b) throw new IllegalArgumentException();

        this.mode = mode;
    }

    /**
     * Setter del tablero.
     *
     * Indica el tablero de la partida.
     *
     * @param board Tablero de la partida.
     * @throws IllegalArgumentException Si el tablero no es válido.
     * @throws NullPointerException Si el tablero está vacío.
     */
    public void setBoard(final Board board) throws IllegalArgumentException, NullPointerException
    {
        boolean b = board.isValid();
        if(!b) throw new IllegalArgumentException();

        this.board = board;
    }

    /**
     * Setter del tablero por copia.
     *
     * Indica el tablero de la partida y establece una copia del mismo.
     *
     * @param board Tablero de la partida.
     * @throws IllegalArgumentException Si el tablero no es válido.
     * @throws NullPointerException Si el tablero está vacío.
     */
    public void setBoardByCopy(final Board board) throws IllegalArgumentException, NullPointerException
    {
        boolean b = board.isValid();
        if(!b) throw new IllegalArgumentException();

        this.board = board.deepCopy();
    }

    /**
     * Setter de los jugadores.
     *
     * Indica los jugadores de la partida.
     *
     * @param players Jugadores de la partida.
     * @throws IllegalArgumentException Si alguno de los jugadores no es válido.
     * @throws NullPointerException Si alguno de los jugadores está vacío.
     */
    public void setPlayers(final List<Player> players) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPlayers(players);
        if(!b) throw new IllegalArgumentException();

        this.players = new ArrayList<>(players.size());

        for (final Player player : players)
        {
            addPlayer(player);
        }
    }

    /**
     * Setter de los jugadores por copia.
     *
     * Indica los jugadores de la partida.
     *
     * @param players Jugadores de la partida.
     * @throws IllegalArgumentException Si alguno de los jugadores no es válido.
     * @throws NullPointerException Si alguno de los jugadores está vacío.
     */
    public void setPlayersByCopy(final List<Player> players) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPlayers(players);
        if(!b) throw new IllegalArgumentException();

        this.players = new ArrayList<>(players.size());

        for (final Player player : players)
        {
            addPlayerByCopy(player);
        }
    }

    /**
     * Setter de los puntos.
     *
     * Indica la puntuación aconseguida en la partida.
     *
     * @param points Puntuación.
     */
    public void setPoints(final int points)
    {
        this.points = points;
    }

    /* GET METHODS */

    /**
     * Getter id.
     *
     * Devuelve el identificador de la partida.
     *
     * @return String id.
     */
    public String getId()
    {
        return id;
    }

    /**
     * Getter time.
     *
     * Devuelve la duración de la partida.
     *
     * @return Long time.
     */
    public long getTime()
    {
        return time;
    }

    /**
     * Getter modo.
     *
     * Devuelve el modo de juego de la partida.
     *
     * @return Mode mode.
     */
    public Mode getMode()
    {
        return mode;
    }

    /**
     * Getter tablero.
     *
     * Devuelve el tablero de la partida.
     *
     * @return Board board.
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * Getter jugadores.
     *
     * Devuelve los jugadores de la partida.
     *
     * @return players.
     */
    public List<Player> getPlayers()
    {
        return players;
    }

    /**
     * Getter puntos.
     *
     * Devuelve la puntuación de la partida del jugador correspondiente.
     *
     * @return int points.
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * Juego empezado?.
     *
     * Devuelve cierto si la partida ya ha arrancado.
     *
     * @throws NullPointerException Si el tablero está vacío.
     * @return si la partida ya ha arrancado.
     */
    public boolean hasStarted() throws NullPointerException
    {
        return board.getSolution() != null;
    }

    /**
     * Juego acabado?.
     *
     * Devuelve cierto si la partida ya ha finalizado.
     * @return si la partida ya ha finalizado.
     */
    public boolean hasFinished()
    {
        return board.getTurnSet().size() == board.getMaxAttempts() || (!board.getTurnSet().isEmpty() && board.getLastTurn().getCorrectionCode().orderedEquals(board.getSolutionCorrection()));
        //return board.getTurnSet().size() == board.getMaxAttempts() || (!board.getTurnSet().isEmpty() && board.getLastTurn().getCode().orderedEquals(board.getSolution()));
    }

    /* TESTING METHODS */

    /**
     * Juego válido?.
     *
     * Comprueba si la partida es válida
     * devolviendo cierto si cada uno de los
     * elementos que la conforman también son válidos.
     *
     * @return si la partida es válida.
     */
    public boolean isValid()
    {
        boolean b;

        b = isValidId(id);
        if(!b) return false;

        b = isValidTime(time);
        if(!b) return false;

        b = mode != null;
        if(!b) return false;

        b = board != null;
        if(!b) return false;

        b = players != null;

        return b;
    }

    /* CLONING METHODS */

    /**
     * Copiar partida.
     *
     * Hace copia profunda de la partida.
     *
     * @return una copia de la partida.
     * @throws IllegalArgumentException En caso que alguno de los elementos de la partida no sean válidos.
     * @throws NullPointerException En caso que alguno de los elementos sean nulos.
     */
    @Override
    public Game deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Game(this);
    }
}