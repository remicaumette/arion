package net.orion.orioncore.api.lang;

import net.orion.orioncore.api.OrionApi;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public enum Lang {
    FRENCH("Français", "FR"),
    ENGLISH("Anglais", "EN");

    private String name;
    private String code;
    private Map<String, String> sentences;

    Lang(String name, String code) {
        this.name = name;
        this.code = code;
        this.sentences = new HashMap<>();

        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream(name().toLowerCase() + ".properties"));
            properties.stringPropertyNames()
                    .forEach(property -> sentences.put(property, properties.getProperty(property)));
        } catch (IOException e) {
            OrionApi.getLogger().severe("Unable to load the " + name().toLowerCase() + " properties file.");
        }
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String tl(String key, String... values) {
        String value = sentences.get(key);

        if (value != null) {
            value = value.replaceAll("&", "§");
            MessageFormat messageFormat = new MessageFormat(value);
            return messageFormat.format(values);
        }

        return key;
    }
}
