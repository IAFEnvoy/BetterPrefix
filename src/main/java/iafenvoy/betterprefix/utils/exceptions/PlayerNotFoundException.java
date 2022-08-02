package iafenvoy.betterprefix.utils.exceptions;

import java.util.UUID;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String name) {
        super("Can't find a player named " + name);
    }

    public PlayerNotFoundException(UUID uuid) {
        super("Can't find a player's UUID is " + uuid.toString());
    }
}
