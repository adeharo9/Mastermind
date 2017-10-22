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

    private static boolean isValidPlayerRolePair(Pair<Player, Role> playerRolePair)
    {
        return Pair.isValid(playerRolePair)&& playerRolePair.first.isValid();
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
        difficulty = null;
        time = -1;
        board = null;
        playerRolePairs = null;
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
                b = addPlayerRolePair(playerRolePair);
                if(!b) return b;
            }
        }

        return b;
    }

    public boolean addPlayerRolePair(Pair<Player, Role> playerRolePair)
    {
        boolean b = isValidPlayerRolePair(playerRolePair);

        if(b)
        {
            if(playerRolePairs == null)
            {
                playerRolePairs = new ArrayList<>();
            }

            Pair<Player, Role> aux = new Pair<>(playerRolePair.first.deepCopy(), playerRolePair.second);
            this.playerRolePairs.add(aux);
        }

        return b;
    }

    public boolean addPlayerRolePairNoCopy(Pair<Player, Role> playerRolePair)
    {
        boolean b = isValidPlayerRolePair(playerRolePair);

        if(b)
        {
            if(playerRolePairs == null)
            {
                playerRolePairs = new ArrayList<>();
            }

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
        Player player = null;
        boolean b = Utils.isValidIndex(playerRolePairs, i);

        if(b)
        {
            player = playerRolePairs.get(i).first;
        }

        return player;
    }

    public Role getRole(int i)
    {
        Role role = null;
        boolean b = Utils.isValidIndex(playerRolePairs, i);

        if(b)
        {
            role = playerRolePairs.get(i).second;
        }

        return role;
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