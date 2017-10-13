import presentation.controllers.MainController;
import testing.MainTestController;
import util.Input;

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
                MainController mainController = new MainController();
                mainController.exe();
        }
    }
}
