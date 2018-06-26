package fr.faygwenn.practice.command;

import fr.faygwenn.practice.api.command.PracticeCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CoreInternalCommand extends Command {
    private PracticeCommand command;

    public CoreInternalCommand(PracticeCommand command) {
        super(command.getName(), command.getDescription(), command.getUsage(), command.getAliases());
        this.command = command;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage(command.getName());
        return true;
    }
}
