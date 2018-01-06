package presentation.runnables;

import enums.Difficulty;
import presentation.controllers.PresentationController;

/**
 * Clase ejecutable de renderizado de tablero.
 *
 * Clase encargada de encapsular el código necesario para llamar
 * a las funciones de renderizado de tablero del controlador de presentación
 * en el thread de la interfaz gráfica desde el thread de la lógica del programa.
 *
 * @author Rafael Ramírez
 */

public class RenderBoardRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final Difficulty difficulty;

    /**
     * Constructora por defecto.
     *
     * Constructora por defecto de instancia de clase ejecutable con los
     * parámetros necesarios para renderizar un tablero vacío.
     *
     * @param presentationController Controlador de presentación encargado del renderizado del tablero.
     * @param difficulty Dificultad del tablero a renderizar.
     */
    public RenderBoardRunnable(final PresentationController presentationController, final Difficulty difficulty)
    {
        this.presentationController = presentationController;
        this.difficulty = difficulty;
    }

    /**
     * Función run.
     *
     * Ejecuta el código indicado cuando es llamado por el nuevo thread.
     */
    @Override
    public void run()
    {
        presentationController.renderBoard(difficulty);
    }
}
