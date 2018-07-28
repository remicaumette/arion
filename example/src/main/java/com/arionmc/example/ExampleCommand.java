package com.arionmc.example;

import com.arionmc.arioncore.api.ArionApi;
import com.arionmc.arioncore.api.command.Command;
import com.arionmc.arioncore.api.command.CommandArguments;
import com.arionmc.arioncore.api.player.ArionPlayer;

public class ExampleCommand extends Command {
    public ExampleCommand() {
        super("example");
        setUsage("This is my example command!");
    }

    @Override
    public void handle(ArionPlayer player, CommandArguments arguments) {
        player.sendMessage("hello");

        ArionApi.getGuiManager().openGui(ExampleGui.class, player);
    }
}
