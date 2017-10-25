package persistence;

import domain.classes.Board;
import exceptions.IntegrityCorruption;

import java.io.FileNotFoundException;

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

    private void saveBoard(Board board)
    {
    }

    public void save(Object board)
    {
        saveBoard((Board) board);
    }

    public void delete(String key) throws FileNotFoundException
    {

    }

    public void checkIntegrity() throws IntegrityCorruption
    {

    }
}
