package com.arionmc.arioncore.display;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.display.Scoreboard;
import com.arionmc.arioncore.api.nms.NmsWrapper;
import com.arionmc.arioncore.api.player.ArionPlayer;

public class ArionCoreScoreboard implements Scoreboard {
    private ArionCore plugin;
    private ArionPlayer player;
    private String objective;
    private String name;
    private String lines[];
    private boolean locked;

    public ArionCoreScoreboard(ArionCore plugin, ArionPlayer player) {
        this.plugin = plugin;
        this.player = player;
        this.objective = player.getName().toLowerCase();
        this.lines = new String[15];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (locked) {
            return;
        }

        if (this.name == null && name != null) {
            plugin.getNmsWrapper().sendObjectivePacket(player, NmsWrapper.ObjectiveAction.CREATE, objective, name);
            plugin.getNmsWrapper().sendObjectiveSlotPacket(player, objective, 1);
        }
        if (this.name != null && name == null) {
            plugin.getNmsWrapper().sendObjectivePacket(player, NmsWrapper.ObjectiveAction.REMOVE, objective, this.name);
        }
        if (this.name != null && name != null && !this.name.equals(name)) {
            plugin.getNmsWrapper().sendObjectivePacket(player, NmsWrapper.ObjectiveAction.UPDATE, objective, name);
        }

        this.name = name;
    }

    @Override
    public String[] getLines() {
        return lines;
    }

    @Override
    public void setLine(int line, String value) {
        if (locked) {
            return;
        }

        if (lines[line] != null && !lines[line].equals(value)) {
            plugin.getNmsWrapper().sendScoreboardScorePacket(player, objective, NmsWrapper.ScoreboardScoreAction.REMOVE,
                    line, lines[line]);
        }

        if (value != null) {
            if (!value.equals(lines[line])) {
                plugin.getNmsWrapper().sendScoreboardScorePacket(player, objective, NmsWrapper.ScoreboardScoreAction.CHANGE,
                        line, value);
            }
        }

        lines[line] = value;
    }

    public void delete() {
        locked = true;

        if (name != null) {
            plugin.getNmsWrapper().sendObjectivePacket(player, NmsWrapper.ObjectiveAction.REMOVE, objective, null);

            for (int i = 0; i < lines.length; i++) {
                if (lines[i] != null) {
                    plugin.getNmsWrapper().sendScoreboardScorePacket(player,
                            objective,
                            NmsWrapper.ScoreboardScoreAction.REMOVE,
                            i,
                            lines[i]);
                }
            }
        }
    }
}
