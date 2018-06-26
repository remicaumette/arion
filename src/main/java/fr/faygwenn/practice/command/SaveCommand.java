package fr.faygwenn.practice.command;

import fr.faygwenn.practice.kit.KitEditor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command !");
            return false;
        }

        KitEditor.stop((Player) sender);
        // TODO Practice.i.hide((Player) sender);
        return true;
    }
}
