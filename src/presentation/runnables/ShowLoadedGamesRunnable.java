package presentation.runnables;

import presentation.controllers.PresentationController;

import java.util.Set;

/**
 * Clase ejecutable de transferencia de lista de partidas guardadas
 *
 * Clase encargada de transferir la lista de partidas guardadas al controlador
 * de presentación en cuestión para su correcta visualización.
 *
 * Funcionalidad implementada en clase a parte en vez de aprovechar la clase genérica
 * de transferencia de información porque, en el caso del controlador de vista de cargar
 * partida dicha función ya estaba en uso.
 *
 * @author Alex Sánchez
 */

public class ShowLoadedGamesRunnable implements Runnable
{
    private final PresentationController presentationController;
    private final Set<String> savedGames;

    /**
     * Constructora por defecto.
     *
     * Constructora por defecto de una instancia de ejecutable encargado del envío
     * de la lista de partidas guardadas al controlador de vista de cargado de partidas.
     *
     * @param presentationController Controlador de presentación encargado de la visualización de la
     *                               lista de partidas guardadas.
     * @param savedGames Lista de partidas guardadas.
     */
    public ShowLoadedGamesRunnable(final PresentationController presentationController, final Set<String> savedGames)
    {
        this.presentationController = presentationController;
        this.savedGames = savedGames;
    }

    /**
     * Función run.
     *
     * Ejecuta el código indicado cuando es llamado por el nuevo thread.
     */
    public void run()
    {
        presentationController.showLoadedGames(savedGames);
    }
}
