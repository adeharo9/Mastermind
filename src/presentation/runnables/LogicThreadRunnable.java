package presentation.runnables;

import domain.controllers.DomainController;

public class LogicThreadRunnable implements Runnable
{
    private DomainController domainController;

    public LogicThreadRunnable(DomainController domainController)
    {
        this.domainController = domainController;
    }

    @Override
    public void run()
    {
        try
        {
            domainController.exe();
        }
        catch (InterruptedException e)
        {

        }
    }
}
