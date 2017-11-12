package presentation.controllers;

import enums.Color;
import enums.Difficulty;
import exceptions.ReservedKeywordException;
import util.Constants;
import util.Pair;
import util.ioUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PresentationController
{
    /* ATTRIBUTES */

    /* PRIVATE METHODS */

    private Pair<String, String> getInfoUser() throws ReservedKeywordException
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Write your username(or 0 to go back): ");
        String username = ioUtils.input();

        if (username.equals("0")) throw new ReservedKeywordException();

        ioUtils.endLine();
        ioUtils.printOutLn("Write your password(or 0 to go back): ");
        String password = ioUtils.input();

        if (password.equals("0")) throw new ReservedKeywordException();

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
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- Register user");
        ioUtils.printOutLn ("2.- Log in");
        ioUtils.printOutLn ("0.- Close");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    public Pair<String, String> logInMenu() throws ReservedKeywordException
    {
        return getInfoUser();
    }

    public Pair<String, String> registerMenu() throws ReservedKeywordException
    {
        return getInfoUser();
    }

    public int mainGameMenu() throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn ("1.- New game");
        ioUtils.printOutLn ("2.- Load game");
        ioUtils.printOutLn ("3.- Ranking");
        ioUtils.printOutLn ("4.- Info");
        ioUtils.printOutLn ("0.- Log out");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
    }

    public void showInfo()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Player modes:");
        ioUtils.printOutLn("   Human vs. Human: play against other human");
        ioUtils.printOutLn("   Human vs. CPU: play against our algorithm");
        ioUtils.printOutLn("   CPU vs. CPU: see how our algorithm plays against himself");
        ioUtils.endLine();
        ioUtils.printOutLn("Game modes:");
        ioUtils.printOutLn("  CODEMAKER: Make a code and CPU will try to solve it");
        ioUtils.printOutLn("  CODEBREAKER: CPU makes a code with specific rules and you should solve it");
        ioUtils.endLine();
        ioUtils.printOutLn("Difficulty modes:");
        ioUtils.printOutLn("  EASY MODE: Code of 4 non-repeated colors, 6 possible colors");
        ioUtils.printOutLn("  MEDIUM MODE: Code of 4 colors, 6 possible colors");
        ioUtils.printOutLn("  HARD MODE: Code of 6 colors, 8 possible colors");
        ioUtils.endLine();
        ioUtils.printOutLn("Correction rules:");
        ioUtils.printOutLn("  Black tokens: The color and the position of a token coincide");
        ioUtils.printOutLn("  White tokens: The color of a token coincide");
        ioUtils.printOutLn("  None tokens: The color of a token don't coincide");
        ioUtils.printOutLn("  EASY MODE: correction tokens are placed in the order of the response tokens.");
        ioUtils.printOutLn("  MEDIUM and HARD MODE: correction tokens are placed in random order.");
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

    public List<String> readCode(Difficulty difficulty) throws ReservedKeywordException
    {
        boolean repetitionsPolicy = Constants.getRepetitionPolicyByDifficulty(difficulty);
        int numColors = Constants.getNumColorsByDifficulty(difficulty);
        int numPins = Constants.getNumPinsByDifficulty(difficulty);

        Set<Color> colorSet = Color.getValues(difficulty);
        List<String> code = new ArrayList<>(numPins);

        ioUtils.endLine();
        ioUtils.printOut("Write a code of " + numPins + (repetitionsPolicy ? " " : " non-repeated ") + "colors using the following letters (Input example:");

        for(int i = 0; i < numPins; ++i)
        {
            ioUtils.printOut(" " + Color.getRandomColor(numColors).getStrId());
        }

        ioUtils.printOutLn("):");

        for(final Color color : colorSet)
        {
            ioUtils.printOutLn(color.getStrId() + ": " + color.getStrDescription());
        }

        ioUtils.endLine();
        ioUtils.printOutLn("Write your code here (or 0 to pause):");

        String read;

        for(int i = 0; i < numPins; ++i)
        {
            read = ioUtils.input();
            if (read.equals("0")) throw new ReservedKeywordException();

            code.add(read);
        }

        return code;
    }

    public List<String> readCorrectionCode (Difficulty difficulty) throws ReservedKeywordException
    {
        int numColors = Constants.getNumColorsByDifficulty(difficulty);
        int numPins = Constants.getNumPinsByDifficulty(difficulty);
        Set<Color> correctionSet = Color.getCorrectionValues();
        List<String> code = new ArrayList<>(numPins);

        ioUtils.endLine();

        ioUtils.printOut("Write a " + numPins + "-sized code using the following letters (Input example:");

        for(int i = 0; i < numPins; ++i)
        {
            ioUtils.printOut(" " + Color.getRandomColor(numColors).getStrId());
        }

        ioUtils.printOutLn("):");

        for(final Color color : correctionSet)
        {
            ioUtils.printOutLn(color.getStrId() + ": " + color.getStrDescription());
        }
        ioUtils.endLine();

        ioUtils.printOutLn("Write your code here(or 0 to pause):");

        String read;

        for(int i = 0; i < numPins; ++i)
        {
            read = ioUtils.input();
            if (read.equals("0")) throw new ReservedKeywordException();

            code.add(read);
        }

        return code;
    }

    public void printBoard(ArrayList<ArrayList<String>> codes)
    {
        ioUtils.endLine();

        if(codes.get(0).size() == 8)
        {
            ioUtils.printOutLn ("╔══════════════╦══════════════╗");
        }
        else if(codes.get(0).size() == 12)
        {
            ioUtils.printOutLn ("╔═════════════════════╦═════════════════════╗");
        }

        for(int i = 0; i < codes.size(); ++i)
        {
            ioUtils.printOut (" ");

            for(int j = 0; j < codes.get(0).size(); ++j)
            {
                ioUtils.printOut(" ");
                ioUtils.printOut(codes.get(i).get(j));
                ioUtils.printOut(" ");
            }

            ioUtils.printOut(" ");
            ioUtils.endLine();

            if((i + 1) == codes.size())
            {
                if(codes.get(0).size() == 8)
                {
                    ioUtils.printOutLn ("╚═════════════════════════════╝");
                }
                else if(codes.get(0).size() == 12)
                {
                    ioUtils.printOutLn ("╚═══════════════════════════════════════════╝");
                }
            }
            else
            {
                if(codes.get(0).size() == 8)
                {
                    ioUtils.printOutLn ("╠══════════════╬══════════════╣");
                }
                else if(codes.get(0).size()==12)
                {
                    ioUtils.printOutLn ("╠═════════════════════╬═════════════════════╣");
                }
            }
        }
    }

    public void printCode(ArrayList<String> code)
    {
        ioUtils.printOut (" ");
        for(int j=0;j<code.size();++j) {
            ioUtils.printOut(" ");
            ioUtils.printOut(code.get(j));
            ioUtils.printOut(" ");
        }
        ioUtils.printOut(" ");
        ioUtils.endLine();
        if(code.size()==4) ioUtils.printOutLn ("╚══════════════╝");
        else if(code.size()==6)ioUtils.printOutLn ("╚═════════════════════╝");
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

    public void showClue(int type, String number, String color) throws IllegalArgumentException
    {
        ioUtils.endLine();
        switch(type)
        {
            case 1:
                ioUtils.printOutLn ("Token in position" + number + "is" + color);
                break;
            case 2:
                ioUtils.printOutLn ("There is/are" + number + color + "tokens");
                break;
            default:
                throw new IllegalArgumentException();
        }
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

    public int gameOverMenu() throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Game over");
        ioUtils.endLine();
        ioUtils.printOutLn("1.- Restart this game");
        ioUtils.printOutLn("2.- Start new game");
        ioUtils.printOutLn("0.- Main menu");
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
    }

    public void logInError()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Wrong username or password");
    }

    public void registerError()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Username is already used");
    }

    public void savedGamesListLoadError()
    {
        genericLoadError("saved games list");
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
