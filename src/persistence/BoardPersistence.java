package persistence;

import domain.classes.Board;

public class BoardPersistence extends AbstractPersistence
{
    public BoardPersistence()
    {

    }

    public Board load(Object key)
    {
        return new Board();
    }

    private boolean saveBoard(Board board)
    {
        return true;
    }

    public boolean save(Object board)
    {
        return saveBoard((Board) board);
    }
}
