package presentation.runnables;

import presentation.controllers.PresentationController;

/**
 * Clase ejecutable de renderizado de último turno.
 *
 * Clase encargada de transferir los datos necesarios para actualizar la vista de tablero
 * con la información del último turno, con el objetivo de no renderizar el tablero completo
 * cada vez que se juega un nuevo turno.
 *
 * @author Alejandro de Haro
 */

public class RenderLastTurnRunnable implements Runnable
{

    private final PresentationController presentationController;
    private final boolean renderFinishTurnButton;

    /**
     * Constructora por defecto.
     *
     * Constructora por defecto de instancia de ejecutable con
     * los parámetros necesarios para la renderización de un turno
     * individual como último turno en la vista de tablero
     *
     * @param presentationController Controlador de presentación encargado del renderizado
     *                               del último turno a añadir al tablero
     * @param renderFinishTurnButton Booleano de control para mostrar u ocultar el botónde finalizar turno
     */
    public RenderLastTurnRunnable(final PresentationController presentationController, final boolean renderFinishTurnButton)
    {
        this.presentationController = presentationController;
        this.renderFinishTurnButton = renderFinishTurnButton;
    }

    /**
     * Función run.
     *
     * Ejecuta el código indicado cuando es llamado por el nuevo thread.
     */
    @Override
    public void run()
    {
        presentationController.renderLastTurn(renderFinishTurnButton);
    }
}
