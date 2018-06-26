package fr.faygwenn.practice.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * Créer et utiliser simplement des fichiers de configuration.
 *
 * @author PYRRH4
 */

public class ConfigFile {
    private Plugin plugin;
    private String fileName;

    public ConfigFile(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;

        create();
    }

    public void set(String path, Object value) {
        File file = new File(plugin.getDataFolder(), fileName);
        FileConfiguration language = YamlConfiguration.loadConfiguration(file);
        language.set(path, value);

        try {
            language.save(new File(plugin.getDataFolder(), fileName));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public YamlConfiguration get() {
        return YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), fileName));
    }

    private void create() {
        try {
            plugin.saveResource(fileName, false);
        } catch (IllegalArgumentException exception) {
            if (exception.getMessage() != null && exception.getMessage().contains("The embedded resource"))
                return;
            else
                exception.printStackTrace();
        }
    }
}
