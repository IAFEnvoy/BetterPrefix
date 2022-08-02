package iafenvoy.betterprefix.utils;

import iafenvoy.betterprefix.utils.exceptions.PlayerNotFoundException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class UUIDUtils {
    public static UUID getUuidByName(String name) throws PlayerNotFoundException {
        Player player = Bukkit.getServer().getPlayer(name);
        if (player == null) throw new PlayerNotFoundException(name);
        return player.getUniqueId();
    }

    public static String getNameByUuid(UUID uuid) throws PlayerNotFoundException {
        Player player = Bukkit.getServer().getPlayer(uuid);
        if (player == null) throw new PlayerNotFoundException(uuid);
        return player.getName();
    }
}
