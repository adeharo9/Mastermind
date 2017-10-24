package persistence;

import domain.classes.Board;
import exceptions.FileDoesNotExist;
import exceptions.IntegrityCorruption;

public class BoardPersistence extends AbstractPersistence
{
    public BoardPersistence()
    {

    }

    public boolean exists(String key)
    {
        return true;
    }

    public Board load(String key)
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

    public void delete(String key) throws FileDoesNotExist
    {

    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
