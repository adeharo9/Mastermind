package presentation.runnables;

import presentation.controllers.PresentationController;

/**
 * Clase ejecutable de transferencia de información.
 *
 * Clase encargada de realizar la transferencia unilateral de información genérica
 * desde el controlador de dominio hacia las clases y subclases del controlador
 * de presentación.
 *
 * @author Alejandro de Haro
 */

public class ProcessInfoRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final Object info;

    /**
     * Constructora por defecto.
     *
     * Constructora de una instancia de ejecutable con los distintos
     * parámetros necesarios para transferir información genérica a las
     * distintas subclases de controlador de presentación.
     *
     * @param presentationController Controlador de presentación al que realizar
     *                               la transferencia de información.
     * @param info Objeto contenedor de la información a transferir al controlador
     *             de presentación en cuestión.
     */
    public ProcessInfoRunnable(PresentationController presentationController, final Object info)
    {
        this.presentationController = presentationController;
        this.info = info;
    }

    /**
     * Función run.
     *
     * Ejecuta el código indicado cuando es llamado por el nuevo thread.
     */
    @Override
    public void run()
    {
        presentationController.processInfo(info);
    }
}
