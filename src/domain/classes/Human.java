package domain.classes;

import enums.Color;
import enums.Difficulty;
import exceptions.ReservedKeywordException;
import util.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Clase Humano.
 *
 * Esta clase representa un Jugador del tipo
 * Humano, el cual tiene una contraseña.
 *
 * @author Rafael Ramirez
 */

public class Human extends Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    private String password;

    /* PRIVATE METHODS */

    private static boolean isValidPassword(final String password)
    {
        return password != null;
    }

    /* CONSTRUCTION METHODS */

    /**
     * Constructor de humano por defecto.
     *
     * Instancia un humano vacío y no válido
     * sin previa inicialización usando los setters
     * públicos disponibles.
     */

    public Human()
    {
        super();
        this.password = "";
    }

    /**
     * Constructor de jugador por nombre.
     *
     * Instancia un jugador con nombre solamente.
     *
     * @param username Nombre del jugador.
     * @throws IllegalArgumentException En caso que el nombre no sea correcto.
     */

    public Human(final String username) throws IllegalArgumentException
    {
        super(username);
    }

    /**
     * Constructor de jugador.
     *
     * Instancia un jugador con nombre y contraseña,
     * el cual ya puede iniciar sesion en la aplicacion.
     *
     * @param username Nombre del jugador
     * @param password Contraseña de acceso
     * @throws IllegalArgumentException En caso que alguno de los parametros de human no sea correcto.
     * @throws NullPointerException En caso que alguno de los parametros sea nulo.
     */

    public Human(final String username, final String password) throws IllegalArgumentException, NullPointerException
    {
        super(username);

        setPassword(password);
    }

    /**
     * Constructor de jugador.
     *
     * Instancia un jugador humano que es copia de human.
     *
     * @param human Objeto Humano
     * @throws IllegalArgumentException En caso que human no sea correcto.
     * @throws NullPointerException En caso que human sea nulo.
     */
    @Deprecated
    public Human(final Human human) throws IllegalArgumentException, NullPointerException
    {
        super(human);

        setPassword(human.getPassword());
    }

    /* SET METHODS */

    /**
     * Setter de contraseña.
     *
     * Indica la contraseña de acceso del humano.
     *
     * @param password Contraseña de acceso.
     * @throws IllegalArgumentException En caso que password no sea correcto.
     * @throws NullPointerException En caso que password sea nulo.
     */

    public void setPassword(final String password) throws IllegalArgumentException, NullPointerException
    {
        boolean b = isValidPassword(password);
        if(!b) throw new IllegalArgumentException();

        this.password = password;
    }

    /* GET METHODS */
    /**
     * Getter de contraseña.
     *
     * Obtiene la contraseña del humano.
     *
     * @return String password
     */
    public final String getPassword()
    {
        return password;
    }

    /* VALIDATION METHODS */

    /**
     * Setter de contraseña.
     *
     * Instancia un jugador humano que es copia de human.
     *
     * @param password Contraseña de acceso.
     * @return boolean password es igual al password del humano actual.
     * @throws NullPointerException En caso que password sea nulo.
     */
    public final boolean checkPassword(final String password) throws NullPointerException
    {
        return this.password.equals(password);
    }

    /* CLONING METHODS */

    /**
     * Copia de Human
     *
     * Devuelve una copia profunda del objeto actual
     *
     * @return Copia profunda del objeto actual
     * @throws IllegalArgumentException En caso que Human no sea correcto.
     * @throws NullPointerException En caso que Human sea nulo.
     */
    @Deprecated
    public Human deepCopy() throws IllegalArgumentException, NullPointerException
    {
        return new Human(this);
    }

    /* PLAY METHODS */
    
    public Action codeMake(final Difficulty difficulty) throws ReservedKeywordException
    {
        List<Color> colorList = codeInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeMake(code);
    }

    public Action codeBreak(final Difficulty difficulty, final Turn lastTurn, final boolean isFirstTurn) throws ReservedKeywordException
    {
        List<Color> colorList = codeInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeBreak(code);
    }

    public Action codeCorrect(final Difficulty difficulty, final Code c, final Code s) throws ReservedKeywordException
    {
        List<Color> colorList = correctionInputByUser(difficulty);
        Code code = new Code(colorList);

        return new CodeCorrect(code);
    }

    /* USER INTERACTION METHODS */

    private List<Color> codeInputByUser(Difficulty difficulty) throws ReservedKeywordException
    {
        List<String> code = readCode(difficulty);
        List<Color> colorList = new ArrayList<>(code.size());

        for(final String str : code)
        {
            colorList.add(Color.getColor(str));
        }

        return colorList;
    }

    private List<Color> correctionInputByUser(Difficulty difficulty) throws ReservedKeywordException
    {
        List<String> correction = readCorrectionCode(difficulty);
        List<Color> colorList = new ArrayList<>(correction.size());

        for(final String str : correction)
        {
            colorList.add(Color.getColor(str));
        }

        return colorList;
    }

    private List<String> readCode(Difficulty difficulty) throws ReservedKeywordException
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

    private List<String> readCorrectionCode (Difficulty difficulty) throws ReservedKeywordException
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
}