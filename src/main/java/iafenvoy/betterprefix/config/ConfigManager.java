package iafenvoy.betterprefix.config;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import iafenvoy.betterprefix.prefix.PrefixManager;
import iafenvoy.betterprefix.utils.FileUtils;

import java.io.IOException;

public class ConfigManager {
    private final String configPath;

    public ConfigManager(String configPath) {
        this.configPath = configPath;
    }

    public void load() {
        try {
            String json = FileUtils.readFile(this.configPath);
            PrefixManager.INSTANCE = new Gson().fromJson(json, PrefixManager.class);
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            String json = new Gson().toJson(PrefixManager.INSTANCE);
            FileUtils.saveFile(this.configPath, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
