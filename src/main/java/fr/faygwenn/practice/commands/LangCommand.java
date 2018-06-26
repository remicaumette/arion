package fr.faygwenn.practice.commands;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.utils.LangMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LangCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command !");
            return false;
        }

        Player player = (Player) sender;

        // Commande /lang fr

        if (args.length == 1 && args[0].equalsIgnoreCase("fr")) {
            Practice.i.database.set("lang." + player.getUniqueId().toString(), "fr");
            LangMessages.LANG_CHANGE.getFor(player).send(player);
            return true;
        }

        // Commande /lang en

        else if (args.length == 1 && args[0].equalsIgnoreCase("en")) {
            Practice.i.database.set("lang." + player.getUniqueId().toString(), "en");
            LangMessages.LANG_CHANGE.getFor(player).send(player);
            return true;
        }

        // Autre

        else {
            LangMessages.UNKNOWN_ARGUMENTS.getFor(player).replace("{usage}", "/lang fr|en").send(player);
            return false;
        }
    }
}
