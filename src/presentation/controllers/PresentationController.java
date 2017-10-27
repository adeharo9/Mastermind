package presentation.controllers;

import util.Pair;
import util.ioUtils;

public class PresentationController
{
    /* ATTRIBUTES */

    /* PRIVATE METHODS */

    private Pair<String, String> getInfoUser() throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn ("Write your username: ");
        String username = ioUtils.input();

        ioUtils.endLine();
        ioUtils.printOutLn ("Write your password: ");
        String password = ioUtils.input();

        return new Pair<>(username, password);
    }

    /* CONSTRUCTION METHODS */
    
    public PresentationController()
    {

    }

    /* METHODS */

    public void wrongOption()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Wrong option number.");
        ioUtils.endLine();
    }

    public void wrongLogIn()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Wrong username or password");
        ioUtils.endLine();
    }

    public void wrongRegister()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Username is already used");
        ioUtils.endLine();
    }

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

    public int inGameMenu() throws NumberFormatException
    {
        // Menu de seleccion de accion en el juego: tirar turno o bien pausa
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Write your code");
        ioUtils.printOutLn ("0.- Pause game");
        ioUtils.endLine();
        ioUtils.printOut("Select an option: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int pauseMenu() throws NumberFormatException
    {
        // Menu de pausa del juego: guardar partida, salir, etc
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Save and leave");
        ioUtils.printOutLn ("2.- Leave without saving");
        ioUtils.printOutLn ("3.- Ask for clue");
        ioUtils.printOutLn ("0.- Continue game");
        ioUtils.endLine();
        ioUtils.printOut("Select an option: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int savegame() throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn ("Do you want to change the name of the game?");
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Yes");
        ioUtils.printOutLn ("0.- No");
        ioUtils.endLine();
        ioUtils.printOut("Select an option: ");
        return Integer.parseInt(ioUtils.input());
    }

    public String setGameName()
    {
        ioUtils.endLine();
        ioUtils.printOutLn ("Enter a name for the game:");
        return ioUtils.input();
    }

    public Pair<String, String> logInMenu() throws NumberFormatException
    {
        return getInfoUser();
    }

    public Pair<String, String> registerUserMenu() throws NumberFormatException
    {
        return getInfoUser();
    }
    /* TESTING METHODS */

    /* CLONING METHODS */

}
