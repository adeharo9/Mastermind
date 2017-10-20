package domain.controllers;

import domain.classes.Player;
import domain.classes.Turn;
import domain.classes.Game;
import persistence.BoardPersistence;
import persistence.GamePersistence;
import persistence.PlayerPersistence;
import persistence.RankingPersistence;

import java.util.ArrayList;

public class DomainController
{
    private BoardController boardController;
    private ArrayList<PlayerController> playerControllers;

    private BoardPersistence boardPersistence;
    private GamePersistence gamePersistence;
    private PlayerPersistence playerPersistence;
    private RankingPersistence rankingPersistence;

    public DomainController()
    {
        playerControllers = new ArrayList<>();
        boardController = new BoardController();

        boardPersistence = new BoardPersistence();
        gamePersistence = new GamePersistence();
        playerPersistence = new PlayerPersistence();
        rankingPersistence = new RankingPersistence();
    }

    /* EXECUTE */

    public boolean registerUser(String username, String password)
    {
        return true;
    }

    public boolean logIn(String username, String password)
    {
        PlayerController playerController = new HumanController(username, password);
        return true;
    }

    public void newGame(int gameMode, int difficulty)
    {
        // Initialize Game, Board, Player structures
    }

    public void loadGame(String id)
    {
        // Load persistence data structures
        //gamePersistence.load(id);
    }

    public boolean saveGame()
    {
        return true;
    }

    /*public boolean endGame()
    {
        return true;
    }*/

    public void playTurn()
    {

    }

    public boolean checkTurn(Turn turn)
    {
        return true;
    }
}
