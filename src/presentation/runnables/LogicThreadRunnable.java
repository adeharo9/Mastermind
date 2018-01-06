package presentation.runnables;

import domain.controllers.DomainController;

/**
 * Clase ejecutable de thread lógico
 *
 * Clase encargada de encapsular el código a ser ejecutado desde
 * un thread paralelo al de la interfaz gráfica, para evitar que
 * en el caso de un
 */
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
