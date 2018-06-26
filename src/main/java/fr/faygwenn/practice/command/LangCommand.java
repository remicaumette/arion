package fr.faygwenn.practice.command;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.api.lang.Lang;
import fr.faygwenn.practice.api.player.PracticePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LangCommand implements CommandExecutor {
    private Practice plugin;

    public LangCommand(Practice plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command !");
            return true;
        }
        Player player = (Player) sender;
        PracticePlayer practicePlayer = plugin.getPlayerManager().getPlayer(player);

        // Commande /lang fr

        if (args.length == 1 && args[0].equalsIgnoreCase("fr")) {
            practicePlayer.setLang(Lang.FRENCH);
            player.sendMessage(practicePlayer.getLang().tl("lang-change"));
            return true;
        }

        // Commande /lang en

        else if (args.length == 1 && args[0].equalsIgnoreCase("en")) {
            practicePlayer.setLang(Lang.ENGLISH);
            player.sendMessage(practicePlayer.getLang().tl("lang-change"));
            return true;
        }

        // Autre

        else {
            player.sendMessage(practicePlayer.getLang().tl("unknown-arguments", "/lang fr|en"));
            return true;
        }
    }
}
