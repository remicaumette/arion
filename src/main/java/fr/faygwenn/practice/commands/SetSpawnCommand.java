package fr.faygwenn.practice.commands;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.utils.Messages;
import fr.faygwenn.practice.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command !");
            return false;
        }

        Player player = (Player) sender;

        Practice.i.database.set("spawn", Utils.serializeLocation(player.getLocation()));
        Messages.DEFINE_SPAWN.get().send(player);

        return true;
    }
}
