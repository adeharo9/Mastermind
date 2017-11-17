package presentation.controllers;

import enums.Color;
import enums.Role;
import exceptions.ReservedKeywordException;
import util.Pair;
import util.ioUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * PresentationController.
 *
 * @author Rafael
 */

public class PresentationController
{
    /* ATTRIBUTES */

    private List<Color> solution = new ArrayList<>();
    private List<List<Color>> codes = new ArrayList<>();
    private List<List<Color>> corrections = new ArrayList<>();

    /* PRIVATE METHODS */

    public String getUsername() throws ReservedKeywordException
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Write your username(or 0 to go back): ");
        String username = ioUtils.input();

        if (username.equals("0")) throw new ReservedKeywordException();

        return username;
    }

    public String getPassword() throws ReservedKeywordException
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Write your password(or 0 to go back): ");
        String password = ioUtils.input();

        if (password.equals("0")) throw new ReservedKeywordException();

        return password;
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
    }

    private void genericSaveError(String savedElement)
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Error while saving " + savedElement);
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

    public void setSolution(List<Color> solution)
    {
        this.solution = solution;
    }

    public void setCodes(List<List<Color>> codes)
    {
        this.codes = codes;
    }

    public void setCorrections(List<List<Color>> corrections)
    {
        this.corrections = corrections;
    }

    public void addCode(List<Color> code)
    {
        this.codes.add(code);
    }

    public void addCorrection(List<Color> correction)
    {
        this.corrections.add(correction);
    }

    public void clear()
    {
        solution.clear();
        codes.clear();
        corrections.clear();
    }

    public void printBoard(Role role)
    {
        if(!codes.isEmpty())
        {
            printCodes();
        }

        if((role == Role.CODE_MAKER || role == Role.WATCHER) && !solution.isEmpty())
        {
            printSolution();
        }
    }

    private void printCodes()
    {
        ioUtils.endLine();

        if(codes.get(0).size() == 4)
        {
            ioUtils.printOutLn ("╔════════════╦════════════╗");
        }
        else if(codes.get(0).size() == 5)
        {
            ioUtils.printOutLn ("╔═══════════════╦═══════════════╗");
        }

        for(int i = codes.size() - 1; i > 0; --i)
        {
            ioUtils.printOut ("║");

            for(int j = 0; j < codes.get(i).size(); ++j)
            {
                ioUtils.printOut(" ");
                ioUtils.printOut(codes.get(i).get(j).getStrId());
                ioUtils.printOut(" ");
            }
            ioUtils.printOut("║");

            if(i < corrections.size())
            {
                for (int j = 0; j < corrections.get(i).size(); ++j) {
                    ioUtils.printOut(" ");
                    ioUtils.printOut(corrections.get(i).get(j).getStrId());
                    ioUtils.printOut(" ");
                }
                ioUtils.printOut("║");
            }
            ioUtils.endLine();

            if(codes.get(i).size() == 4)
            {
                ioUtils.printOutLn ("╠════════════╬════════════╣");
            }
            else if(codes.get(i).size()==5)
            {
                ioUtils.printOutLn ("╠═══════════════╬═══════════════╣");
            }
        }

        ioUtils.printOut ("║");

        for(int j = 0; j < codes.get(0).size(); ++j)
        {
            ioUtils.printOut(" ");
            ioUtils.printOut(codes.get(0).get(j).getStrId());
            ioUtils.printOut(" ");
        }
        ioUtils.printOut ("║");

        if(0 < corrections.size())
        {
            for(int j = 0; j < corrections.get(0).size(); ++j)
            {
                ioUtils.printOut(" ");
                ioUtils.printOut(corrections.get(0).get(j).getStrId());
                ioUtils.printOut(" ");
            }
            ioUtils.printOut ("║");
        }
        ioUtils.endLine();

        if(codes.get(0).size() == 4)
        {
            ioUtils.printOutLn ("╚════════════╩════════════╝");
        }
        else if(codes.get(0).size() == 5)
        {
            ioUtils.printOutLn ("╚═══════════════╩═══════════════╝");
        }
    }

    private void printSolution()
    {
        ioUtils.printOut ("║");

        for(final Color color : solution)
        {
            ioUtils.printOut(" ");
            ioUtils.printOut(color.getStrId());
            ioUtils.printOut(" ");
        }

        ioUtils.printOut("║");
        ioUtils.endLine();

        if(solution.size()==4)
        {
            ioUtils.printOutLn ("╚════════════╝");
        }
        else if(solution.size()==5)
        {
            ioUtils.printOutLn ("╚═══════════════╝");
        }
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
                ioUtils.printOutLn ("Token in position " + number + " is " + color);
                break;
            case 2:
                ioUtils.printOutLn ("There is/are " + number + " " + color + " tokens");
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int loadGameMenu(List<String> savedGames) throws NumberFormatException
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

    public int gameOverMenu(String points) throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Game over");
        ioUtils.printOutLn("Points: "+ points);
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
        ioUtils.printOutLn ("Enter a name for the game: ");
        return ioUtils.input();
    }

    public int printRanking(List<Pair<String, Integer>> ranking)
    {
        if(!ranking.isEmpty())
        {
            ioUtils.endLine();
        }

        int i = 1;
        for(final Pair<String, Integer> rankingEntry : ranking)
        {
            ioUtils.printOutLn("    " + i + ". " + rankingEntry.first + " - " + rankingEntry.second + " points");
            ++i;
        }
        ioUtils.endLine();
        ioUtils.printOutLn("0.- Back");
        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");
        return Integer.parseInt(ioUtils.input());
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

    public int savedGameAlreadyExistsWarning() throws NumberFormatException
    {
        ioUtils.endLine();
        ioUtils.printOutLn("This game already exists. Do you want to overwrite it?");

        ioUtils.endLine();
        ioUtils.printOutLn("1.- Yes");
        ioUtils.printOutLn("0.- No");

        ioUtils.endLine();
        ioUtils.printOut("Select option number: ");

        return Integer.parseInt(ioUtils.input());
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

    public void savedGamesListNotExistError()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("There are no saved games for this player.");
    }

    public void gameNotStartedError()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("The game still has no code");
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

    public void gameDeleteError()
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Error while deleting game");
    }

    public void rankingLoadError()
    {
        genericLoadError("ranking");
    }

    public void rankingSaveError()
    {
        genericSaveError("ranking");
    }

    public void illegalActionError(final String msg)
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Illegal action: " + msg);
    }

    public void gameNotExistError(final String gameId)
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Game " + gameId + " does not exist. Please choose another one");
    }

    public void playerAlreadyExistsError(final String playerId)
    {
        ioUtils.endLine();
        ioUtils.printOutLn("Player " + playerId + " already exists. Please choose another one");
    }
}
