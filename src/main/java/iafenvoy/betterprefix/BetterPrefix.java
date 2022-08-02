package iafenvoy.betterprefix;

import iafenvoy.betterprefix.commands.PrefixCommandExecutor;
import iafenvoy.betterprefix.config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterPrefix extends JavaPlugin {
    private final ConfigManager manager = new ConfigManager(".\\config\\betterprefix.json");

    @Override
    public void onEnable() {
        manager.load();
        this.getCommand("prefix").setExecutor(new PrefixCommandExecutor());
    }

    @Override
    public void onDisable() {
        manager.save();
    }
}
