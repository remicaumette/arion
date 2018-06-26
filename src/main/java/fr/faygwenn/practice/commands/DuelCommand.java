package fr.faygwenn.practice.commands;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.objects.SelectKitFor;
import fr.faygwenn.practice.utils.LangMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command !");
            return false;
        }

        Player player = (Player) sender;

        if (Practice.i.manager.hasFight(player)) {
            LangMessages.IN_COMBAT_SELF.getFor(player).send(player);
            return false;
        }

        if (args.length != 1) {
            LangMessages.UNKNOWN_ARGUMENTS.getFor(player).replace("{usage}", "/duel <player>").send(player);
            return false;
        }

        Practice.i.manager.cancelPreviousSearch(player);
        Practice.i.manager.selectKitFor.add(new SelectKitFor(player, args[0], SelectKitFor.Type.DUEL));
        return true;
    }
}
