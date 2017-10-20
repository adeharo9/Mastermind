package domain.controllers;

import domain.classes.Player;
import domain.classes.Turn;
import domain.classes.Game;
import persistence.GamePersistence;

public class DomainController
{
    private PlayerController player1;
    private PlayerController player2;
    private GamePersistence gamePersistence;

    public DomainController()
    {
        player1 = new HumanController();
        player2 = new CPUController();
        gamePersistence = new GamePersistence();
    }

    /* EXECUTE */

    public boolean registerUser(String username, String password)
    {
        return true;
    }

    public boolean logIn(String username, String password)
    {
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
