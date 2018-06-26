package fr.faygwenn.practice.commands;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.utils.LangMessages;
import fr.faygwenn.practice.utils.Messages;
import fr.faygwenn.practice.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command !");
            return false;
        }

        Player player = (Player) sender;

        // Commande /arena setp1 [name]

        if (args.length == 2 && args[0].equalsIgnoreCase("setp1")) {
            String name = ChatColor.translateAlternateColorCodes('&', args[1]);

            Practice.i.database.set("arenas." + Utils.getArenaPath(name) + ".name", name);
            Practice.i.database.set("arenas." + Utils.getArenaPath(name) + ".location-1", Utils.serializeLocation(player.getLocation()));
            Messages.DEFINE_ARENA.get().replace("{arena}", name).replace("{point}", 1).send(player);

            return true;
        }

        // Commande /arena setp2 [name]

        else if (args.length == 2 && args[0].equalsIgnoreCase("setp2")) {
            String name = ChatColor.translateAlternateColorCodes('&', args[1]);

            Practice.i.database.set("arenas." + Utils.getArenaPath(name) + ".name", name);
            Practice.i.database.set("arenas." + Utils.getArenaPath(name) + ".location-2", Utils.serializeLocation(player.getLocation()));
            Messages.DEFINE_ARENA.get().replace("{arena}", name).replace("{point}", 2).send(player);

            return true;
        }

        // Commande /arena remove [name]

        else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
            String path = args[1];
            String name = Utils.getArenaName(path);

            Practice.i.database.set("arenas." + path, null);
            Messages.REMOVE_ARENA.get().replace("{arena}", name).send(player);

            return true;
        }

        // Commande /arena list

        else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            ConfigurationSection sec = Practice.i.database.get().getConfigurationSection("arenas");

            if (sec == null) {
                Messages.NO_ARENAS.get().send(player);
                return false;
            }

            Messages.LIST_ARENAS.get().send(player);

            for (String path : sec.getKeys(false)) {
                Location loc1 = Utils.getArenaLocation(path, 1);
                Location loc2 = Utils.getArenaLocation(path, 1);

                player.sendMessage("§a" + Utils.getArenaName(path) + " §f: ");
                player.sendMessage("§fSpawn 1 : " + loc1.getWorld().getName() + ", " + loc1.getBlockX() + ", " + loc1.getBlockY() + ", " + loc1.getBlockZ());
                player.sendMessage("§fSpawn 2 : " + loc2.getWorld().getName() + ", " + loc2.getBlockX() + ", " + loc2.getBlockY() + ", " + loc2.getBlockZ());
            }

            return true;
        }

        // Autre

        else {
            LangMessages.UNKNOWN_ARGUMENTS.getFor(player).replace("{usage}", "/arena setp1 [arena]|setp2 [arena]|remove|list").send(player);
            return false;
        }
    }
}
