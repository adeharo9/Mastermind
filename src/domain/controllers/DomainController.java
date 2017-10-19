package domain.controllers;

import domain.classes.Turn;
import domain.classes.Game;

public class DomainController
{
    private PlayerController player1;
    private PlayerController player2;

    public DomainController()
    {
        player1 = new HumanController();
        player2 = new CPUController();
    }

    public Game newGame()
    {
        return new Game();
    }

    public boolean endGame()
    {
        return true;
    }

    public Turn nextTurn()
    {
        return new Turn();
    }

    public boolean checkTurn(Turn turn)
    {
        return true;
    }
}
