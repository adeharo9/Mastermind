package util;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public enum Role implements Serializable
{
    CODE_MAKER,
    CODE_BREAKER,
    WATCHER;

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
