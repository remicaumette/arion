package com.arionmc.arioncore.api.lang;

import com.arionmc.arioncore.api.ArionApi;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

public enum Lang {
    FRENCH("Français", "FR"),
    ENGLISH("Anglais", "EN");

    private String name;
    private String code;
    private Map<String, String> sentences = new HashMap<>();

    Lang(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * @return Le nom de la langue.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Le code de la langue.
     */
    public String getCode() {
        return code;
    }

    /**
     * Permet de récupérer la traduction d'un message.
     *
     * @param key    La clef du message.
     * @param values Les valeurs à remplacer dans le message.
     * @return Le message formatté et traduit. S'il n'existe pas, la clef du message sera renvoyé.
     */
    public String tl(String key, String... values) {
        String value = sentences.get(key);

        if (value != null) {
            value = value.replaceAll("&", "§");
            MessageFormat messageFormat = new MessageFormat(value);
            return messageFormat.format(values);
        }

        return key;
    }

    /**
     * Importe les traductions d'un plugin.
     *
     * @param plugin Le plugin.
     */
    public static void importTranslationFromPlugin(JavaPlugin plugin) {
        Stream.of(values()).forEach(lang -> {
            try {
                Properties properties = new Properties();
                properties.load(plugin.getResource(lang.name().toLowerCase() + ".properties"));
                properties.stringPropertyNames()
                        .forEach(property -> lang.sentences.put(property, properties.getProperty(property)));
            } catch (IOException e) {
                ArionApi.getLogger()
                        .warning("Unable to load the " + lang.name().toLowerCase() + " properties file.");
            }
        });
    }
}
