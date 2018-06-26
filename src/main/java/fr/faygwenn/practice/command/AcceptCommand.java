package fr.faygwenn.practice.command;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.object.SelectKitFor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AcceptCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command !");
            return false;
        }

        Player player = (Player) sender;
        Practice.i.manager.cancelPreviousSearch(player);
        Practice.i.manager.selectKitFor.add(new SelectKitFor(player, null, SelectKitFor.Type.DUEL_ACCEPT));
        return true;
    }
}
