package presentation.runnables;

import enums.PopUpWindowStyle;
import presentation.controllers.PresentationController;

import java.io.IOException;

/**
 * Clase ejecutable de creación de nueva ventana de popup.
 *
 * Clase encargada de encapsular el código a ser ejecutado desde
 * un thread paralelo al de la la lógica del programa con la finalidad
 * de abrir una nueva ventana de popup en la interfaz gráfica.
 *
 * @author Alejandro de Haro
 */

public class PopUpViewRunnable implements Runnable
{
    private final PresentationController presentationController;
    private final PopUpWindowStyle popUpWindowStyle;
    private final String viewFile;

    /**
     * Constructora por defecto.
     *
     * Constructora de una instancia de ejecutable con los
     * distintos parámetros necesarios para abrir una nueva
     * ventana de popup.
     *
     * @param presentationController Controlador de presentación a utilizar
     *                               para gestionar la creación y apertura de
     *                               la nueva ventana.
     * @param popUpWindowStyle Enumeración que indica el tipo de plantilla predefinida
     *                         de estilo a utilizar en la generación de la nueva ventana
     *                         de popup.
     * @param viewFile Nombre del archivo de vista a cargar en la nueva ventana de popup.
     */
    public PopUpViewRunnable(final PresentationController presentationController, final PopUpWindowStyle popUpWindowStyle, final String viewFile)
    {
        this.presentationController = presentationController;
        this.popUpWindowStyle = popUpWindowStyle;
        this.viewFile = viewFile;
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
            presentationController.popUpWindow(popUpWindowStyle, viewFile);
        }
        catch (IOException e)
        {
            throw new RuntimeException();
        }
    }
}
