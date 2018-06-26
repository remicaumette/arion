package fr.faygwenn.practice.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Message extends Replaceable<Message> {
    private LinkedList<String> lines;

    public Message() {
        this.lines = new LinkedList<String>();
    }

    public Message(String... lines) {
        this.lines = new LinkedList<String>();

        add(lines);
    }

    public Message(List<String> lines) {
        this.lines = new LinkedList<String>();
        String[] linesArray = lines.toArray(new String[lines.size()]);

        add(linesArray);
    }

    public LinkedList<String> getLines() {
        return cloneReplaceFormat(this.lines);
    }

    @Deprecated
    public void setLines(LinkedList<String> lines) {
        this.lines = lines;
    }

    public Message add(String... lines) {
        for (String line : lines)
            this.lines.add(line);

        return this;
    }

    public Message add(Collection<String> lines) {
        for (String line : lines)
            this.lines.add(line);

        return this;
    }

    public void send(CommandSender... target) {
        send(Arrays.asList(target));
    }

    public void send(List<? extends CommandSender> targets) {
        LinkedList<String> finalText = cloneReplaceFormat(lines);

        for (CommandSender target : targets) {
            for (String line : finalText)
                target.sendMessage(line);
        }
    }

    public void broadcast() {
        LinkedList<String> finalText = cloneReplaceFormat(lines);

        for (String line : finalText)
            Bukkit.broadcastMessage(line);
    }

    @SuppressWarnings("deprecation")
    public void broadcast(List<World> worlds) {
        LinkedList<String> finalText = cloneReplaceFormat(lines);

        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (worlds.contains(pl.getWorld())) {
                for (String line : finalText)
                    pl.sendMessage(line);
            }
        }
    }

    public static String format(String toFormat) {
        return ChatColor.translateAlternateColorCodes('&', toFormat);
    }

    public static List<String> format(List<String> toFormat) {
        for (int i = 0; i < toFormat.size(); i++)
            toFormat.set(i, ChatColor.translateAlternateColorCodes('&', toFormat.get(i)));

        return toFormat;
    }

    public static String[] format(String[] toFormat) {
        for (int i = 0; i < toFormat.length; i++)
            toFormat[i] = ChatColor.translateAlternateColorCodes('&', toFormat[i]);

        return toFormat;
    }

    public static String unformat(String toUnformat) {
        return ChatColor.stripColor(toUnformat);
    }

    public static List<String> unformat(List<String> toUnformat) {
        for (int i = 0; i < toUnformat.size(); i++)
            toUnformat.set(i, ChatColor.stripColor(toUnformat.get(i)));

        return toUnformat;
    }

    public static String[] unformat(String[] toUnformat) {
        for (int i = 0; i < toUnformat.length; i++)
            toUnformat[i] = ChatColor.stripColor(toUnformat[i]);

        return toUnformat;
    }

    @SuppressWarnings("unchecked")
    public static Message fromConfig(YamlConfiguration config, String path) {
        Object object = config.get(path);

        if (object instanceof List<?>)
            return new Message((List<String>) object);

        else if (object instanceof String)
            return new Message((String) object);

        else
            return new Message(String.valueOf(object));
    }
}