package enums;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * enum Role.
 *
 * Roles que puede tener un jugador.
 *
 * @author Alex Sánchez
 */

public enum Role implements Serializable
{
    CODE_MAKER,
    CODE_BREAKER,
    WATCHER;

    /**
     * Rol aleatorio.
     *
     * Devuelve un rol aleatorio.
     *
     * @return Rol.
     * @throws IllegalArgumentException Parametro no valido.
     */

    public static Role autoRole() throws IllegalArgumentException
    {
        Role role;
        int rand = ThreadLocalRandom.current().nextInt(0, 2);

        switch (rand)
        {
            case 0:
                role = CODE_MAKER;
                break;
            case 1:
                role = CODE_BREAKER;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return role;
    }

    /**
     * Rol complementario.
     *
     * Devuelve el rol complementario al del
     * parametro de entrada.
     *
     * @param role Rol.
     * @return CODE_MAKER si role es CODE_BREAKER, y a la inversa.
     */

    public static Role complementaryRole(Role role) throws IllegalArgumentException
    {
        Role compRole;

        switch(role)
        {
            case CODE_MAKER:
                compRole = CODE_BREAKER;
                break;
            case CODE_BREAKER:
                compRole = CODE_MAKER;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return compRole;
    }
}
