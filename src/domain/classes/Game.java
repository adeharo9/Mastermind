package domain.classes;

import util.*;

import java.util.*;

public class Game implements DeepCopyable
{
    /* ATTRIBUTES */

    private int id;
    private Difficulty difficulty;
    private long time;

    private Board board;
    private ArrayList<Pair<Player, Role>> playerRolePairs;

    /* PRIVATE METHODS */

    private static boolean isValidId(int id)
    {
        return id >= 0;
    }

    private static boolean isValidTime(long time)
    {
        return time >= 0 && time <= System.currentTimeMillis();
    }

    /*private boolean isValidPlayersAndRoles()
    {
        boolean b;

        b = playerRolePairs != null && !playerRolePairs.isEmpty();

        if(b)
        {
            for(Pair<Player, Role> playerRolePair : playerRolePairs)
            {
                b = !playerRolePair.hasNull();
                if(!b) return b;

                b = playerRolePair.first.isValid();
                if(!b) return b;

                b = Role.isValid(playerRolePair.second);
                if(!b) return b;
            }
        }

        return b;
    }*/

    /* CONSTRUCTION METHODS */

    public Game()
    {
        id = -1;
        time = -1;
        playerRolePairs = new ArrayList<>();
    }

    public Game(Game game)
    {
        boolean b;
        try
        {
            b = setId(game.getId());
            if(!b) throw new Exception("");

            b = setDifficulty(game.getDifficulty());
            if(!b) throw new Exception("");

            b = setTime(game.getTime());
            if(!b) throw new Exception("");

            b = setBoard(game.getBoard());
            if(!b) throw new Exception("");

            b = setPlayerRolePairs(game.getPlayerRolePairs());
            if(!b) throw new Exception("");
        }
        catch(Exception e)
        {
            ioUtils.printErrLn(e.getMessage());
        }
    }

    /* SET METHODS */

    public boolean setId(int id)
    {
        boolean b = isValidId(id);

        if(b)
        {
            this.id = id;
        }

        return b;
    }

    public boolean setDifficulty(Difficulty difficulty)
    {
        boolean b = Difficulty.isValid(difficulty);

        if(b)
        {
            this.difficulty = difficulty;
        }

        return b;
    }

    public boolean setTime()
    {
        this.time = System.currentTimeMillis();

        return true;
    }

    public boolean setTime(long time)
    {
        boolean b = isValidTime(time);

        if(b)
        {
            this.time = time;
        }

        return b;
    }

    public boolean setBoard(Board board)
    {
        boolean b = board.isValid();

        if(b)
        {
            this.board = board.deepCopy();
        }

        return b;
    }

    public boolean setPlayerRolePairs(ArrayList<Pair<Player, Role>> playerRolePairs)
    {
        boolean b = playerRolePairs != null;

        if(b)
        {
            this.playerRolePairs = new ArrayList<>(playerRolePairs.size());

            for (Pair<Player, Role> playerRolePair : playerRolePairs) {
                b &= addPlayerRolePair(playerRolePair);
            }
        }

        return b;
    }

    public boolean addPlayerRolePair(Pair<Player, Role> playerRolePair)
    {
        boolean b = playerRolePair != null && !playerRolePair.hasNull() && playerRolePair.first.isValid();

        if(b)
        {
            Pair<Player, Role> aux = new Pair<>(playerRolePair.first.deepCopy(), playerRolePair.second);
            this.playerRolePairs.add(aux);
        }

        return b;
    }

    public boolean addPlayerRolePairNoCopy(Pair<Player, Role> playerRolePair)
    {
        boolean b = playerRolePair != null && !playerRolePair.hasNull() && playerRolePair.first.isValid();

        if(b)
        {
            this.playerRolePairs.add(playerRolePair);
        }

        return b;
    }

    /* GET METHODS */

    public int getId()
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

    public Player getPlayer(int i)
    {
        return playerRolePairs.get(i).first;
    }

    public Role getRole(int i)
    {
        return playerRolePairs.get(i).second;
    }

    /* TESTING METHODS */

    public boolean isValid()
    {
        boolean b;

        b = isValidId(id);
        if(!b) return b;
        b = Difficulty.isValid(difficulty);
        if(!b) return b;
        b = isValidTime(time);
        if(!b) return b;
        b = board != null;
        if(!b) return b;
        b = playerRolePairs != null;

        return b;
    }

    /* CLONING METHODS */

    public Game deepCopy()
    {
        return new Game(this);
    }
}