package presentation.runnables;

import presentation.controllers.PresentationController;

import java.io.IOException;

/**
 * Clase ejecutable de actualización de vista
 *
 * Clase encargada de facilitar la actualización de la vista a mostrar en
 * la interfaz gráfica desde el thread de la lógica del programa
 *
 * @author Alejandro de Haro
 */

public class UpdateViewRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final String viewFile;

    /**
     * Constructora por defecto
     *
     * Constructora por defectode instancia de ejecutable con la información
     * necesaria para la actualización de la vista a mostrar en la ventana actual
     *
     * @param presentationController Controlador de presentación encargado de actualizar la
     *                               vista en la ventana actual
     * @param viewFile Archivo FXML de la vista a ser cargada
     */
    public UpdateViewRunnable(PresentationController presentationController, String viewFile)
    {
        this.presentationController = presentationController;
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
            presentationController.updateView(viewFile);
        }
        catch (IOException e)
        {
            throw new RuntimeException();
        }
    }
}
