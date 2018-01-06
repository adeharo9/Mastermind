package presentation.controllers;

public class RegisteringPresentationController extends PresentationController
{
    /**
     * Constructor por defecto.
     *
     * Constructor de instancia de controlador registrante en el controlador de dominio,
     * es decir, controlador que registra su actividad en el controlador de dominio y, por lo tanto,
     * puede interactuar con el mismo.
     */
    public RegisteringPresentationController()
    {
        registerToDomainController();
    }
}
