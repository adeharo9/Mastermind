package domain.classes;

import util.*;

import java.lang.reflect.Array;
import java.util.*;

public class Game implements DeepCopyable
{
    /* ATTRIBUTES */

    private int id;
    private Difficulty difficulty;
    private long time;

    private Board board;
    private ArrayList<Pair<Player, Role>> playersAndRoles;

    /* CONSTRUCTION METHODS */

    public Game()
    {
        playersAndRoles = new ArrayList<>();
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

            b = setPlayersAndRoles(game.getPlayersAndRoles());
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
        boolean b = id >= 0;

        if(b)
        {
            this.id = id;
        }

        return b;
    }

    public boolean setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;

        return true;
    }

    public boolean setTime()
    {
        this.time = System.currentTimeMillis();

        return true;
    }

    public boolean setTime(long time)
    {
        boolean b = time > 0 && time <= System.currentTimeMillis();

        if(b)
        {
            this.time = time;
        }

        return b;
    }

    public boolean setBoard(Board board)
    {
        this.board = board.deepCopy();

        return true;
    }

    public boolean setPlayersAndRoles(ArrayList<Pair<Player, Role>> playersAndRoles)
    {
        boolean b = true;

        this.playersAndRoles = new ArrayList<>(playersAndRoles.size());

        for(Pair<Player, Role> playerAndRole : playersAndRoles)
        {
            b &= addPlayerAndRole(playerAndRole);
        }

        return b;
    }

    public boolean addPlayerAndRole(Pair<Player, Role> playerAndRole)
    {
        boolean b = playerAndRole != null && playerAndRole.first != null && playerAndRole.second != null;

        if(b)
        {
            Pair<Player, Role> aux = new Pair<>(playerAndRole.first.deepCopy(), playerAndRole.second);
            this.playersAndRoles.add(aux);
        }

        return b;
    }

    public boolean relatePlayerAndRole(Pair<Player, Role> playerAndRole)
    {
        boolean b = playerAndRole != null && playerAndRole.first != null && playerAndRole.second != null;

        if(b)
        {
            Pair<Player, Role> aux = new Pair<>(playerAndRole.first.deepCopy(), playerAndRole.second);
            this.playersAndRoles.add(aux);
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

    public ArrayList<Pair<Player, Role>> getPlayersAndRoles()
    {
        return playersAndRoles;
    }

    public Player getPlayer(int i)
    {
        return playersAndRoles.get(i).first;
    }

    public Role getRole(int i)
    {
        return playersAndRoles.get(i).second;
    }

    /* TESTING METHODS */

    /* CLONING METHODS */

    public Game deepCopy()
    {
        return new Game(this);
    }
}