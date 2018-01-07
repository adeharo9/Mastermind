package domain.controllers;

import domain.classes.*;
import enums.Difficulty;
import enums.Role;
import exceptions.ReservedKeywordException;

/**
 * PlayerController.
 *
 * @author Alejandro de Haro, Alex
 */

public class PlayerController
{
    /* ATTRIBUTES */

    private Player player;

    /* CONSTRUCTION METHODS */

    /**
     * Constructora por defecto.
     *
     * Constructora de acción vacía.
     */

    public PlayerController()
    {

    }

    /**
     * Creador del controlador de jugador.
     *
     * Crea un controlador de jugador con el
     * valor de player que se pasa como parametro.
     *
     * @param player Jugador.
     */

    public PlayerController(final Player player)
    {
        setPlayerByReference(player);
    }

    /* INSTANTIATION METHODS */

    /**
     * Nuevo humano.
     *
     * Crea un jugador humano con contraseña
     * mastermind, segun el nombre introducido
     * como parametro.
     *
     * @param id Nombre de jugador.
     */

    public void newHuman(final String id)
    {
        newHuman(id, "mastermind");
    }

    /**
     * Nuevo humano con contraseña
     *
     * Crea un jugador humano con contraseña
     * segun los parametros introducidos.
     *
     * @param id Nombre del jugador humano.
     * @param password Contraseña del jugador humano.
     * @return jugador creado.
     */

    public Player newHuman(final String id, final String password)
    {
        player = new Human(id, password);
        return player;
    }

    /**
     * Crear CPU
     *
     * Crea un jugador CPU
     *
     * @param id Nombre de jugador CPU.
     */

    public void newCPU(final String id)
    {
        player = new CPU(id);
    }

    /* INSTANCE CONTROL METHODS */

    /**
     * Reinicio
     *
     * Reinicia los parametros del jugador
     * asociado al controlador.
     */

    public void restart()
    {
        player.restart();
    }

    /* SET METHODS */

    /**
     * Setter del jugador.
     *
     * Establece el valor de jugador
     * del controlador como el jugador pasado por parametro.
     *
     * @param player objeto jugador.
     * @throws IllegalArgumentException parametro no valido.
     */

    public void setPlayerByReference(final Player player) throws IllegalArgumentException
    {
        boolean b = player.isValid();
        if(!b) throw new IllegalArgumentException();

        this.player = player;
    }

    /**
     * Setter rol del jugador.
     *
     * Establece el valor de rol del jugador
     * del controlador como el rol pasado por parametro.
     *
     * @param role rol del jugador.
     * @throws IllegalArgumentException parametro no valido.
     */

    public void setRole(final Role role) throws IllegalArgumentException
    {
        player.setRole(role);
    }

    /* GET METHODS */

    /**
     * Getter del jugador.
     *
     * Devuelve el jugador
     * asociado al controlador.
     *
     * @return objeto jugador.
     */

    public Player getPlayer()
    {
        return player;
    }

    /**
     * Getter nombre del jugador.
     *
     * Devuelve el nombre del jugador
     * asociado al controlador.
     *
     * @return nombre del jugador.
     */

    public String getId()
    {
        return player.getId();
    }

    /**
     * Getter rol del jugador.
     *
     * Devuelve el rol del jugador
     * asociado al controlador.
     *
     * @return rol del jugador.
     */

    public Role getRole()
    {
        return player.getRole();
    }

    /* PLAYING METHODS */

    /**
     * Play
     *
     * Devuelve la accion jugar segun los parametros
     * de entrada.
     *
     * @param difficulty Dificultad de la partida.
     * @param lastTurn Ultimo turno jugado.
     * @param solution Codigo solucion.
     * @param isFirstTurn True si es el primer turno de la partida.
     * @return accion de escribir un codigo de respuesta.
     * @throws  IllegalArgumentException En caso que el rol del jugador no sea correcto.
     * @throws  ReservedKeywordException Input de un valor utilizado para retroceder en los menus.
     */

    public final Action play(final Difficulty difficulty, final Turn lastTurn, final Code solution, final boolean isFirstTurn) throws IllegalArgumentException, ReservedKeywordException
    {
        Action action;

        switch (player.getRole()) {
            case CODE_MAKER:
                if(isFirstTurn)
                {
                    action = codeMake(difficulty);
                }
                else
                {
                    action = codeCorrect(lastTurn.getCode(), solution);
                }
                break;
            case CODE_BREAKER:
                action = codeBreak(difficulty, lastTurn, isFirstTurn);
                break;
            case WATCHER:
                action = null;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return action;
    }

    /**
     * CodeMake
     *
     * Devuelve la accion de escribir
     * un codigo solucion segun los parametros.
     *
     * @param difficulty Dificultad de la partida.
     * @return accion de escribir un codigo de solucion.
     * @throws ReservedKeywordException Input de un valor utilizado para retroceder en los menus.
     */
    private Action codeMake(final Difficulty difficulty) throws ReservedKeywordException
    {
        return player.codeMake(difficulty);
    }

    /**
     * CodeBreak
     *
     * Devuelve la accion de escribir
     * un codigo respuesta segun los parametros.
     *
     * @param difficulty Dificultad de la partida.
     * @param lastTurn Ultimo turno jugado.
     * @param isFirstTurn True si es el primer turno de la partida.
     * @return accion de escribir un codigo de respuesta.
     * @throws ReservedKeywordException Input de un valor utilizado para retroceder en los menus.
     */
    private Action codeBreak(final Difficulty difficulty, final Turn lastTurn, final boolean isFirstTurn) throws ReservedKeywordException
    {
        return player.codeBreak(difficulty, lastTurn, isFirstTurn);
    }

    /**
     * CodeCorrect
     *
     * Devuelve la accion de correccion segun
     * los parametros de entrada.
     *
     * @param code Codigo respuesta.
     * @param solution Codigo solucion.
     * @return accion de correccion del jugador actual
     * @throws ReservedKeywordException Input de un valor utilizado para retroceder en los menus.
     */
    private Action codeCorrect(final Code code, final Code solution) throws ReservedKeywordException
    {
        return player.codeCorrect(code, solution);
    }

    /* VALIDATION METHODS */

    /**
     * Validar contraseña
     *
     * Informa si la contraseña pasada como
     * parametro es igual a la del jugador actual.
     *
     * @param password Contraseña
     * @return true si la contraseña coincide, false en caso contrario.
     */

    public boolean checkPassword(final String password)
    {
        return player.checkPassword(password);
    }
}

