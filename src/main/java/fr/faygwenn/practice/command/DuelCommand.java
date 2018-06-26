package fr.faygwenn.practice.command;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.object.SelectKitFor;
import fr.faygwenn.practice.api.player.PracticePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuelCommand implements CommandExecutor {
    private Practice plugin;

    public DuelCommand(Practice plugin) {
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

        if (plugin.manager.hasFight(player)) {
            player.sendMessage(practicePlayer.getLang().tl("in-fight-self"));
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(practicePlayer.getLang().tl("unknown-arguments", "/duel <player>"));
            return true;
        }

        plugin.manager.cancelPreviousSearch(player);
        plugin.manager.selectKitFor.add(new SelectKitFor(player, args[0], SelectKitFor.Type.DUEL));
        return true;
    }
}
