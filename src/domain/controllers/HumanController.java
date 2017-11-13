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

    @Deprecated
    public HumanController(final DomainController domainController)
    {
        this.domainController = domainController;
        player = new Human();
    }

    public HumanController(final DomainController domainController, final Player player)
    {
        super(player);
        this.domainController = domainController;
    }

    /* INSTANTIATION METHODS */

    public Player newPlayer(final String id)
    {
        player = new Human(id);
        return player;
    }

    public Player newPlayer(final String id, final String password)
    {
        player = new Human(id, password);
        return player;
    }

    /* OTHER METHODS */

    protected Action codeMake(final Difficulty difficulty) throws ReservedKeywordException
    {
        List<Color> colorList = domainController.codeInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeMake(code);
    }

    protected Action codeBreak(final Difficulty difficulty, final Turn lastTurn) throws ReservedKeywordException
    {
        List<Color> colorList = domainController.codeInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeBreak(code);
    }

    protected Action codeCorrect(final Difficulty difficulty, final Code c, final Code s) throws ReservedKeywordException
    {
        List<Color> colorList = domainController.correctionInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeCorrect(code);
    }

    public boolean checkPassword(final String password)
    {
        return ((Human) player).checkPassword(password);
    }
}
