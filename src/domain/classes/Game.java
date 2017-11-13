package domain.classes;

import enums.Difficulty;
import enums.Role;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Game implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private final String id;
    private long time;
    protected int points;

    private Board board;
    private ArrayList<Pair<Player, Role>> playerRolePairs;

    /* PRIVATE METHODS */

    private static boolean isValidId(final String id)
    {
        return !id.isEmpty();
    }

    private static boolean isValidTime(final long time)
    {
        return time >= 0 && time <= System.currentTimeMillis();
    }

    private static boolean isValidPlayerRolePair(final Pair<Player, Role> playerRolePair) throws NullPointerException
    {
        return playerRolePair.first.isValid() && playerRolePair.second != null;
    }

    private static boolean isValidPlayerRolePairs(final ArrayList<Pair<Player, Role>> playerRolePairs) throws NullPointerException
    {
        boolean b = true;

        for(Pair<Player, Role> playerRolePair : playerRolePairs)
        {
            b = isValidPlayerRolePair(playerRolePair);
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

    private void addPlayerRolePair(final Pair<Player, Role> playerRolePair) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPlayerRolePair(playerRolePair);
        if(!b) throw new IllegalArgumentException();

        if(playerRolePairs == null)
        {
            playerRolePairs = new ArrayList<>();
        }

        this.playerRolePairs.add(playerRolePair);
    }

    private void addPlayerRolePairByCopy(final Pair<Player, Role> playerRolePair) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPlayerRolePair(playerRolePair);
        if(!b) throw new IllegalArgumentException();

        if(playerRolePairs == null)
        {
            playerRolePairs = new ArrayList<>();
        }

        Pair<Player, Role> aux = new Pair<>(playerRolePair.first.deepCopy(), playerRolePair.second);
        this.playerRolePairs.add(aux);
    }

    /* CONSTRUCTION METHODS */

    @Deprecated
    public Game() throws IllegalArgumentException, NullPointerException
    {
        this.id = Utils.autoID();
        setTime();
        setPoints(Constants.POINTS_INIT);

        board = null;
        playerRolePairs = new ArrayList<>();
    }

    public Game(final Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        this.id = Utils.autoID();
        setTime();
        setPoints(Constants.POINTS_INIT);

        board = null;
        playerRolePairs = new ArrayList<>();

    }

    public Game(final String id, final Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        this.id = id;
        setTime();
        setPoints(Constants.POINTS_INIT);

        board = new Board(difficulty);
        playerRolePairs = new ArrayList<>();
    }

    public Game(final Game game) throws IllegalArgumentException, NullPointerException
    {
        id = game.getId();
        setTime(game.getTime());
        setBoardByCopy(game.getBoard());
        setPoints(game.getPoints());
        setPlayerRolePairsByCopy(game.getPlayerRolePairs());
    }

    /* SET METHODS */

    /*public void setId(String id) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidId(id);
        if(!b) throw new IllegalArgumentException();

        this.id = id;
    }*/

    public void setTime()
    {
        this.time = System.currentTimeMillis();
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

    public void setPlayerRolePairs(final ArrayList<Pair<Player, Role>> playerRolePairs) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPlayerRolePairs(playerRolePairs);
        if(!b) throw new IllegalArgumentException();

        this.playerRolePairs = new ArrayList<>(playerRolePairs.size());

        for (final Pair<Player, Role> playerRolePair : playerRolePairs) {
            addPlayerRolePair(playerRolePair);
        }
    }

    public void setPlayerRolePairsByCopy(final ArrayList<Pair<Player, Role>> playerRolePairs) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPlayerRolePairs(playerRolePairs);
        if(!b) throw new IllegalArgumentException();

        this.playerRolePairs = new ArrayList<>(playerRolePairs.size());

        for (final Pair<Player, Role> playerRolePair : playerRolePairs)
        {
            addPlayerRolePairByCopy(playerRolePair);
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

    public Board getBoard()
    {
        return board;
    }

    public ArrayList<Pair<Player, Role>> getPlayerRolePairs()
    {
        return playerRolePairs;
    }

    public Pair<Player, Role> getPlayerRolePair(final int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return playerRolePairs.get(i);
    }

    public Player getPlayer(final int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return playerRolePairs.get(i).first;
    }

    public Role getRole(final int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return playerRolePairs.get(i).second;
    }

    public int getPoints()
    {
        return points;
    }

    /* TESTING METHODS */

    public boolean isValid()
    {
        boolean b;

        b = isValidId(id);
        if(!b) return false;

        b = isValidTime(time);
        if(!b) return false;

        b = board != null;
        if(!b) return false;

        b = playerRolePairs != null;

        return b;
    }

    /* CLONING METHODS */

    public Game deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Game(this);
    }
}