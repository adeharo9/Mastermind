import domain.controllers.DomainController;
import presentation.controllers.PresentationController;
import testing.MainTestController;

public class Mastermind
{
    public static void main(String args[])
    {
        switch(args[0])
        {
            case "-T":
            case "--TESTING":
                MainTestController mainTestController = new MainTestController();
                mainTestController.exe();
                break;
            default:
                DomainController domainController = new DomainController();
                domainController.exe();
        }
    }
}
