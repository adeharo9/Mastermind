package presentation.controllers;

import util.Pair;
import util.ioUtils;

import java.util.ArrayList;

public class PresentationController
{
    /* ATTRIBUTES */

    /* PRIVATE METHODS */

    private Pair<String, String> getInfoUser()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Write your username: ");
        String username = ioUtils.input();

        ioUtils.endLine();
        ioUtils.printOutLn("Write your password: ");
        String password = ioUtils.input();

        return new Pair<>(username, password);
    }

    private int genericWarning(String action)
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Are you sure you want to " + action + "?");
        ioUtils.endLine();
        ioUtils.printOutLn("1.- Yes");
        ioUtils.printOutLn("0.- No");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    private void genericLoadError(String loadedElement)
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Error while loading " + loadedElement);
        ioUtils.endLine();
    }

    private void genericSaveError(String savedElement)
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Error while saving " + savedElement);
        ioUtils.endLine();
    }

    /* CONSTRUCTION METHODS */
    
    public PresentationController()
    {

    }

    /* METHODS */

    /* MENUS */

    public int initSessionMenu() throws NumberFormatException
    {
        ioUtils.printOutLn ("1.- Register user");
        ioUtils.printOutLn ("2.- Log in");
        ioUtils.printOutLn ("0.- Close");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    public Pair<String, String> logInMenu() throws NumberFormatException
    {
        return getInfoUser();
    }

    public Pair<String, String> registerMenu() throws NumberFormatException
    {
        return getInfoUser();
    }

    public int mainGameMenu() throws NumberFormatException
    {
        ioUtils.printOutLn ("1.- New game");
        ioUtils.printOutLn ("2.- Load game");
        ioUtils.printOutLn ("3.- Ranking");
        ioUtils.printOutLn ("4.- Info");
        ioUtils.printOutLn ("0.- Log out");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int gameModeSelectionMenu() throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Human vs. Human");
        ioUtils.printOutLn ("2.- Human vs. CPU");
        ioUtils.printOutLn ("3.- CPU vs. CPU");
        ioUtils.printOutLn ("0.- Back");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int gameRoleSelectionMenu() throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Code Maker");
        ioUtils.printOutLn ("2.- Code Breaker");
        ioUtils.printOutLn ("0.- Back");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int gameDifficultySelectionMenu() throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Easy");
        ioUtils.printOutLn ("2.- Medium");
        ioUtils.printOutLn ("3.- Hard");
        ioUtils.printOutLn ("0.- Back");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int inGameMenu() throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Write your code");
        ioUtils.printOutLn ("0.- Pause game");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int pauseMenu() throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Save and continue");
        ioUtils.printOutLn ("2.- Save and exit");
        ioUtils.printOutLn ("3.- Exit without saving");
        ioUtils.printOutLn ("4.- Ask for clue");
        ioUtils.printOutLn ("0.- Continue game");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int loadGameMenu(ArrayList<String> savedGames) throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Currently saved games:");
        ioUtils.endLine();

        for(int i = 0; i < savedGames.size(); ++i)
        {
            ioUtils.printOut(i + 1 + ".- ");
            ioUtils.printOutLn(savedGames.get(i));
        }

        ioUtils.printOutLn("0.- Back");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    public int saveGameMenu() throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn ("Do you want to change the name of the game?");
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Yes");
        ioUtils.printOutLn ("0.- No");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    public String setGameName()
    {
        ioUtils.endLine();
        ioUtils.printOutLn ("Enter a name for the game:");
        return ioUtils.input();
    }

    /* WARNING MESSAGES */

    public int exitGameWarning() throws NumberFormatException
    {
        return genericWarning("exit");
    }

    public int logOutWarning() throws NumberFormatException
    {
        return genericWarning("log out");
    }

    public int closeProgramWarning() throws NumberFormatException
    {
        return genericWarning("close Mastermind");
    }

    /* ERROR MESSAGES */

    public void optionError()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Wrong option number");
        ioUtils.endLine();
    }

    public void logInError()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Wrong username or password");
        ioUtils.endLine();
    }

    public void registerError()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Username is already used");
        ioUtils.endLine();
    }

    public void gameLoadError()
    {
        genericLoadError("game");
    }

    public void playerLoadError()
    {
        genericLoadError("player");
    }

    public void gameSaveError()
    {
        genericSaveError("game");
    }

    public void playerSaveError()
    {
        genericSaveError("player");
    }

    /* TESTING METHODS */

    /* CLONING METHODS */

}
