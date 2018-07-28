package com.arionmc.example;

import com.arionmc.arioncore.api.display.Scoreboard;
import com.arionmc.arioncore.api.display.ScoreboardFormatter;
import com.arionmc.arioncore.api.player.ArionPlayer;

import java.text.DecimalFormat;

public class ExampleScoreboardFormatter implements ScoreboardFormatter {
    private static final DecimalFormat FORMAT = new DecimalFormat("#");


    @Override
    public void formatScoreboard(ArionPlayer player, Scoreboard scoreboard) {
        scoreboard.setName("§2Salut " + player.getName());
        scoreboard.setLine(6, "");
        scoreboard.setLine(5, "§a§lPosition :");
        scoreboard.setLine(4, "§fX: "+ FORMAT.format(player.getBukkitPlayer().getLocation().getX()));
        scoreboard.setLine(3, "§fY: "+ FORMAT.format(player.getBukkitPlayer().getLocation().getY()));
        scoreboard.setLine(2, "§fZ: "+ FORMAT.format(player.getBukkitPlayer().getLocation().getZ()));
        scoreboard.setLine(1, " ");
    }
}
