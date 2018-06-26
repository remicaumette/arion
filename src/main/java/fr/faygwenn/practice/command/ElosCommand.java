package fr.faygwenn.practice.command;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.kit.Kit;
import fr.faygwenn.practice.api.player.PracticePlayer;
import fr.faygwenn.practice.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ElosCommand implements CommandExecutor {
    private Practice plugin;

    public ElosCommand(Practice plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command !");
            return false;
        }

        Player player = (Player) sender;

        // Commande /elos [player]

        if (args.length == 1) {
            if (!player.hasPermission("practice.admin")) {
                showElos(player);
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            PracticePlayer practicePlayer = plugin.getPlayerManager().getPlayer(player);

            if (target == null) {
                player.sendMessage(practicePlayer.getLang().tl("unknown-player", args[0]));
                return true;
            }

            player.sendMessage("§aElos de " + target.getDisplayName());
            player.sendMessage("§a... en " + Kit.NODEBUFF.name + " : " + Utils.getElos(target, Kit.NODEBUFF));
            player.sendMessage("§a... en " + Kit.DEBUFF.name + " : " + Utils.getElos(target, Kit.DEBUFF));
            player.sendMessage("§a... en " + Kit.G_APPLE.name + " : " + Utils.getElos(target, Kit.G_APPLE));
            player.sendMessage("§a... en " + Kit.SUMO.name + " : " + Utils.getElos(target, Kit.SUMO));

            return true;
        }

        // Commande /elos

        else {
            showElos(player);
            return true;
        }

    }

    private void showElos(Player player) {
        player.sendMessage("§aElos de " + player.getDisplayName());
        player.sendMessage("§a... en " + Kit.NODEBUFF.name + " : " + Utils.getElos(player, Kit.NODEBUFF));
        player.sendMessage("§a... en " + Kit.DEBUFF.name + " : " + Utils.getElos(player, Kit.DEBUFF));
        player.sendMessage("§a... en " + Kit.G_APPLE.name + " : " + Utils.getElos(player, Kit.G_APPLE));
        player.sendMessage("§a... en " + Kit.SUMO.name + " : " + Utils.getElos(player, Kit.SUMO));
    }

}
