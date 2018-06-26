package fr.faygwenn.practice.command;

import fr.faygwenn.practice.kit.Kit;
import fr.faygwenn.practice.util.LangMessages;
import fr.faygwenn.practice.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ElosCommand implements CommandExecutor {
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

            if (target == null) {
                LangMessages.UNKNOWN_PLAYER.getFor(player).replace("{player}", args[0]).send(player);
                return false;
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
