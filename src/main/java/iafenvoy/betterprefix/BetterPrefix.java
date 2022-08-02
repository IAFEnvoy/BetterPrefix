package iafenvoy.betterprefix;

import iafenvoy.betterprefix.commands.PrefixCommandExecutor;
import iafenvoy.betterprefix.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterPrefix extends JavaPlugin {
    private final ConfigManager manager = new ConfigManager(".\\config\\betterprefix.json");

    @Override
    public void onEnable() {
        manager.load();
        PluginCommand pc = this.getCommand("prefix");
        if (pc == null) throw new RuntimeException("Can't find command /prefix");
        pc.setExecutor(new PrefixCommandExecutor());
        Bukkit.getPluginManager().registerEvents(new PluginEventHandler(), this);
    }

    @Override
    public void onDisable() {
        manager.save();
    }
}
