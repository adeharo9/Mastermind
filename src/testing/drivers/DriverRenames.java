package testing.drivers;

import domain.classes.Human;
import domain.classes.Player;
import domain.controllers.DomainController;
import persistence.PlayerPersistence;
import util.ioUtils;

import java.io.IOException;

public class DriverRenames {

    private PlayerPersistence playerPersistence;
    private DomainController domainController;

    private DriverRenames()
    {

    }

    public static void main (String args[])
    {

        DriverRenames dt = new DriverRenames();
        dt.exe();

    }

    public void exe()
    {
       try
       {
           testRenameUsername();
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
    }

    public void testRenameUsername() throws IOException
    {
        playerPersistence = new PlayerPersistence();
        domainController = new DomainController();

        ioUtils.printOutLn("Introduce a id:");
        String idPlayer = ioUtils.input();

        Player player = new Human(idPlayer, "1234");
        playerPersistence.save(player);

        //domainController.logInUser(idPlayer, "1234");

        ioUtils.printOutLn("Introduce a new id:");
        idPlayer = ioUtils.input();

        domainController.renameUsername(idPlayer);
    }


}
