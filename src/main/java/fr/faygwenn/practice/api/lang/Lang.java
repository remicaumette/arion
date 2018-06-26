package fr.faygwenn.practice.api.lang;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;

public enum Lang {
    FRENCH("Français", "FR"),
    ENGLISH("Anglais", "EN");

    private String name;
    private String code;
    private Configuration config;

    Lang(String name, String code) {
        this.name = name;
        this.code = code;
        Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(name().toLowerCase() +".yml"));
        this.config = YamlConfiguration.loadConfiguration(reader);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String tl(String key, String... values) {
        String value = config.getString(key);
        if (value != null) {
            value = value.replaceAll("&", "§");
            MessageFormat messageFormat = new MessageFormat(value);
            return messageFormat.format(values);
        }
        return key;
    }
}
