package iafenvoy.betterprefix;

import iafenvoy.betterprefix.prefix.PrefixManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PluginEventHandler implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        Player player = evt.getPlayer();
        if (PrefixManager.INSTANCE.getPlayerByUuid(player.getUniqueId()) == null)
            PrefixManager.INSTANCE.newPlayer(player);
    }
}
