package persistence;

import domain.classes.Board;

public class BoardPersistence extends AbstractPersistence
{
    public BoardPersistence()
    {

    }

    public Board load()
    {
        return new Board();
    }

    public boolean save(Object board)
    {
        return true;
    }
}
