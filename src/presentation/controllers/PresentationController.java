package presentation.controllers;

import util.Pair;
import util.ioUtils;

public class PresentationController
{
    /* ATTRIBUTES */

    /* CONSTRUCTION METHODS */
    
    public PresentationController()
    {

    }

    /* METHODS */

    public int initialMenu () throws NumberFormatException
    {
        // Menu inicial: iniciar sesion/registrar jugador
        ioUtils.printOutLn ("1.- Register user");
        ioUtils.printOutLn ("2.- Log in");
        ioUtils.printOutLn ("0.- Close");
        ioUtils.endLine();
        ioUtils.printOut("Select an option: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int gameSelectionMenu() throws NumberFormatException
    {
        // Menú de seleccion: cargar partida, partida nueva, etc.
        ioUtils.printOutLn ("1.- New game");
        ioUtils.printOutLn ("2.- Load game");
        ioUtils.printOutLn ("3.- Ranking");
        ioUtils.printOutLn ("4.- Info");
        ioUtils.printOutLn ("0.- Log out");
        ioUtils.endLine();
        ioUtils.printOut("Select an option: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int gameModeSelectionMenu() throws NumberFormatException
    {
        // Menú de selección de modo de juego: codebreaker, codemaker, etc.
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Codemaker");
        ioUtils.printOutLn ("2.- Codebreaker");
        ioUtils.printOutLn ("0.- Back");
        ioUtils.endLine();
        ioUtils.printOut("Select game mode: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int gameDifficultySelectionMenu() throws NumberFormatException
    {
        // Menú de selección de dificultad de juego: fácil, medio, difícil, etc.
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Easy");
        ioUtils.printOutLn ("2.- Medium");
        ioUtils.printOutLn ("3.- Hard");
        ioUtils.printOutLn ("0.- Back");
        ioUtils.endLine();
        ioUtils.printOut("Select difficulty: ");
        return Integer.parseInt(ioUtils.input());
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
