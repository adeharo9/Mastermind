package domain.classes;

import enums.Difficulty;
import enums.Mode;
import enums.Role;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Game implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private final String id;
    private long time;
    private int points;
    private Mode mode;

    private Board board;
    private List<Player> players;

    /* PRIVATE METHODS */

    private static boolean isValidId(final String id)
    {
        return !id.isEmpty();
    }

    private static boolean isValidTime(final long time)
    {
        return time >= 0 && time <= System.currentTimeMillis();
    }

    @Deprecated
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

    private void setTime(final long time) throws IllegalArgumentException
    {
        boolean b = isValidTime(time);
        if(!b) throw new IllegalArgumentException();

        this.time = time;
    }

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

    @Deprecated
    public Game() throws IllegalArgumentException, NullPointerException
    {
        this.id = Utils.autoID();
        setTime();
        setPoints(Constants.POINTS_INIT);
        mode = null;

        board = null;
        players = new ArrayList<>();
    }

    @Deprecated
    public Game(final Difficulty difficulty, final Mode mode) throws IllegalArgumentException, NullPointerException
    {
        this.id = Utils.autoID();
        setTime();
        setPoints(Constants.POINTS_INIT);
        setMode(mode);

        board = null;
        players = new ArrayList<>();

    }

    public Game(final String id, final Difficulty difficulty, final Mode mode) throws IllegalArgumentException, NullPointerException
    {
        this.id = id;
        setTime();
        setPoints(Constants.POINTS_INIT);
        setMode(mode);

        board = new Board(difficulty);
        players = new ArrayList<>();
    }

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

    public void setTime()
    {
        this.time = System.currentTimeMillis();
    }

    public void setMode(final Mode mode) throws IllegalArgumentException
    {
        boolean b = mode != null;
        if(!b) throw new IllegalArgumentException();

        this.mode = mode;
    }

    public void setBoard(final Board board) throws IllegalArgumentException, NullPointerException
    {
        boolean b = board.isValid();
        if(!b) throw new IllegalArgumentException();

        this.board = board;
    }

    public void setBoardByCopy(final Board board) throws IllegalArgumentException, NullPointerException
    {
        boolean b = board.isValid();
        if(!b) throw new IllegalArgumentException();

        this.board = board.deepCopy();
    }

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

    public void setPoints(final int points)
    {
        this.points = points;
    }

    /* GET METHODS */

    public String getId()
    {
        return id;
    }

    public long getTime()
    {
        return time;
    }

    public Mode getMode()
    {
        return mode;
    }

    public Board getBoard()
    {
        return board;
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    public Player getPlayer(final int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return players.get(i);
    }

    public Role getRole(final int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return players.get(i).getRole();
    }

    public int getPoints()
    {
        return points;
    }

    public boolean hasStarted() throws NullPointerException
    {
        return board.getSolution() != null;
    }

    public boolean hasFinished()
    {
        //return turnSet.size() == maxAttempts || (!turnSet.isEmpty() && getLastTurn().getCorrectionCode().equals(solutionCorrection));
        return board.getTurnSet().size() == board.getMaxAttempts() || (!board.getTurnSet().isEmpty() && board.getLastTurn().getCode().orderedEquals(board.getSolution()));
    }

    /* TESTING METHODS */

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

    public Game deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Game(this);
    }
}