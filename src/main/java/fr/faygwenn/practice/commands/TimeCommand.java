package fr.faygwenn.practice.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            final Player p = (Player) sender;
            if (command.getName().equalsIgnoreCase("day")) {
                if (args.length == 0) {
                    p.setPlayerTime(0L, false);
                    p.sendMessage(ChatColor.YELLOW + "[TimeChanger]" + ChatColor.GOLD + " Its now day time for you.");
                    return true;
                }
            } else if (command.getName().equalsIgnoreCase("night") && args.length == 0) {
                p.setPlayerTime(14500L, false);
                p.sendMessage(ChatColor.YELLOW + "[TimeChanger]" + ChatColor.GOLD + " Its now night time for you.");
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You are not a player.");
        }
        return true;
    }
}
