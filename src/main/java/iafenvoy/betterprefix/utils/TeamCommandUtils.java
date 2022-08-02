package iafenvoy.betterprefix.utils;

import iafenvoy.betterprefix.prefix.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class TeamCommandUtils {
    public static void addPrefix(Prefix p) {
        sendCommand(String.format("team add %s \"%s\"", p.getId(), p.getText()));
    }

    public static void modifyPrefix(Prefix p) {
        sendCommand(String.format("team modify %s displayName \"%s\"", p.getId(), p.getText()));
    }

    public static void removePrefix(Prefix p) {
        sendCommand(String.format("team remove %s", p.getId()));
    }

    public static void setPlayerPrefix(Prefix p, String playerName) {
        sendCommand(String.format("team join %s %s", p.getId(), playerName));
    }

    public static void removePlayerPrefix(String playerName) {
        sendCommand(String.format("team leave %s", playerName));
    }

    public static void sendCommand(String command) {
        CommandSender sender = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(sender, command);
    }
}
