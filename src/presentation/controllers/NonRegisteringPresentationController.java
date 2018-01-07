package presentation.controllers;

/**
 * Controlador de controladores no registrantes.
 *
 * Clase encargada de gestionar los controladores no registrantes.
 *
 * @author Alejandro de Haro
 */

public class NonRegisteringPresentationController extends PresentationController
{
    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador no registrante en el controlador de dominio,
     * es decir, controlador que no registra su actividad en el controlador de dominio y, por lo tanto,
     * no puede interactuar con el mismo.
     */
    public NonRegisteringPresentationController()
    {

    }
}
