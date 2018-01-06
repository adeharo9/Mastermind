package presentation.runnables;

import domain.controllers.DomainController;

/**
 * Clase ejecutable de thread lógico.
 *
 * Clase encargada de encapsular el código a ser ejecutado desde
 * un thread paralelo al de la interfaz gráfica, para evitar que
 * en el caso de un cálculo de coste computacional alto la interfaz
 * gráfica se colapse.
 *
 * @author Alejandro de Haro
 */

public class LogicThreadRunnable implements Runnable
{
    private DomainController domainController;

    /**
     * Constructora por defecto.
     *
     * Constructora de una instancia de ejecutable con el
     * controlador de dominio sobre el que ejecutar la función
     * exe() como parámetro.
     *
     * @param domainController Controlador de dominio sobre el que
     *                         ejecutar la lógica del juego.
     */
    public LogicThreadRunnable(DomainController domainController)
    {
        this.domainController = domainController;
    }

    /**
     * Función run.
     *
     * Ejecuta el código indicado cuando es llamado por el nuevo thread.
     */
    @Override
    public void run()
    {
        try
        {
            domainController.exe();
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException();
        }
    }
}
