package domain.classes;

import util.*;

import java.io.Serializable;
import java.util.*;

public class Game implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private String id;
    private Difficulty difficulty;
    private long time;

    private Board board;
    private ArrayList<Pair<Player, Role>> playerRolePairs;

    /* PRIVATE METHODS */

    @Deprecated
    private static boolean isValidId(String id)
    {
        return true;
    }

    private static boolean isValidTime(long time)
    {
        return time >= 0 && time <= System.currentTimeMillis();
    }

    private static boolean isValidPlayerRolePair(Pair<Player, Role> playerRolePair) throws NullPointerException
    {
        return playerRolePair.first.isValid();
    }

    private static boolean isValidPlayerRolePairs(ArrayList<Pair<Player, Role>> playerRolePairs) throws NullPointerException
    {
        boolean b = true;

        for(Pair<Player, Role> playerRolePair : playerRolePairs)
        {
            b = !playerRolePair.hasNull();
            if(!b) break;

            b = playerRolePair.first.isValid();
            if(!b) break;
        }

        return b;
    }

    /* CONSTRUCTION METHODS */

    public Game()
    {
        id = "-1";
        difficulty = null;
        time = -1;

        board = null;
        playerRolePairs = new ArrayList<>();
    }

    public Game(String id, Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        setId(id);
        setDifficulty(difficulty);
        setTime();
    }

    public Game(Game game) throws IllegalArgumentException, NullPointerException
    {
        setId(game.getId());
        setDifficulty(game.getDifficulty());
        setTime(game.getTime());
        setBoard(game.getBoard());
        setPlayerRolePairs(game.getPlayerRolePairs());
    }

    /* SET METHODS */

    public void setId(String id) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidId(id);
        if(!b) throw new IllegalArgumentException();

        this.id = id;
    }

    public void setDifficulty(Difficulty difficulty) throws IllegalArgumentException, NullPointerException
    {
        boolean b = difficulty != null;
        if(!b) throw new IllegalArgumentException();

        this.difficulty = difficulty;
    }

    public void setTime()
    {
        this.time = System.currentTimeMillis();
    }

    public void setTime(long time) throws IllegalArgumentException
    {
        boolean b = isValidTime(time);
        if(!b) throw new IllegalArgumentException();

        this.time = time;
    }

    public void setBoard(Board board) throws IllegalArgumentException, NullPointerException
    {
        boolean b = board.isValid();
        if(!b) throw new IllegalArgumentException();

        this.board = board.deepCopy();
    }

    public void setBoardByReference(Board board) throws IllegalArgumentException, NullPointerException
    {
        boolean b = board.isValid();
        if(!b) throw new IllegalArgumentException();

        this.board = board;
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

    public void setPlayerRolePairsByReference(ArrayList<Pair<Player, Role>> playerRolePairs) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPlayerRolePairs(playerRolePairs);
        if(!b) throw new IllegalArgumentException();

        this.playerRolePairs = new ArrayList<>(playerRolePairs.size());

        for (Pair<Player, Role> playerRolePair : playerRolePairs) {
            addPlayerRolePairByReference(playerRolePair);
        }
    }

    public void addPlayerRolePair(Pair<Player, Role> playerRolePair) throws IllegalArgumentException, NullPointerException
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

    public void addPlayerRolePairByReference(Pair<Player, Role> playerRolePair) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPlayerRolePair(playerRolePair);
        if(!b) throw new IllegalArgumentException();

        if(playerRolePairs == null)
        {
            playerRolePairs = new ArrayList<>();
        }

        this.playerRolePairs.add(playerRolePair);
    }

    /* GET METHODS */

    public String getId()
    {
        return id;
    }

    public Difficulty getDifficulty()
    {
        return difficulty;
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

    public Player getPlayer(int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return playerRolePairs.get(i).first;
    }

    public Role getRole(int i) throws IndexOutOfBoundsException, NullPointerException
    {
        return playerRolePairs.get(i).second;
    }

    /* TESTING METHODS */

    public boolean isValid()
    {
        boolean b;

        b = isValidId(id);
        if(!b) return b;

        b = difficulty != null;
        if(!b) return b;

        b = isValidTime(time);
        if(!b) return b;

        b = board != null;
        if(!b) return b;

        b = playerRolePairs != null;

        return b;
    }

    /* CLONING METHODS */

    public Game deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Game(this);
    }
}