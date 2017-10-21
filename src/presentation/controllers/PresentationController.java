package presentation.controllers;

import util.Pair;

public class PresentationController
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */
    
    public PresentationController()
    {

    }

    /* METHODS */

    public int initialMenu ()
    {
        // Menu inicial: iniciar sesion/registrar jugador
        return 0;
    }

    public int gameSelectionMenu()
    {
        // Menú de seleccion: cargar partida, partida nueva, etc.
        return 0;
    }

    public int inGameMenu()
    {
        // Menu de seleccion de accion en el juego: tirar turno o bien pausa
        return 0;
    }

    public int pauseMenu()
    {
        // Menu de pausa del juego: guardar partida, salir, etc
        return 0;
    }

    public Pair<String, String> logInMenu()
    {
        return new Pair<>("", "");
    }

    public Pair<String, String> registerUserMenu()
    {
        return new Pair<>("", "");
    }

    /* TESTING METHODS */

    /* CLONING METHODS */

}
