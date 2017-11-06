package domain.classes;

import enums.Difficulty;
import enums.Role;
import util.*;

import java.io.Serializable;
import java.util.*;

public class Game implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private String id;
    private long time;
    private Difficulty difficulty;
    protected int points;

    private Board board;
    private ArrayList<Pair<Player, Role>> playerRolePairs;

    /* PRIVATE METHODS */

    private static boolean isValidId(String id)
    {
        return !id.isEmpty();
    }

    private static boolean isValidTime(long time)
    {
        return time >= 0 && time <= System.currentTimeMillis();
    }

    private static boolean isValidPlayerRolePair(Pair<Player, Role> playerRolePair) throws NullPointerException
    {
        return playerRolePair.first.isValid() && playerRolePair.second != null;
    }

    private static boolean isValidPlayerRolePairs(ArrayList<Pair<Player, Role>> playerRolePairs) throws NullPointerException
    {
        boolean b = true;

        for(Pair<Player, Role> playerRolePair : playerRolePairs)
        {
            b = isValidPlayerRolePair(playerRolePair);
            if(!b) break;
        }

        return b;
    }

    private void setTime(long time) throws IllegalArgumentException
    {
        boolean b = isValidTime(time);
        if(!b) throw new IllegalArgumentException();

        this.time = time;
    }

    private void addPlayerRolePair(Pair<Player, Role> playerRolePair) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPlayerRolePair(playerRolePair);
        if(!b) throw new IllegalArgumentException();

        if(playerRolePairs == null)
        {
            playerRolePairs = new ArrayList<>();
        }

        this.playerRolePairs.add(playerRolePair);
    }

    private void addPlayerRolePairByCopy(Pair<Player, Role> playerRolePair) throws IllegalArgumentException, NullPointerException
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
        setId(Utils.autoID());
        setTime();
        difficulty = null;
        setPoints(0);

        board = null;
        playerRolePairs = new ArrayList<>();
    }

    public Game(Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        setId(Utils.autoID());
        setTime();
        setDifficulty(difficulty);
        setPoints(0);

        board = null;
        playerRolePairs = new ArrayList<>();

    }

    public Game(String id, Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        setId(id);
        setTime();
        setDifficulty(difficulty);
        setPoints(0);

        board = null;
        playerRolePairs = new ArrayList<>();
    }

    public Game(Game game) throws IllegalArgumentException, NullPointerException
    {
        setId(game.getId());
        setTime(game.getTime());
        setDifficulty(game.getDifficulty());
        setBoardByCopy(game.getBoard());
        setPoints(game.getPoints());
        setPlayerRolePairsByCopy(game.getPlayerRolePairs());
    }

    /* SET METHODS */

    public void setId(String id) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidId(id);
        if(!b) throw new IllegalArgumentException();

        this.id = id;
    }

    public void setTime()
    {
        this.time = System.currentTimeMillis();
    }

    public void setDifficulty(Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        boolean b = difficulty != null;
        if(!b) throw new IllegalArgumentException();

        this.difficulty = difficulty;
    }

    public void setBoard(Board board) throws IllegalArgumentException, NullPointerException
    {
        boolean b = board.isValid();
        if(!b) throw new IllegalArgumentException();

        this.board = board;
    }

    public void setBoardByCopy(Board board) throws IllegalArgumentException, NullPointerException
    {
        boolean b = board.isValid();
        if(!b) throw new IllegalArgumentException();

        this.board = board.deepCopy();
    }

    public void setPlayerRolePairs(ArrayList<Pair<Player, Role>> playerRolePairs) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPlayerRolePairs(playerRolePairs);
        if(!b) throw new IllegalArgumentException();

        this.playerRolePairs = new ArrayList<>(playerRolePairs.size());

        for (Pair<Player, Role> playerRolePair : playerRolePairs) {
            addPlayerRolePair(playerRolePair);
        }
    }

    public void setPlayerRolePairsByCopy(ArrayList<Pair<Player, Role>> playerRolePairs) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPlayerRolePairs(playerRolePairs);
        if(!b) throw new IllegalArgumentException();

        this.playerRolePairs = new ArrayList<>(playerRolePairs.size());

        for (Pair<Player, Role> playerRolePair : playerRolePairs)
        {
            addPlayerRolePairByCopy(playerRolePair);
        }
    }

    public void setPoints(int points)
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

    public Difficulty getDifficulty()
    {
        return difficulty;
    }

    public Board getBoard()
    {
        return board;
    }

    public ArrayList<Pair<Player, Role>> getPlayerRolePairs()
    {
        return playerRolePairs;
    }

    public Pair<Player, Role> getPlayerRolePair(int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return playerRolePairs.get(i);
    }

    public Player getPlayer(int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return playerRolePairs.get(i).first;
    }

    public Role getRole(int i) throws IndexOutOfBoundsException, NullPointerException
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

        b = difficulty != null;
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