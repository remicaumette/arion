package net.arion.arioncore.command;

import net.arion.arioncore.ArionCore;
import net.arion.arioncore.api.command.ArionCommand;
import net.arion.arioncore.api.command.exception.CommandException;
import net.arion.arioncore.player.ArionCorePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class InternalBukkitCommand extends Command {
    private ArionCore plugin;
    private ArionCommand command;

    public InternalBukkitCommand(ArionCore plugin, ArionCommand command) {
        super(command.getName(), command.getDescription(), command.getUsage(), command.getAliases());
        this.plugin = plugin;
        this.command = command;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You need to be a player!");
        } else {
            Player bukkitPlayer = (Player) sender;
            ArionCorePlayer player = plugin.getPlayerManager().getPlayer(bukkitPlayer.getUniqueId());

            try {
                if (player.getRank().getPower() >= command.getRequiredRank().getPower()) {
                    command.handle(player, new ArionCoreCommandArguments(plugin, args));
                } else {
                    throw new CommandException("error.not-allowed");
                }
            } catch (CommandException exception) {
                player.sendMessage(exception.getKey(), exception.getValues());
            } catch (Exception exception) {
                player.sendMessage("error.basic");
                plugin.getLogger().log(Level.WARNING, "An error occurred!", exception);
            }
        }
        return true;
    }
}
