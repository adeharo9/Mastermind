package domain.classes;

import enums.Difficulty;
import enums.Role;
import exceptions.ReservedKeywordException;
import util.DeepCopyable;
import util.Utils;

import java.io.Serializable;

/**
 * Clase Player.
 *
 * Esta clase representa un jugador.
 *
 * @author Rafael Ramírez
 */

public abstract class Player implements DeepCopyable, Serializable
{
    /* ATTRIBUTES */

    protected final String id;
    protected String username;
    protected Role role;

    /* PRIVATE METHODS */
    /**
     * Validar id.
     *
     * Comprueba si el id no es nulo.
     *
     * @param id ID a comprobar.
     * @return Devuelve true si el id es válido.
     * @throws NullPointerException Si el id es null.
     */
    private static boolean isValidId(final String id) throws NullPointerException
    {
        return id != null;
    }

    /* CONSTRUCTION METHODS */

    /**
     * Constructora por defecto.
     *
     * Instancia un jugador con un identificador aleatorio.
     */
    protected Player ()
    {
        this.id = Utils.autoID();
        this.username = this.id;
        this.role = null;
    }

    /**
     * Constructora con id.
     *
     * Instancia un jugador con un identificador dado.
     *
     * @param username Identificador del jugador.
     * @throws IllegalArgumentException Si el id dado no es válido.
     */
    protected Player(final String username) throws IllegalArgumentException
    {
        this.id = Utils.autoID();
        this.username = username;
        this.role = null;
    }

    /**
     * Constructora por copia.
     *
     * Instancia un jugador por copia a partir de otro dado.
     *
     * @param player Jugador a copiar.
     * @throws IllegalArgumentException El jugador dado no es válido.
     */
    protected Player(final Player player) throws IllegalArgumentException, NullPointerException
    {
        this.id = player.id;
        this.username = player.getUsername();
        this.role = player.getRole();
    }

    /* INSTANCE CONTROL METHODS */

    public final void restart()
    {
        this.role = null;
    }

    /* GET METHODS */

    /**
     * Getter del ID de jugador.
     *
     * Devuelve el ID del jugador.
     *
     * @return ID del jugador.
     */
    public final String getId()
    {
        return id;
    }

    /**
     * Getter del nombre de usuario.
     *
     * Devuelve el nombre de usuario del jugador.
     *
     * @return Nombre de usuario del jugador.
     */
    public final String getUsername()
    {
        return username;
    }

    /**
     * Getter del rol.
     *
     * Devuelve el rol del jugador.
     *
     * @return Rol del jugador.
     */
    public final Role getRole()
    {
        return role;
    }

    /* SET METHODS */

    /**
     * Setter del id.
     *
     * Establece el identificador del jugador.
     *
     * @param username Identificador del jugador.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Setter del rol.
     *
     * Establece el rol del jugador.
     *
     * @param role Rol del jugador.
     * @throws IllegalArgumentException Si el rol es null.
     */
    public void setRole(Role role) throws IllegalArgumentException
    {
        boolean b = role != null;
        if(!b) throw new IllegalArgumentException();

        this.role = role;
    }

    /**
     * Setter de la contraseña.
     *
     * Establece la contraseña del jugador en caso
     * que este sea de tipo Human.
     *
     * @param password Contraseña del jugador.
     * @throws IllegalArgumentException Si la contraseña no es válida.
     * @throws NullPointerException Si la contraseña es null.
     */
    public void setPassword(final String password) throws IllegalArgumentException, NullPointerException {}

    /* VALIDATION METHODS */

    /**
     * Validar id.
     *
     * Comprueba que el id del jugador es válido.
     *
     * @return boolean.
     */
    public boolean isValid()
    {
        return isValidId(username);
    }

    /**
     * Comprobar contraseña.
     *
     * Comprueba que la contraseña password se
     * corresponde a la del jugador.
     *
     * @param password Contraseña a comprobar.
     * @return boolean.
     */
    public boolean checkPassword(final String password)
    {
        return true;
    }

    /* CLONING METHODS */

    @Override
    public abstract Player deepCopy ();

    /* PLAYING METHODS */

    /**
     * Acción como code maker.
     *
     * Permite al jugador realizar una acción como code maker.
     *
     * @param difficulty Dificultad de la partida.
     * @return Acción de code maker con el código de la jugada.
     * @throws ReservedKeywordException Si se espera un tipo objeto pero se pretende ir hacia atrás.
     */
    public abstract Action codeMake(final Difficulty difficulty) throws ReservedKeywordException;

    /**
     * Acción como code breaker.
     *
     * Permite al jugador realizar una acción como code breaker.
     *
     * @param difficulty Dificultad de la partida.
     * @param lastTurn Último turno de la partida.
     * @param isFirstTurn Indica si se trata del primer turno.
     * @return Acción de code breaker con el código de la jugada.
     * @throws ReservedKeywordException Si se espera un tipo objeto pero se pretende ir hacia atrás.
     */
    public abstract Action codeBreak(final Difficulty difficulty, final Turn lastTurn, final boolean isFirstTurn) throws ReservedKeywordException;

    /**
     * Acción como code correct.
     *
     * Permite al jugador corregir la acción de otro jugador.
     *
     * @param code Código introducido a corregir.
     * @param solution Código de colores que representa la solución.
     * @return Acción de code maker en modo corrección con el código de la corrección.
     * @throws ReservedKeywordException Si se espera un tipo objeto pero se pretende ir hacia atrás.
     */
    public abstract Action codeCorrect(final Code code, final Code solution) throws ReservedKeywordException;
}