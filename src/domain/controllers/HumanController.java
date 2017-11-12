package domain.controllers;

import domain.classes.*;
import enums.Color;
import enums.Difficulty;
import exceptions.ReservedKeywordException;

import java.util.List;

public class HumanController extends PlayerController
{
    /* CONSTRUCTION METHODS */
    private final DomainController domainController;

    public HumanController(DomainController domainController)
    {
        this.domainController = domainController;
        player = new Human();
    }

    public HumanController(DomainController domainController, Player player)
    {
        super((Human) player);
        this.domainController = domainController;
    }

    /* INSTANTIATION METHODS */

    public Player newPlayer(String id)
    {
        player = new Human(id);
        return player;
    }

    public Player newPlayer(String id, String password)
    {
        player = new Human(id, password);
        return player;
    }

    /* OTHER METHODS */

    protected Action codeMake(Difficulty difficulty) throws ReservedKeywordException
    {
        List<Color> colorList = domainController.codeInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeMake(code);
    }

    protected Action codeBreak(Difficulty difficulty, Turn lastTurn) throws ReservedKeywordException
    {
        List<Color> colorList = domainController.codeInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeBreak(code);
    }

    protected Action codeCorrect(Code c, Code s, Difficulty difficulty) throws ReservedKeywordException
    {
        List<Color> colorList = domainController.correctionInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeCorrect(code);
    }

    public boolean checkPassword(String password)
    {
        return ((Human) player).checkPassword(password);
    }
}
